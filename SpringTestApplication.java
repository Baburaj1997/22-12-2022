package com.spring.data.rest.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.data.rest.controller.CategoryController;
import com.spring.data.rest.controller.ProductController;
import com.spring.data.rest.model.Category;
import com.spring.data.rest.model.Product;

@SpringBootApplication
public class SpringTestApplication implements CommandLineRunner {
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private CategoryController categoryController;

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("***********************************************************************");
		System.out.println("      welcome to my spring test       ");
		System.out.println("***********************************************************************");
		
		
		System.out.println("        Products Records        ");
		System.out.println("***********************************************************************");
		addProducts(createProducts());
		
		
		System.out.println("        Categories Records         ");
		System.out.println("**********************************************************************");
		addCategories(createCategories());	
	}
	
	
	
	//for category...
	
	private void addCategories(List<Category> categories) {
		for(Category category : categories) {
			categoryController.save(category);
		}
	}
	private List<Category> createCategories(){
		Category category1 = new Category("Dairy Products","Soft drinks, coffees, teas, beers, and ales",null);
		Category category2 = new Category("Grains/Cereals","Sweet and savory sauces, relishes, spreads, and seasonings",null);
		Category category3 = new Category("Produce","Desserts, candies, and sweet breads",null);
		
		List<Category> categories = new ArrayList<>();
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		return categories;
	}
	
	
	
	//for product...
	private void addProducts(List<Product> products ) {
		for(Product product : products) {
			productController.save(product);
		}
	}
	private List<Product> createProducts(){
		Product product1 = new Product(1,"Chai",15,29,false,null);
		Product product2 = new Product(2,"Chang",19,17,true,null);
		Product product3 = new Product(3,"Aniseed Syrup",50,15,false,null);
	
		
		List<Product> products= new ArrayList<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		return products;
	
	
	
	
	
	}	
}
