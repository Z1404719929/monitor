package com.datangedu.cn.controller.monitor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.controller.pub.ControllerPub;
import com.datangedu.cn.model.sysUser.MonitorUser;
import com.datangedu.cn.model.sysUser.Region;
import com.datangedu.cn.service.MonitorService;
import com.datangedu.cn.service.RegionService;


@Controller
@RequestMapping("/monitor_center")
public class monitor_center {
	@Resource
	MonitorService monitorservice;
	@Resource
	RegionService rService;
	
	@ResponseBody
	@RequestMapping(value = "/info",method = RequestMethod.POST)	//路径拦截查找用户信息
	public Map <String,Object> Login(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String id=request.getParameter("id");
		//查找用户信息
		MonitorUser userInfo = monitorservice.Select(id);
		map.put("userinfo",userInfo);
		System.out.println("111111111111111111"+userInfo.getRegionId());
		//获取省市区
		List<Region> district = rService.getList(userInfo.getRegionId());		//区
		map.put("district",district);
		System.out.println("111111111111111111111111");
		List<Region> county = rService.getList(district.get(0).getParentId());			//市
		map.put("county",county);
		List<Region> province = rService.getList(county.get(0).getParentId());			//省
		map.put("province",province);

		return map;
}
	
	
	@ResponseBody		//得到省
	@RequestMapping(value="/province",method = RequestMethod.POST)
	public Map<String,Object> getsheng(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Region> sheng = rService.getLevel((short) 1);
		map.put("sheng", sheng);
		System.out.println("省id=="+sheng.get(0).getId());
		return map;
	}
	
	@ResponseBody		//得到市
	@RequestMapping(value="/county",method = RequestMethod.POST)
	public Map<String,Object> getshi(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("选择的省id=="+request.getParameter("id"));
		List<Region> shi = rService.getshi(request.getParameter("id"));
		map.put("shi", shi);
		System.out.println("市id=="+shi.get(0).getId());
		return map;
	}
	
	
	@ResponseBody		//得到区
	@RequestMapping(value="/district",method = RequestMethod.POST)
	public Map<String,Object> getqu(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("选择的市id=="+request.getParameter("id"));
		List<Region> qu = rService.getshi(request.getParameter("id"));
		map.put("qu", qu);
		System.out.println("区id=="+qu.get(0).getId());
		return map;
	}
	

	//头像上传
	@ResponseBody
    @RequestMapping("upload")
    public Map upload(MultipartFile file,HttpServletRequest request){

        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\workspace2\\Monitor\\src\\main\\resources\\static\\images\\" + dateStr+"\\"+uuid+"." + prefix;
                System.out.println(request.getParameter("id"));
                
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+ dateStr+"/"+uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }
	
	
	//改变用户信息changeUserinfo
	@ResponseBody		
	@RequestMapping(value="/changeUserinfo",method = RequestMethod.POST)
	public Map<String,Object> changeUserinfo(HttpServletRequest request) throws ParseException, IOException {
		Map<String,Object> map = new HashMap<String,Object>();

		MonitorUser monitoruser = new MonitorUser();	
        monitoruser.setUserName(request.getParameter("username"));					//用户名
        //查找手机号是否存在且不等于当前手机号
  		List<MonitorUser> userInfo = monitorservice.Selectbycellphone(request.getParameter("cellphone"));
  		MonitorUser userInfo2 = monitorservice.Select(request.getParameter("userid"));
  		if(!userInfo.isEmpty()&&!userInfo.get(0).getCellphone().equals(userInfo2.getCellphone())) {
  			map.put("msg","手机号绑定其他账号" );
  			System.out.println("手机号绑定其他账号");
  			return map;
  		}
        monitoruser.setCellphone(request.getParameter("cellphone"));				//手机号
        monitoruser.setRegionId(request.getParameter("district"));					//区号
        //日期String转换成Date
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        Date date =  formatter.parse(request.getParameter("born"));
        Calendar calendar = new GregorianCalendar(); 			//
        calendar.setTime(date); 
        calendar.add(calendar.DATE,1);		//增加一天日期，因为数据库是datetime类型，存入时后边时间是00:00，取出时会少一天
        date=calendar.getTime();
        monitoruser.setBorn(date);														//出生日期
        monitoruser.setGender(Integer.parseInt(request.getParameter("gender")));		//性别
        //选择图片时
        if(!request.getParameter("images").isEmpty()) {
        	File originalFile = new File("D:\\workspace2\\Monitor\\src\\main\\resources\\static\\"+request.getParameter("images"));//指定要读取的图片
        	FileInputStream in = new FileInputStream(originalFile);		//要写入的图片
        	
                byte[] buffer = new byte[1024];		// 存储每次读取的内容
                int len=0;		// 每次读取的字节长度
                ByteArrayOutputStream bos=new ByteArrayOutputStream();		//接收
                while((len=in.read(buffer))!=-1){
                	bos.write(buffer,0,len);							// 将读取的内容，写入到输出流当中
                }
                bos.flush();
                byte data[] = bos.toByteArray();	//存入byte数组
                monitoruser.setHeadImg(data);		//头像
                System.out.println("2");
                bos.close();
                in.close();
        }
        
        monitorservice.changeUserinfo(monitoruser,request.getParameter("userid"));
        System.out.println("用户信息上传成功");
        map.put("msg","修改成功" );
		return map;
	}

}
