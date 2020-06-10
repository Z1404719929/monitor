package com.datangedu.cn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.datang.hrb.util.MD5Util;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;



@SpringBootApplication
public class MonitorApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MonitorApplication.class, args);
//		System.out.println(MonitorApplication.execute(MonitorApplication.login("192.168.1.130","zhaochaoqun","123456"),"bash ~/sh/echo.sh"));
//		String exec = exec("192.168.1.50", "root", "123456", 22, "halt -h");
//		System.out.println(exec);	
//		restore("settopbox");
	}
	
	
	public static void restore(String databaseName) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe -hlocalhost -uroot -p123456 --default-character-set=utf8 "
                            + databaseName);
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("D:\\test\\stp.sql"), "utf-8"));
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
//	   
	
//	public static void main(String[] args) {
//
//		// 数据库导出
//
//		String user = "root"; // 数据库帐号
//
//		String password = "123456"; // 数据库密码
//
//		String database = "settopbox"; // 需要备份的数据库名
//
//		String filepath = "D:\\test\\stp.sql"; // 需要备份到的地址
//
//		String stmt1 = "mysqldump " + database + " -u " + user + " -p" + password + " --result-file=" + filepath;
//
//		try {
//
//		     Runtime.getRuntime().exec(stmt1);
//
//		     System.out.println("数据已导出到文件" + filepath + "中");
//
//		} catch (IOException e) {
//
//		     e.printStackTrace();
//
//		}
//
//		}
	

	

	
	public static String exec(String host,String user,String psw,int port,String command){
		String result="";
		Session session =null;
		ChannelExec openChannel =null;
		try {
			JSch jsch=new JSch();
			session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(psw);
			session.connect();
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			int exitStatus = openChannel.getExitStatus();
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
		return result;
	}
}
