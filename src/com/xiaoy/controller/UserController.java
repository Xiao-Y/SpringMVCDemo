package com.xiaoy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoy.entity.User;

@Controller(value="userController")
@RequestMapping(value="/user")
public class UserController {

	Map<String, User> users = new HashMap<String, User>();
	
	public UserController(){
		users.put("xiaoy", new User("xiaoy", "123", "xiaoy"));
		users.put("teowl", new User("teowl", "123", "teowl"));
		users.put("wwwqd", new User("wwwqd", "123", "wwwqd"));
		users.put("yuyeg", new User("yuyeg", "123", "yuyeg"));
		users.put("wqqry", new User("wqqry", "123", "wqqry"));
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("users", users);
		return "user/userList";
	}
	
	//链接到add页面，请求是get
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model){
		//开启ModelDirven
		model.addAttribute("user", new User());
		return "user/userAdd";
	}
	
	/**
	 * 第二种方式：
		@RequestMapping(value="/add", method=RequestMethod.GET)
		public String add(@ModelAttribute("user")User user){
			return "user/userAdd";
		}
	*/
	
	//具体添加，执行保存操作，请求为post
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@Validated User user, BindingResult br){//BindingResult一定要跟在@Validated的后面，否则会出错
		if(br.hasErrors()){
			return "user/userAdd";
		}
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}
	
	//从路径中获得username
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String show(@PathVariable String username, Model model){
		model.addAttribute("user",users.get(username));
		//model.addAttribute("user",users.get(username));//两种方式相同
		return "user/userShow";
	}
}


















