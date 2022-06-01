package com.chippo.LoginWeb.service;

import java.util.List;

import com.chippo.LoginWeb.model.ProductsDTO;

public interface ProductsRepositoryService {

	void add(ProductsDTO productsDTO);

	void edit(ProductsDTO productsDTO);

	void delete(int id);

	ProductsDTO getProductsById(int id);

	List<ProductsDTO> getAll();
}
