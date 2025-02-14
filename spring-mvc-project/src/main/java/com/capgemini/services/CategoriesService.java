package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.persistence.CategoriesRepository;
import com.capgemini.persistence.TasksRepository;
import com.capgemini.persistence.dto.CategoryDto;

/**
 * 
 * Clase que representa el servicio de Categor�as con todos los m�todos
 * relacionados con las categor�as.
 * 
 * 
 * @author andrefer
 *
 */
@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private TasksRepository tasksRepository;

    /**
     * 
     * Elimina la categor�a cuyo id se pasa por par�metro
     * 
     * 
     * @param id id de la categor�a a eliminar
     */
	public void deleteCategoryByUserId(int id)  {
		categoriesRepository.deleteByUserId(id);
	}

	/**
	 * 
	 * Obtiene un listado de todas las categor�as del sistema
	 * 
	 * @return listado de todas las categor�as del sistema
	 */
	public List<Category> getCategories(int userid) {

		List<Object> categories = categoriesRepository.findbyUserid(userid);
		List<Category> categories1 = new ArrayList<>();

		for(Object c : categories) {
			CategoryDto category = (CategoryDto) c ;

			Category category2 = new Category(category.id, category.name, category.user_id);

			categories1.add(category2);
		}
		return categories1;
	}
	
	/**
	 * A�ade una categor�a
	 * 
	 * 
	 * @param category categor�a a a�adir
	 */
	public void addCategory(Category category)  {
		
		CategoryDto categoryDto = new CategoryDto();
		
		categoryDto.user_id = category.getUser_id();
		categoryDto.name = category.getName();
		categoryDto.id = category.getId();
		
		this.categoriesRepository.add(categoryDto);
		
	}
	
	/**
	 * Borra una categor�a del sistema
	 * 
	 * 
	 * @param id id de la categor�a a borrar
	 */
	public void deleteCategory(int id)  {
		this.tasksRepository.deleteByCategoryId(id);
		this.categoriesRepository.delete(id);
		
		
	}
	
	/**
	 * M�todo que edita una categor�a del sistema
	 * 
	 * 
	 * @param id id de la categor�a a borrar
	 */
	public void editCategorie(Category category) {	
		this.categoriesRepository.updateCategory(category.getId(), category.getName());
	}
	
	/**
	 * Obtiene una categor�a del sistema
	 * 
	 * 
	 * @param id id de la categor�a a borrar
	 */
	public Category getCategoryByName(String name) {
		
		CategoryDto categoryDto = categoriesRepository.findbyname(name);
		
		Category category = new Category(categoryDto.id, categoryDto.name, categoryDto.user_id);
		
		return category;
	}

}
