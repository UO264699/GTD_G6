package com.capgemini.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.model.Task;
import com.capgemini.model.User;
import com.capgemini.services.CategoriesService;
import com.capgemini.services.TasksService;

@Controller
public class TasksController {
	
	@Autowired
	private TasksService tasksService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	
	@RequestMapping(value = "tasks/add", method = RequestMethod.POST)
	public String addTask(Task task, HttpSession httpSession)  {
		
		
		User user = (User) httpSession.getAttribute("user") ;
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		task.setUser_id(user.getId());
		
		tasksService.addTask(task);
		
		
		return "redirect:/tasks/list";
		
	}
	
	
	@RequestMapping(value = "/tasks/list")
	public String getTasks(Model model, HttpSession httpSession)  {
		
		
		User user = (User) httpSession.getAttribute("user");
		
		if(user == null) {
			
			return "redirect:/login";
		}

	
		int id = user.getId();
		
		model.addAttribute("todayTasks",tasksService.listTodayTasks(id));
		
		model.addAttribute("tasks",tasksService.listTasks(id));
		
		model.addAttribute("finishedTasks",tasksService.listFinishedTasks(id));
		
		model.addAttribute("categories",categoriesService.getCategories(id));
		
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
