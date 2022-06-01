package com.chippo.LoginWeb.RestApiController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.chippo.LoginWeb.crawl.Crawl;
import com.chippo.LoginWeb.crawl.CrawlProperty;
import com.chippo.LoginWeb.crawl.Encode;
import com.chippo.LoginWeb.entity.Products;
import com.chippo.LoginWeb.model.ProductsDTO;
import com.chippo.LoginWeb.service.ProductsRepositoryService;

@RestController
public class ProductsContrllerApi {

	@Autowired
	ProductsRepositoryService productsService;

	@PostMapping(value = "/api/add-product")
	public ResponseEntity<ProductsDTO> addProducts(@RequestBody ProductsDTO productsDTO,
			UriComponentsBuilder ucBuilder) {

		if (productsDTO.getMultipartFile() != null && productsDTO.getMultipartFile().getSize() > 0) {
			String UPLOAD_LOCATION = "D:\\Anh";
			String fileName = productsDTO.getMultipartFile().getOriginalFilename();

			File file = new File(UPLOAD_LOCATION + File.separator + fileName);

			try {
				FileOutputStream fileOutputStream = new FileOutputStream(file);

				fileOutputStream.write(productsDTO.getMultipartFile().getBytes());

				productsDTO.setFileName(fileName);
			} catch (Exception e) {
				System.out.println("Xay ra loi" + e);
			}
		}

		productsService.add(productsDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/products/{id}").buildAndExpand(productsDTO.getId()).toUri());
		return new ResponseEntity<ProductsDTO>(headers, HttpStatus.CREATED);

	}

	@GetMapping("/api/chi-tiet-san-pham/{id}")
	public ResponseEntity<ProductsDTO> getUser(@PathVariable(name = "id") int id) {

		ProductsDTO dto = productsService.getProductsById(id);
		if (dto == null) {
			return new ResponseEntity<ProductsDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductsDTO>(dto, HttpStatus.OK);

	}

	@GetMapping(value = "/api/list-products", headers = "Accept=application/json")
	public List<ProductsDTO> getAllUser() {
		List<ProductsDTO> tasks = productsService.getAll();
		return tasks;

	}

	@PutMapping(value = "/api/edit-products", headers = "Accept=application/json")
	public ResponseEntity<ProductsDTO> updateUser(@RequestBody ProductsDTO productsDTO) {

		ProductsDTO products = productsService.getProductsById(productsDTO.getId());
		if (products == null) {
			return new ResponseEntity<ProductsDTO>(HttpStatus.NOT_FOUND);
		}
		productsService.edit(products);
		return new ResponseEntity<ProductsDTO>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/delete-products/{id}", headers = "Accept=application/json")
	public ResponseEntity<Products> deleteProducts(@PathVariable("id") int id) {
		ProductsDTO products = productsService.getProductsById(id);
		if (products == null) {
			return new ResponseEntity<Products>(HttpStatus.NOT_FOUND);
		}
		productsService.delete(id);
		return new ResponseEntity<Products>(HttpStatus.NO_CONTENT);
	}

//	@PostMapping(value = "/api/add-san-pham")
//	public ProductsDTO addProducts(
//			@RequestParam(name = "stringUrl") String Url,
//			UriComponentsBuilder ucBuilder) {
//		System.out.println(Url);
//		
//		System.out.println();
//		Crawl crawl = new Crawl();
//		
//		ProductsDTO dto = crawl.crawl(Url);
//		productsService.add(dto);
//		
//		return dto;
//	}
	@PostMapping(value = "/api/add-san-pham")
	public ProductsDTO addProducts(
			@RequestParam(name = "stringUrl") String Url,
			UriComponentsBuilder ucBuilder) {
	
		Crawl crawl = new Crawl();
		
		ProductsDTO dto = crawl.crawl(Url);
		productsService.add(dto);
		
		return dto;
	}
	
}
