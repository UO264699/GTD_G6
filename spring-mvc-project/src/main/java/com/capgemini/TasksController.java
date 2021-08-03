package com.capgemini;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
		
		List<Task> tasks = tasksService.listTasks();
		
		
		model.addAttribute("tasks",tasks);
		
		return "tasks";
		
	}
	
	@RequestMapping(value = "/tasks/todayList")
	public String getTodayTasks(Model model) throws SQLException {
		
		List<Task> tasks = tasksService.listTasks();
		
		List<Task> todayTasks = new ArrayList<Task>();
		
		tasks.forEach((t)-> {
			
			if(t.getPlanned().before(new Date()) || t.getPlanned().getTime() == new Date().getTime())
				todayTasks.add(t);
			
		});
		
		Collections.sort(todayTasks, new Comparator<Task>(){
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getCreated().compareTo(o2.getCreated());
            }
        });
		
	
		
		model.addAttribute("todayList",todayTasks);
		
		return "tasks";
		
	}
	
	
}
