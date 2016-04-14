package ${basePackage}.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import ${basePackage}.service.I${dbTable.entityName?cap_first}Service;
import ${basePackage}.util.*;
import ${basePackage}.base.*;
import ${basePackage}.domain.*;
/**
 *@Description 用户登陆/注销Controller
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController{

	@Autowired
	I${dbTable.entityName?cap_first}Service ${dbTable.entityName}Service;

	@RequestMapping("/login")
	public String login(String ${username},String ${password},String securityCode){
		String serverCode = (String) request.getSession().getAttribute("SESSION_SECURITY_CODE");
		if(StringUtil.isEmpty(serverCode)){
			request.setAttribute("message","验证码过期，请重新输入");
			return "login/loginUI";
		}
		
		if(!serverCode.equals(securityCode)){
			request.setAttribute("message","验证码错误，请重新输入");
			return "login/loginUI";
		}
		if(StringUtil.isEmpty(${username})||StringUtil.isEmpty(${password})){
			request.setAttribute("message","用户名或密码不能为空");
			return "login/loginUI";
		}
	
		${dbTable.entityName?cap_first} ${dbTable.entityName}=${dbTable.entityName}Service.findBy${username?cap_first}And${password?cap_first}(${username},${password});
		
		if(${dbTable.entityName} == null){
			request.setAttribute("message","用户名或密码错误");
			return "login/loginUI";
		}else{
			request.getSession().setAttribute("user",${dbTable.entityName});
		}
		
		return "redirect:index.action";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "public/index";
	}
	
	@RequestMapping("/securityCode")
	public String securityCode(){
		//如果开启Hard模式，可以不区分大小写
		//String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
		//获取默认难度和长度的验证码
		String securityCode = SecurityCodeGenarator.getSecurityCode();
		request.getSession().setAttribute("SESSION_SECURITY_CODE", securityCode);
		BufferedImage image = BufferedImageGenarator.createImage(securityCode);
		response.setContentType("image/jpeg");
		response.setDateHeader("expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		try {
			ImageIO.write(image,"jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/logout")
	public String logout(){
		request.getSession().removeAttribute("user");
		return "login/loginUI";
	}
}