package com.spring.data.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.data.rest.exception.CategoryNotFoundException;
import com.spring.data.rest.model.Category;
import com.spring.data.rest.repository.CategoryRepository;

//----------------------------------------------------------------------------
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/Categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
/*------------------------------------------------------------------------------
 	GET 	 findAll Category method
--------------------------------------------------------------------------------
*/
	@GetMapping
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
/*------------------------------------------------------------------------------
 	POST 	 create or save Category method
--------------------------------------------------------------------------------
*/
	@PostMapping
	public Category save(@RequestBody Category category) {
		return categoryRepository.save(category);
	}
	
/*------------------------------------------------------------------------------
 	GET 	getSortedByName METHOD
--------------------------------------------------------------------------------
*/    
	 @GetMapping("/sortbyname")
	    public List<Category> SortedByName(@RequestBody Category category) {
	        return categoryRepository.sortedByName();
	             
	  }
/*------------------------------------------------------------------------------
 	GET 	getCategoryById METHOD
--------------------------------------------------------------------------------
*/	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Category> categorygetById(@PathVariable int id) throws CategoryNotFoundException {
		Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException ("Category not exist with id :" + id));
        
        return ResponseEntity.ok(category);
    }
/*------------------------------------------------------------------------------
 	GET 	getCategoryById METHOD
--------------------------------------------------------------------------------
*/		
	
	  @GetMapping("/getbyname/{name}")
	    public ResponseEntity<Category> getByName(@PathVariable  String name){
	    	Category category = categoryRepository.getByName(name);
		
	    	return ResponseEntity.ok(category);

	    }
	  
/*------------------------------------------------------------------------------
	 	delete productByid	 method
--------------------------------------------------------------------------------
*/ 
	  @DeleteMapping("{id}")
	    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id) throws CategoryNotFoundException {

	    	Category category =  categoryRepository.findById( id)
	                .orElseThrow(() -> new CategoryNotFoundException ("Category not exist with id : " + id));

	    	categoryRepository.delete(category);

	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	    }
//------------------------------------------------------------------------------
}
