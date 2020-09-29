package com.datangedu.cn.controller.monitor;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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
@RequestMapping("/mysql")
public class controller_mysql {

	//数据库备份
	@ResponseBody
	@RequestMapping(value = "/backups", method = RequestMethod.POST) // 路径拦截查找用户信息
	public Map<String, Object> Login(HttpServletRequest request) throws InterruptedException {
		Map<String, Object> map = new HashMap<String, Object>();
		String user = "root"; // 数据库帐号

		String password = "123456"; // 数据库密码

		String database = "settopbox"; // 需要备份的数据库名

		String filepath = "D:\\workspace2\\Airport\\mysql\\airport.sql"; // 需要备份到的地址

		String stmt1 = "mysqldump " + database + " -u " + user + " -p" + password + " --result-file=" + filepath;	//命令

		try {

			Process a=Runtime.getRuntime().exec(stmt1);

			System.out.println("数据已导出到文件" + filepath + "中");
			
			map.put("status",a.waitFor());

		} catch (IOException e) {

			e.printStackTrace();

		}
		return map;
	}
	
	//数据库还原
	@ResponseBody
	@RequestMapping(value = "/reduction", method = RequestMethod.POST) // 路径拦截查找用户信息
	public Map<String, Object> restore() {
		Map<String, Object> map = new HashMap<String, Object>();
		String databaseName="airport";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe -hlocalhost -uroot -p123456 --default-character-set=utf8 "
                            + databaseName);
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("D:\\workspace2\\Airport\\mysql\\airport.sql"), "utf-8"));
//            System.out.println("4564646=="+br);
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
//             System.out.println(str);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                    "utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
            map.put("status", 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
