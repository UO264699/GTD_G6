package com.capgemini.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Category;
import com.capgemini.model.Task;
import com.capgemini.persistence.CategoriesRepository;
import com.capgemini.persistence.TasksRepository;
import com.capgemini.persistence.dto.TaskDto;


@Service
public class TasksService {
	
	@Autowired
	private TasksRepository tasksRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;

	public void deleteTask(int id)  {
		
		tasksRepository.delete(id);
	}
	
	/**
	 * 
	 * Añade una tarea 
	 * 
	 * @param t Tarea a añadir
	 */
	public void addTask(Task t)  {
		
		TaskDto taskDto = new TaskDto();
		
		taskDto.id = t.getId();
		taskDto.title = t.getTitle();
		taskDto.userId = t.getUser_id();
		taskDto.categoryId = t.getCategory_id();
		
		if(taskDto.categoryId <= 0)
			
			taskDto.categoryId = categoriesRepository.findbyname("Inbox").id;
	
		
	    tasksRepository.add(taskDto);
		
	}
	
	/**
	 * 
	 * Lista todas las tareas del usuario
	 * 
	 * @return listado de todas las tareas del usuario
	 * 
	 */
	public List<Task> listTasks(int id)  {
		
		List<Task> tasks = new ArrayList<Task>();
		List<Object> tasks1 = this.tasksRepository.findByUserId(id);
		
		for(Object t : tasks1) {
			
			TaskDto tdto = (TaskDto) t;
			
			
			Task task = new Task(tdto.id, tdto.title,tdto.comments,tdto.created,tdto.planned,tdto.finished,tdto.userId,tdto.categoryId);
			
			tasks.add(task);
		}
	
		
		return tasks;
	}
	
	
	/**
	 * 
	 * Lista las tareas de hoy y las retrasadas
	 * 
	 * 
	 * @return listado de las tareas de hoy y las retrasadas
	 * 
	 */
	
	public List<Task> listTodayTasks(int id)  {
		
		List<Task> tasks = listTasks(id);
		
		List<Task> todayTasks = new ArrayList<Task>();
		
		tasks.forEach((t)-> {
			
			if(t.getPlanned()!=null && (t.getPlanned().before(new Date()) || t.getPlanned().getTime() == new Date().getTime()) && t.getFinished() == null )
				todayTasks.add(t);
			
		});
		
		Collections.sort(todayTasks, new Comparator<Task>(){
	        @Override
	        public int compare(Task o1, Task o2) {
	            return o1.getPlanned().compareTo(o2.getPlanned());
	        }
	    });
		return todayTasks;
		
	}
	
	/**
	 * 
	 * Lista todas las tareas finalizadas
	 * 
	 * @return Listado de todas las tareas finalizadas
	 */
	public List<Task> listFinishedTasks(int id)  {
		
		List<Task> tasks = listTasks(id);
		
		List<Task> finishedTasks = new ArrayList<Task>();
		
		tasks.forEach((t)-> {
			
			if(t.getFinished() != null)
				finishedTasks.add(t);
			
		});
		
		return finishedTasks;
		
	}
	
	/**
	 * 
	 * Lista las tareas de una categoría determinada cuyo id se pasa por parámetro
	 * 
	 * 
	 * @param id id del usuario en sesión
	 * @param categoryid id de la categoría
	 * @return Listado de las tareas de una categoría determinada.
	 */
	public List<Task> listTaskByCategory(int id, int categoryid)  {
		
		List<Task> tasks = listTasks(id);
		
		List<Task> categoryTasks = new ArrayList<Task>();
		
		tasks.forEach((t)-> {
			
			if(t.getCategory_id() == categoryid)
				categoryTasks.add(t);
			
		});
		
		return categoryTasks;
		
	}
	
	/**
	 * 
	 * Obtiene una lista de las listas de tareas por cada categoría
	 * 
	 * @param id id del usuario en sesión
	 * @return Lista de las listas de tareas por cada categoría
	 */
	public List<List<Task>> getTaskByCategory(int id) {
		
		List<List<Task>>  tasksByCategory = new ArrayList<List<Task>>();
		
		List<Object> categories = this.categoriesRepository.findAll();
		
		for(Object category:categories) {
			
			List<Task> tasks = listTaskByCategory(((Category) category).getId(),id );
			
			tasksByCategory.add(tasks);
		}
			
			
		
		
		return tasksByCategory;
		
		
		
	}
	
	/**
	 * 
	 * Marca una tarea como finalizada
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void finishTask(int id)  {
		
		tasksRepository.updateFinished(id);
		
	}
	
	public void editTask(Task task,String datePlan) {	
		
		TaskDto t = new TaskDto();
		
		TaskDto beforeTask = tasksRepository.findById(task.getId());
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date fecha = formato.parse(datePlan);
			task.setPlanned(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		t.id = task.getId();
		t.title = task.getTitle();
		t.planned = task.getPlanned();
		t.comments = task.getComments();
		
		
		comprobarCamposVacios(t, beforeTask);	
		
		this.tasksRepository.updateTask(t);
	}
	
	public void comprobarCamposVacios(TaskDto t, TaskDto t2) {
		
		if(t.planned == null) {
			
			t.planned = t2.planned;
			
		}
		
		if(t.comments == null) {
			
			t.comments = t2.comments;
			
		}
		
		if(t.title == null) {
			
			t.title = t2.title;
		}
	}

}
