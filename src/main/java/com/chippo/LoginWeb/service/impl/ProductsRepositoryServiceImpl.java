package com.chippo.LoginWeb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chippo.LoginWeb.entity.Products;
import com.chippo.LoginWeb.model.ProductsDTO;
import com.chippo.LoginWeb.repository.ProductRepository;
import com.chippo.LoginWeb.service.ProductsRepositoryService;

@Service
public class ProductsRepositoryServiceImpl implements ProductsRepositoryService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public void add(ProductsDTO productsDTO) {
//		List<String> listImg = new ArrayList<String>();
//		listImg.add(productsDTO.getFileName());
		Products products = new Products();
		products.setId(productsDTO.getId()+generateRandomIntIntRange(0, 99));
		products.setnameProduct(productsDTO.getNameProduct());
		products.setPrice(productsDTO.getPrice());
		products.setFileName(productsDTO.getLinks());
//		products.setFileName(productsDTO.getFileName());

		productRepository.save(products);
		productsDTO.setId(products.getId());
	}

	@Override
	public void edit(ProductsDTO productsDTO) {

		Products products = productRepository.findById(productsDTO.getId()).orElse(null);
		if (products != null) {
			products.setId(productsDTO.getId());
			products.setnameProduct(productsDTO.getNameProduct());
			products.setPrice(productsDTO.getPrice());
//			products.setFileName(productsDTO.getFileName());

			productRepository.save(products);
		}
	}

	@Override
	public void delete(int id) {
//		ServiceResult serviceResult = new ServiceResult();
		Products products = productRepository.findById(id).orElse(null);
		if (products != null) {
			productRepository.delete(products);
		}
	}

	@Override
	public List<ProductsDTO> getAll() {
		List<ProductsDTO> productsDTOs = new ArrayList<ProductsDTO>();
		List<Products> products = productRepository.findAll();

		for (Products product : products) {
			ProductsDTO dto = new ProductsDTO();
			dto.setId(product.getId());
			dto.setNameProduct(product.getnameProduct());
			dto.setPrice(product.getPrice());
//			dto.setFileName(product.getFileName());

			productsDTOs.add(dto);
		}
		return productsDTOs;
	}

	@Override
	public ProductsDTO getProductsById(int id) {
		Products products = productRepository.findById(id).orElse(null);
		if (products != null) {
			ProductsDTO productsDTO = new ProductsDTO();
			productsDTO.setId(products.getId());
			productsDTO.setNameProduct(products.getnameProduct());
			productsDTO.setPrice(products.getPrice());
//			productsDTO.setFileName(products.getFileName());
			productsDTO.setLinks(products.getFileName());
			
			return productsDTO;
		}
		return null;
	}
	public static int generateRandomIntIntRange(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
}
