package com.fanyi.scm.controller;

import com.fanyi.scm.vo.User;
import com.fanyi.scm.service.user.UserService;
import com.fanyi.scm.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登陆控制类
 * @author wangyds
 * @date 2019/04/11
 */
@Controller
public class LoginController {
	@Resource
	private UserService userService;

	/**
	 * 登陆页面控制
	 */
	@RequestMapping(value="/")
	public String login(){
		return "login";
	}


	/**
	 * 注册页面控制
	 */
	@RequestMapping("/regist.html")
	public String register(){
		return "regist";
	}

	/**
	 * 修改密码页面
	 * @return
	 */
	@RequestMapping(value="/forgetPassword.html")
	public String forgetPassword(){
		return "error";
	}


	/**
	 * 登陆页面控制，成功进入首页，失败返回登录页
	 * @param code
	 * @param password
	 * @param session
	 * @param request
	 * @param model
	 * @return 返回页面
	 */
	@RequestMapping(value="/welcome")
	public String doLogin(@RequestParam(value="code")String code,
			@RequestParam(value="password")String password,
			HttpSession session,
			HttpServletRequest request,
			Model model){
		User user = userService.getUserLogin(code, password);
		if(null != user){
			session.setAttribute(Constants.USER_SESSION, user);
			model.addAttribute("activeFrame", "activeMenu");
			return "frame";
		}
		request.setAttribute("error", "用户名或密码错误！");
		return "login";
	}

	@RequestMapping("/exit.html")
	public String loginOut(HttpSession session){
		session.removeAttribute(Constants.USER_SESSION);
		return "redirect:/";
	}
	
}
