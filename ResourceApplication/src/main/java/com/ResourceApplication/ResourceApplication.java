package com.ResourceApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ResourceApplication extends WebSecurityConfigurerAdapter {

	 @RequestMapping("/")
	 @CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
	  public Message home() {
	    return new Message("Hello Pankaj");
	  }
	 
	 @RequestMapping("/detailHome")
	 @CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with"})
	 public Map<String, String> detailHome(){
		// System.out.println("detailhome hit");
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("detail1", "This is the detail 1 line");
		 map.put("detail2", "This is the detail 2 line");
		 return map;
	 }
	 
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.cors().and().authorizeRequests()
	      .anyRequest().authenticated();
	  }
	 
	 @Bean
	  HeaderHttpSessionStrategy sessionStrategy() {
	    return new HeaderHttpSessionStrategy();
	  }

	 
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
	
	class Message {
		  private String id = UUID.randomUUID().toString();
		  private String content;
		  public Message(String content) {
		    this.content = content;
		  }
		 
		  // ... getters and setters and default constructor
		  public Message(){}
		  public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		}
}

