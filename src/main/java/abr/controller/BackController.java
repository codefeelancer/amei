package abr.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import abr.bean.User;
import abr.dao.UserDao;
import abr.dao.WebpageDao;

/**
 * 后台请求的Controller
 * @author Y.H.
 * @create 2017-11-21 21:34
 **/
@Controller
public class BackController {
	private Logger log = LoggerFactory.getLogger(BackController.class);

	@Autowired
	public WebpageDao webpageDao;
	
	@Autowired
	public UserDao userDao;

	@RequestMapping(value = "/admin/index")
	public String toIndex() {
		return "/admin-index";
	}

	@RequestMapping(value = "/login")
	public String toLogin() {
		return "/login";
	}
	
	@RequestMapping(value = "user/logout")
	public String logout(HttpSession session){
		log.info("User \""+((User)session.getAttribute("user")).getUsername()+"\"has logged out");
		session.invalidate();
        return "redirect:/login";
	}
	
	@RequestMapping(value = "user/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String login(@RequestParam String checkCode, @RequestParam String username,@RequestParam String password, HttpSession session) {
        
        if(checkCode == null || "".equals(checkCode) || 
        		!checkCode.equals((String)session.getAttribute("checkCode"))){
            return "输入的验证码不正确，请重新输入！";
        }
        
		User user = userDao.queryUser(username, password);
		if (user != null) {
			session.setAttribute("user", user);
			log.info("User \'"+((User)session.getAttribute("user")).getUsername()+"\' successfully login just now.");
			return "success";
		} else
			log.info("Someone who is named "+username+" is using password \'"+password+"\' to login");
			return "fail";
	}

}
