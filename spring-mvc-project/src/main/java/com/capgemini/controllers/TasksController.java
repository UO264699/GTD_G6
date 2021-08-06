package com.capgemini.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.model.Task;
import com.capgemini.services.CategoriesService;
import com.capgemini.services.TasksService;

@Controller
public class TasksController {
	
	@Autowired
	private TasksService tasksService;
	
	@Autowired
	private CategoriesService categoriesService;
	

	@RequestMapping(value = "tasks/add/{userid}/{categoryid}", method = RequestMethod.POST)
	public String addTask(Task task,@PathVariable int userid,@PathVariable int categoryid, HttpSession httpSession) throws SQLException {
		
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		
		tasksService.addTask(task, userid, categoryid);
		
		
		return "redirect:/tasks/list";
		
	}
	
	
	@RequestMapping(value = "/tasks/list")
	public String getTasks(Model model, HttpSession httpSession)  {
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

	
		
		model.addAttribute("todayTasks",tasksService.listTodayTasks(38));
		
		model.addAttribute("tasks",tasksService.listTasks(38));
		
		model.addAttribute("finishedTasks",tasksService.listFinishedTasks(38));
		
		model.addAttribute("categories",categoriesService.getCategories());
		
		return "tasksList";
		
	}
	
	@RequestMapping(value = "tasks/finish/{id}")
	public String finishTask(@PathVariable int id,HttpSession httpSession)  {
		
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		
		tasksService.finishTask(id);
		
		return "redirect:/tasks/list";
		
	}
	
	
	@RequestMapping(value = "/tasks/editTask")
	public String editTask(Task task,String datePlan)  {
		
		tasksService.editTask(task, datePlan);
		
		return "redirect:/tasks/list";
		
	}
	
	
}
