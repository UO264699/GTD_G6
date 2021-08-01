package com.capgemini;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.model.User;
import com.capgemini.services.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	private UsersService usersService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale,Model model) throws SQLException {
		
		Date date = new Date();
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG,locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime",formattedDate);
		
		return "home";
	}
	
}
