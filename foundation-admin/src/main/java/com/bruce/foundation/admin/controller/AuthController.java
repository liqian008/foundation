package com.bruce.foundation.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.foundation.admin.service.security.AdminResourceService;
//import nl.captcha.Captcha;
//import nl.captcha.backgrounds.TransparentBackgroundProducer;
//import nl.captcha.gimpy.DropShadowGimpyRenderer;
//import nl.captcha.servlet.CaptchaServletUtil;
//import nl.captcha.text.renderer.DefaultWordRenderer;
//import nl.captcha.text.renderer.WordRenderer; 

@Controller
public class AuthController{

	private static Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
    private AdminResourceService adminResourceService;
	
	@RequestMapping(value = {"/login"})
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = { "/authorizeFailed" })
	public String authorizeFailed() {
		return "authorize_failed";
	}

//	@RequestMapping("/getVerifyCode")
//	public void getVerifyMCode(Model model,HttpServletRequest request,HttpServletResponse response) {
//		
//		List<Color> colors = new ArrayList<Color>();
//		colors.add(Color.GREEN);
//		colors.add(Color.BLUE);
//		colors.add(Color.ORANGE);
//		colors.add(Color.RED);
//		
//		List<Font> fonts = new ArrayList<Font>();
//		fonts.add(new Font("Geneva", 2, 32));
//		fonts.add(new Font("Courier", 3, 32));
//		fonts.add(new Font("Arial", 1, 32));
//	    
//		//WordRenderer wordRenderer = new ColoredEdgesWordRenderer(colors, fonts);
//		WordRenderer wordRenderer = new DefaultWordRenderer();
//
//		Captcha captcha = new Captcha.Builder(150, 50).addText(wordRenderer).gimp(new DropShadowGimpyRenderer())
//				.addBackground(new TransparentBackgroundProducer()).build();
//		request.getSession().setAttribute("verifyCode", captcha.getAnswer());
//		CaptchaServletUtil.writeImage(response, captcha.getImage());
//	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "redirect:/j_spring_security_logout";
		//return "logout";
	}
	
	@RequestMapping("/accessDenied")
	public String accessDenied(){
		return "common/not_permission";
	}
	
}
