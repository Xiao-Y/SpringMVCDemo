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
	
	//���ӵ�addҳ�棬������get
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model){
		//����ModelDirven
		model.addAttribute("user", new User());
		return "user/userAdd";
	}
	
	/**
	 * �ڶ��ַ�ʽ��
		@RequestMapping(value="/add", method=RequestMethod.GET)
		public String add(@ModelAttribute("user")User user){
			return "user/userAdd";
		}
	*/
	
	//������ӣ�ִ�б������������Ϊpost
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@Validated User user, BindingResult br){//BindingResultһ��Ҫ����@Validated�ĺ��棬��������
		if(br.hasErrors()){
			return "user/userAdd";
		}
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}
	
	//��·���л��username
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String show(@PathVariable String username, Model model){
		model.addAttribute("user",users.get(username));
		//model.addAttribute("user",users.get(username));//���ַ�ʽ��ͬ
		return "user/userShow";
	}
}


















