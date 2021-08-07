package com.capgemini.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.model.Task;
import com.capgemini.model.User;
import com.capgemini.services.CategoriesService;
import com.capgemini.services.TasksService;
import com.capgemini.validators.TaskValidator;

@Controller
public class TasksController {
	
	@Autowired
	private TasksService tasksService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private TaskValidator taskValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(taskValidator);
	}
	
	/**
	 * 
	 * M�todo que a�ade una tarea
	 * 
	 * @param task Tarea a a�adir
	 * @param result Resultado de las validaciones
	 * @param httpSession Sesi�n actual del usuario autenticado
	 * @return vistas del listado de tareas
	 */
	@RequestMapping(value = "tasks/add", method = RequestMethod.POST)
	public String addTask(@Validated Task task,BindingResult result,HttpSession httpSession)  {
		
		if (result.hasErrors()) {																																									
			return "redirect:/tasks/list";
		}
		
		
		User user = (User) httpSession.getAttribute("user") ;
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		task.setUser_id(user.getId());
		
		tasksService.addTask(task);
		
		
		return "redirect:/tasks/list";
		
	}
	
	/**
	 * 
	 * M�todo que devuelve la vista del listado de tareas del usuario
	 * en sesi�n.
	 * 
	 * 
	 * @param model
	 * @param httpSession Sesi�n actual del usuario autenticado
	 * @return las vistas del listado de tareas
	 */
	@RequestMapping(value = "/tasks/list")
	public String getTasks(Model model, HttpSession httpSession)  {
		
		model.addAttribute("task", new Task());
		
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
	
	/**
	 * 
	 * M�todo que finaliza la tarea cuyo id se pasa por par�metro
	 * en la url.
	 * 
	 * @param id
	 * @param httpSession
	 * @return las vistas del listado de tareas
	 */
	@RequestMapping(value = "tasks/finish/{id}")
	public String finishTask(@PathVariable int id,HttpSession httpSession)  {
		
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		
		tasksService.finishTask(id);
		
		return "redirect:/tasks/list";
		
	}
	
	/**
	 * 
	 * M�todo que edita una tarea 
	 * 
	 * @param task Tarea editada
	 * @param datePlan Nueva fecha planeada
	 * @return
	 */
	@RequestMapping(value = "/tasks/editTask")
	public String editTask(Task task,String datePlan)  {
		
		tasksService.editTask(task, datePlan);
		
		return "redirect:/tasks/list";
		
	}
	
	
}
