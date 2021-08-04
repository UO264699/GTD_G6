package com.capgemini;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.model.Task;
import com.capgemini.services.TasksService;

@Controller
public class TasksController {
	
	@Autowired
	private TasksService tasksService;
	


	@RequestMapping(value = "tasks/add/{userid}/{categoryid}", method = RequestMethod.POST)
	public String addTask(Task task,@PathVariable int userid,@PathVariable int categoryid) throws SQLException {
		
		
		tasksService.addTask(task, userid, categoryid);
		
		
		return "redirect:/tasks/list";
		
	}
	
	
	@RequestMapping(value = "/tasks/list")
	public String getTasks(Model model) throws SQLException {
	
		
		model.addAttribute("todayTasks",tasksService.listTodayTasks(2));
		
		model.addAttribute("tasks",tasksService.listTasks(2));
		
		model.addAttribute("finishedTasks",tasksService.listFinishedTasks(2));
		
		return "tasksList";
		
	}
	
	@RequestMapping(value = "tasks/finish/{id}")
	public String finishTask(@PathVariable int id) throws SQLException {
		
		
		tasksService.finishTask(id);
		
		
		return "redirect:/tasks/list";
		
	}
	
	
	
}
