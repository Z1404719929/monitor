package com.datangedu.cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;



@SpringBootApplication
public class MonitorApplication {
//	private static final Logger log = LoggerFactory.getLogger(MonitorApplication.class);
//    private static String  DEFAULTCHART="UTF-8"; 
//
//    /** 
//     * 登录主机 
//     * @return 
//     *      登录成功返回true，否则返回false 
//     */  
//    public static Connection login(String ip,
//                            String userName,
//                            String userPwd){  
//
//        boolean flg=false;
//        Connection conn = null;
//        try {  
//            conn = new Connection(ip);  
//            conn.connect();//连接  
//            flg=conn.authenticateWithPassword(userName, userPwd);//认证  
//            if(flg){
//                log.info("=========登录成功========="+conn);
//                return conn;
//            }
//        } catch (IOException e) {  
//            log.error("=========登录失败========="+e.getMessage());
//            e.printStackTrace();  
//        }  
//        return conn;  
//    }  
//
//    /** 
//     * 远程执行shll脚本或者命令 
//     * @param cmd 
//     *      即将执行的命令 
//     * @return 
//     *      命令执行完后返回的结果值 
//     */  
//    public static String execute(Connection conn,String cmd){  
//        String result="";  
//        try {  
//            if(conn !=null){  
//                Session session= conn.openSession();//打开一个会话  
//                session.execCommand(cmd);//执行命令  
//                result=processStdout(session.getStdout(),DEFAULTCHART);  
//                //如果为得到标准输出为空，说明脚本执行出错了  
//                if(StringUtils.isBlank(result)){
//                    log.info("得到标准输出为空,链接conn:"+conn+",执行的命令："+cmd);
//                    result=processStdout(session.getStderr(),DEFAULTCHART);  
//                }else{
//                    log.info("执行命令成功,链接conn:"+conn+",执行的命令："+cmd);
//                }  
//                conn.close();  
//                session.close();  
//            }  
//        } catch (IOException e) {
//            log.info("执行命令失败,链接conn:"+conn+",执行的命令："+cmd+"  "+e.getMessage());
//            e.printStackTrace();  
//        }  
//        return result;  
//    }
//    /** 
//     * 解析脚本执行返回的结果集 
//     * @param in 输入流对象 
//     * @param charset 编码 
//     * @return 
//     *       以纯文本的格式返回 
//     */  
//     private static String processStdout(InputStream in, String charset){  
//         InputStream  stdout = new StreamGobbler(in);  
//         StringBuffer buffer = new StringBuffer();;  
//         try {  
//             BufferedReader br = new BufferedReader(new InputStreamReader(stdout,charset));  
//             String line=null;  
//             while((line=br.readLine()) != null){  
//                 buffer.append(line+"\n");  
//             }  
//         } catch (UnsupportedEncodingException e) { 
//             log.error("解析脚本出错："+e.getMessage());
//             e.printStackTrace();  
//         } catch (IOException e) {
//             log.error("解析脚本出错："+e.getMessage());
//             e.printStackTrace();  
//         }  
//         return buffer.toString();  
//     }  
     

	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
//		System.out.println(MonitorApplication.execute(MonitorApplication.login("192.168.1.130","zhaochaoqun","123456"),"bash ~/sh/echo.sh"));
//		String exec = exec("192.168.1.130", "zhaochaoqun", "123456", 22, "bash ~/sh/webskstart.sh");
//		System.out.println(exec);	
	}
	
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
