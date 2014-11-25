package com.xiaoy.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoy.entity.User;
import com.xiaoy.exception.UserException;

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
	 * @throws IOException 
		@RequestMapping(value="/add", method=RequestMethod.GET)
		public String add(@ModelAttribute("user")User user){
			return "user/userAdd";
		}
	*/
	
	//������ӣ�ִ�б������������Ϊpost
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@Validated User user, BindingResult br, @RequestParam("attachs")MultipartFile[] attachs, HttpServletRequest req) throws IOException{//BindingResultһ��Ҫ����@Validated�ĺ��棬��������
		if(br.hasErrors()){
			return "user/userAdd";
		}
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		for(MultipartFile attach : attachs)
		{
			if(attach.isEmpty())continue;
			File f = new File(realpath + "/" + attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
		}
		users.put(user.getUsername(), user);
		
		return "redirect:/user/users";
	}
	
	//��·���л��username
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String show(@PathVariable String username, Model model){
		model.addAttribute("user",users.get(username));
		//model.addAttribute(users.get(username));//���ַ�ʽ��ͬ
		return "user/userShow";
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET,params="json")
	@ResponseBody
	public User show(@PathVariable String username){
//		User user = users.get(usernam);
		return users.get(username);
	}
	
	@RequestMapping(value="/{username}/update", method=RequestMethod.GET)
	public String update(@PathVariable String username, Model model){
		model.addAttribute("user", users.get(username));
		return "user/userUpdate";
	}
	
	@RequestMapping(value="/{username}/update", method=RequestMethod.POST)
	public String update(@PathVariable String username,@Validated User user, BindingResult br){
		if(br.hasErrors())
		{
			return "user/userUpdate";
		}
		users.put(username, user);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/{username}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String username){
		users.remove(username);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String username,String password, HttpSession session)
	{
		if(!users.containsKey(username))
		{
			throw new UserException("�û����ֲ�����");
		}
		User u = users.get(username);
		if(!password.equals(u.getPassword()))
		{
			throw new UserException("���벻��ȷ");
		}
		session.setAttribute("loginName", u);
		return "redirect:/user/users";
	}
	
	/**
	 * �ֲ��쳣��������ֻ�����ڵ�ǰ�Ŀ�����
	 * @param e
	 * @param rq
	 * @return
	 */
	/**
	@ExceptionHandler(value=UserException.class)
	public String handlerException(UserException e, HttpServletRequest rq)
	{
		rq.setAttribute("e", e);
		return "error";
	}
	*/
}


















