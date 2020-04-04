package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bean.Product;
import com.app.repo.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "ProductRestController", description = "REST Apis related to Product Entity!!!!")
@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping(value = "products")
	@ApiOperation(value = "Get list of Product in the System ", response = Iterable.class, tags = "Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<List<Product>> getProductList() {
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok().body(products);

	}

	@ApiOperation(value = "Get specific Product in the System ", response = Product.class, tags = "Product")
	@GetMapping(value = "products/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") Integer id) {
		Optional<Product> product = productRepository.findById(id);
		return ResponseEntity.ok(product);
	}

	@ApiOperation(value = "Add Product in the System ", response = Iterable.class, tags = "Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping(value = "products/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		System.out.println(
				product.getId() + "\t" + product.getName() + "\t" + product.getQuantity() + "\t" + product.getPrice());
		productRepository.save(product);
		return ResponseEntity.ok("Proudct Saved");
	}

	@ApiOperation(value = "Update Product in the System ", response = Iterable.class, tags = "Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping(value = "products/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		productRepository.save(product);
		return ResponseEntity.ok("Proudct Updated");
	}

	@ApiOperation(value = "Delete specific Product in the System ", response = Product.class, tags = "Product")
	@DeleteMapping(value = "products/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok("Proudct deleted");
	}

}
