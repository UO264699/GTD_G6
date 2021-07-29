package com.capgemini;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.model.User;

@Controller
public class HomeController {

	@RequestMapping(value = "/hola")
	public ModelAndView saludo() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("titulo","Bienvenido a Spring MVC");
		mv.addObject("mensaje","Arrancando mi primera aplicación Spring MVC");
		mv.setViewName("saludo");
		
		
		return mv;
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale,Model model) {
		
		Date date = new Date();
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG,locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime",formattedDate);
		
		return "home";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(User user,Model model) {
		
		model.addAttribute("userName",user.getUserName());
		
		return "user";
		
	}
	
	
}
