package com.example.empManageApp.models.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.example.empManageApp.models.Ceo;
import com.example.empManageApp.models.Clerk;
import com.example.empManageApp.models.Manager;
import com.example.empManageApp.models.Programmer;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Scope("prototype")
	@Bean("g_pro")
	public Programmer getProgrammer() {
		return new Programmer();
	}
	@Bean("g_ceo")
	@Lazy
	public Ceo getCeo() {
		return Ceo.getCeoInstance();	
	}
	
	@Scope("prototype")
	@Bean("g_clerk")
	public Clerk getClerk() {
		return new Clerk();
	}
	
	@Scope("prototype")
	@Bean("g_man")
	public Manager getManager() {
		return new Manager();
	}
	
	
	
	
	
}
