package com.datangedu.cn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.datangedu.cn.model.sysUser.FreightForecast;



@SpringBootApplication
public class AirportApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(AirportApplication.class);
	}

	public static void main(String[] args) throws Exception{
		SpringApplication.run(AirportApplication.class, args);
//		List list = new ArrayList<>();
//		List<FreightForecast> a=new ArrayList<FreightForecast>();
//		FreightForecast b=new FreightForecast();
//		b.setAirportname("a");
//		for(int i=0;i<10;i++) {
//			a.add(i,b);
//			System.out.println(a.get(i).getAirportname());
//		}
		
		
	}
}
