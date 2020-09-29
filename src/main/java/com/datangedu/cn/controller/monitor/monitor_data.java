package com.datangedu.cn.controller.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.HiveApi;

@Controller
@RequestMapping("/monitor_data")
public class monitor_data {

	// 数据导入freight表
	@ResponseBody
	@RequestMapping(value = "/hivefreight", method = RequestMethod.POST)
	public static Map<String, Object> hivefreight(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("hive" + request.getParameter("url"));
		HiveApi hive = new HiveApi();
		// 加载驱动、创建连接
		hive.init();

		// 导入hive
		hive.loadDatafreight(request.getParameter("url"));

		// 释放资源
		hive.destory();
		return map;
	}

	// 数据导入passenger表
	@ResponseBody
	@RequestMapping(value = "/hivepassenger", method = RequestMethod.POST)
	public static Map<String, Object> hivepassenger(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("hive" + request.getParameter("url"));
		HiveApi hive = new HiveApi();
		// 加载驱动、创建连接
		hive.init();

		// 导入hive
		hive.loadDatapassenger(request.getParameter("url"));

		// 释放资源
		hive.destory();
		return map;
	}

	// python
	//数据采集
	@ResponseBody
	@RequestMapping(value = "/coll", method = RequestMethod.POST)
	public static Map<String, Object> coll() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// 调用python文件
				Process proc;
		        try {
		        	 proc = Runtime.getRuntime().exec("python D:\\3software\\PythonProject\\untitled2\\俄罗斯机场\\数据采集.py");// 执行py文件
		            //用输入输出流来截取结果
		            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		            String line = null;
		            while ((line = in.readLine()) != null) {
		                System.out.println(line);
		            }
		            map.put("msg", "采集运行成功");
		            System.out.println("成功");
//		            in.close();
		            proc.waitFor();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }

		return map;
	}

	//数据处理
	@ResponseBody
	@RequestMapping(value = "/clean", method = RequestMethod.POST)
	public static Map<String, Object> clean() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		Process proc;
        try {
        	 proc = Runtime.getRuntime().exec("python D:\\3software\\PythonProject\\untitled2\\俄罗斯机场\\数据处理.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            map.put("msg", "处理运行成功");
            System.out.println("成功");
//            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return map;
	}
	
	// 货运数据预测
		@ResponseBody
		@RequestMapping(value = "/freightforecast", method = RequestMethod.POST)
		public static Map<String, Object> freightforecast() throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			
			Process proc;
	        try {
	        	 proc = Runtime.getRuntime().exec("python D:\\3software\\PythonProject\\untitled2\\俄罗斯机场\\数据预测freight.py");// 执行py文件
	            //用输入输出流来截取结果
	            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	            String line = null;
	            while ((line = in.readLine()) != null) {
	                System.out.println(line);
	            }
	            map.put("msg", "货运预测成功");
	            System.out.println("成功");
//	            in.close();
	            proc.waitFor();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			return map;
		}

	// 客运数据预测
	@ResponseBody
	@RequestMapping(value = "/passengerforecast", method = RequestMethod.POST)
	public static Map<String, Object> passengerforecast() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Process proc;
        try {
        	 proc = Runtime.getRuntime().exec("python D:\\3software\\PythonProject\\untitled2\\俄罗斯机场\\数据预测passenger.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            map.put("msg", "客运预测成功");
            System.out.println("成功");
//            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return map;
	}
	
}