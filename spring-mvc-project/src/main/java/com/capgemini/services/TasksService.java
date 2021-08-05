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
	
	public void addTask(Task t,int userid, int categoryid)  {
		
		TaskDto taskDto = new TaskDto();
		
		taskDto.id = t.getId();
		taskDto.title = t.getTitle();
		taskDto.userId = t.getUser_id();
		taskDto.categoryId = categoryid;
		taskDto.userId = userid;
		
	    tasksRepository.add(taskDto);
		
	}
	
	/**
	 * 
	 * Lista todas las tareas del usuario
	 * 
	 * @return
	 * @throws SQLException
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
	 * @return
	 * @throws SQLException
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
	 * @return
	 * @throws SQLException
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
	
	public List<Task> listTaskByCategory(int id, int categoryid)  {
		
		List<Task> tasks = listTasks(id);
		
		List<Task> categoryTasks = new ArrayList<Task>();
		
		tasks.forEach((t)-> {
			
			if(t.getCategory_id() == categoryid)
				categoryTasks.add(t);
			
		});
		
		return categoryTasks;
		
	}
	
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
		
		this.tasksRepository.updateTask(t);
	}

}
