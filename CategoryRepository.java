package com.spring.data.rest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.data.rest.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public Category getByName(String name);
	
	
	@Query(value = "SELECT * FROM Categories  WHERE category_name like 'Bevrages'",nativeQuery=true)
	public List<Category> getByNames(String name);
	
	@Query(value = "SELECT cate FROM Category cate ORDER BY name")
	public List<Category> sortedByName();
	
	@Query("from Category" )
	List<Category> findCategories(Pageable pageable);
	
}
