package com.chippo.LoginWeb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.chippo.LoginWeb.entity.Products;

public interface ProductRepository extends MongoRepository<Products, Integer>{

}
