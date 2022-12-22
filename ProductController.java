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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.data.rest.exception.ProductNotFoundException;
import com.spring.data.rest.model.Product;
import com.spring.data.rest.repository.ProductRepository;
//----------------------------------------------------------------------------
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/product")
public class ProductController {

	
	@Autowired
	private ProductRepository productRepository;

/*------------------------------------------------------------------------------
 		GET 	 findAll product method
--------------------------------------------------------------------------------
*/
		@GetMapping
		public List<Product> getAllProducts(){
			return productRepository.findAll();
		}
/*------------------------------------------------------------------------------
 		GET 	SortedByPrice method
--------------------------------------------------------------------------------
*/
		@GetMapping("/sortbyprice")
	    public List<Product> getsortedByPrice(@RequestBody Product productdetails)  {
	        return productRepository.SortedByPrice();
	             
		}
/*------------------------------------------------------------------------------
 		GET 	SortedByName method
--------------------------------------------------------------------------------
*/
		@GetMapping("/sortbyname")
		    public List<Product> getSortedByName(@RequestBody Product productdetails)  {
		        return productRepository.SortedByName();
		             
		}
		
/*------------------------------------------------------------------------------
	 	POST 	 CREATE product method
--------------------------------------------------------------------------------
*/		
		
		@PostMapping("/add")
		public Product save(@RequestBody Product product) {
			return productRepository.save(product);
		}
		
		
/*------------------------------------------------------------------------------
	 	POST 	getProductById METHOD
--------------------------------------------------------------------------------
*/		
		
	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable  int id) throws ProductNotFoundException {
	        Product product = productRepository.findById(id)
	                .orElseThrow(() -> new ProductNotFoundException ("Product not exist with id :" + id));
	        
	        return ResponseEntity.ok(product);
	    }
	    
/*------------------------------------------------------------------------------
	 	POST 	getProductByname METHOD
--------------------------------------------------------------------------------
*/
	  
	    @GetMapping("/getbyname/{productName}")
	    public ResponseEntity<List<Product>> findProductByName(@PathVariable  String productName){
	    	  List<Product> products = productRepository.findProductByName(productName);

	    	    if ( products.isEmpty()) {
	    	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	    }
	    	    
	    	    return new ResponseEntity<>( products, HttpStatus.OK);
	    	  }
	    
/*------------------------------------------------------------------------------
	 	POST 	 updateProducts method
--------------------------------------------------------------------------------
*/
	    
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable  int id,@RequestBody Product productdetails) throws ProductNotFoundException  {
		
	     Product updateProduct = productRepository.findById(id)
	    		 .orElseThrow(() -> new ProductNotFoundException("Product not exist with id : " + id));
	      
	     	updateProduct.setName(productdetails.getName());
		
	     	productRepository.save(updateProduct);
	     
	     	return ResponseEntity.ok(updateProduct);
	    
	    }
/*------------------------------------------------------------------------------
	 	delete productByid	 method
--------------------------------------------------------------------------------
*/    
	   
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<HttpStatus> deleteByProductId(@PathVariable int id) throws ProductNotFoundException {

	        Product product =  productRepository.findById(id)
	                .orElseThrow(() -> new ProductNotFoundException ("Product not exist with id : " + id));

	        productRepository.delete(product);

	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	    }	
//----------------------------------------------------------------------------------
}
