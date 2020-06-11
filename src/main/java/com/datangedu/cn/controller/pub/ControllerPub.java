package com.datangedu.cn.controller.pub;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datang.hrb.util.ImgCodeUtil;
import com.datangedu.cn.model.sysUser.MonitorUser;
import com.datangedu.cn.service.MonitorService;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Controller
public class ControllerPub {
	@Resource
	MonitorService monitorservice;
	
	//网页跳转注解
//	@RequestMapping("/redirect")
//	public String page(HttpServletRequest request) {
//		String url = request.getParameter("page");
//		System.out.println("跳转到" + url);
//		return url;
//	}

	//验证码
	@RequestMapping("/imgGetCode")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * System.out.println("run in doGet"); resp.sendRedirect("ok.jsp");
		 */
		System.out.println("run in doGet");

		// 调用工具类生成的验证码和验证码图片
		Map<String, Object> codeMap = ImgCodeUtil.generateCodeAndPic();

		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		session.setAttribute("code", codeMap.get("code").toString());

		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no	-cache");
		resp.setDateHeader("Expires", -1);

		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos;
		try {
			sos = resp.getOutputStream();
			ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//头像
	@ResponseBody	
	@RequestMapping(value="/headImg", produces = MediaType.IMAGE_PNG_VALUE)		//显示头像
	public ResponseEntity<byte[]> headImg(String id) throws Exception{
//		System.out.println("tp");
		byte[] imageContent ;
		// 根据id获取当前用户的信息
		MonitorUser user = monitorservice.Select(id);
				        
		imageContent = user.getHeadImg();
		System.out.println("图片==="+user.getHeadImg());
				        
		// 设置http头部信息
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		// 返回响应实体
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	

	    
	//对linux进行操作
	public int exec(String host,String command){
		String user="zhaochaoqun";						//登录用户名
		String psw="123456";							//登录密码
		int port=22;									//端口号
		String result="";
		Session session =null;
		ChannelExec openChannel =null;
		int exitStatus=0;
		try {
			JSch jsch=new JSch();
			session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(psw);
			session.setTimeout(7000);
			session.connect();
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			exitStatus = openChannel.getExitStatus();
			System.out.println(exitStatus);
			openChannel.connect();  
            InputStream in = openChannel.getInputStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
            String buf = null;
            while ((buf = reader.readLine()) != null) {
            	result+= new String(buf.getBytes("gbk"),"UTF-8")+"    <br>\r\n";  
            }  
		} catch (JSchException | IOException e) {
			result+=e.getMessage();
		}finally{
			if(openChannel!=null&&!openChannel.isClosed()){
				openChannel.disconnect();
			}
			if(session!=null&&session.isConnected()){
				session.disconnect();
			}
		}
		return exitStatus;
	}
	//使用root用户
	public int exec1(String host,String command){
		String user="root";						//登录用户名
		String psw="123456";							//登录密码
		int port=22;									//端口号
		String result="";
		Session session =null;
		ChannelExec openChannel =null;
		int exitStatus=0;
		try {
			JSch jsch=new JSch();
			session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(psw);
			session.setTimeout(5000);
			session.connect();
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			exitStatus = openChannel.getExitStatus();
			System.out.println(exitStatus);
			openChannel.connect();  
            InputStream in = openChannel.getInputStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
            String buf = null;
            while ((buf = reader.readLine()) != null) {
            	result+= new String(buf.getBytes("gbk"),"UTF-8")+"    <br>\r\n";  
            }  
		} catch (JSchException | IOException e) {
			result+=e.getMessage();
		}finally{
			if(openChannel!=null&&!openChannel.isClosed()){
				openChannel.disconnect();
			}
			if(session!=null&&session.isConnected()){
				session.disconnect();
			}
		}
		return exitStatus;
	}

}
