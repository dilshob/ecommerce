package dil.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dil.ecommerce.entity.Product;
import dil.ecommerce.repository.ProductRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/all")
	public List<Product> getAllProducts() {
		System.out.println("getAllProducts");
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Product> getProductById(@PathVariable("id") int id) {
		System.out.println("getProductById");
		return productRepository.findById(id);
	}

	@GetMapping("/name/{name}")
	public List<Product> getProductByName(@PathVariable("name") String name) {
		System.out.println("getProductByName");
		return productRepository.findByProductName(name);
	}

	@GetMapping("/search/{name}")
	public List<Product> getProductByNameLike(@PathVariable("name") String name) {
		System.out.println("getProductByNameLike" + name);
		return productRepository.findByProductNameLike(name);
	}

	@GetMapping("/	/{catogory}")
	public List<Product> getProductByCategory(@PathVariable("catogory") String catogory) {
		System.out.println("getProductById");
		return productRepository.findByCategory(catogory);
	}

	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}

}
