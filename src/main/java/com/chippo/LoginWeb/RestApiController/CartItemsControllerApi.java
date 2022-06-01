package com.chippo.LoginWeb.RestApiController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chippo.LoginWeb.entity.Products;
import com.chippo.LoginWeb.model.CartItemsDTO;
import com.chippo.LoginWeb.model.ProductsDTO;
import com.chippo.LoginWeb.service.ProductsRepositoryService;

@RestController
public class CartItemsControllerApi {

	@Autowired
	ProductsRepositoryService repositoryService;
	
	@GetMapping("/add-cartItem")
	public ProductsDTO addCartItems(HttpSession httpSession, @RequestBody ProductsDTO dto, @RequestParam(name = "idPro") int idPro) {
		
		ProductsDTO productsDTO = repositoryService.getProductsById(dto.getId());
		
		if (productsDTO!=null) {
			Object object = httpSession.getAttribute("cart");
			if (object == null) {
				CartItemsDTO cartItemsDTO = new CartItemsDTO();
				cartItemsDTO.setNameProducts(dto.getNameProduct());
				//cartItemsDTO.setSize(dto.getPropertyProps());
			}
		}
		
		return null;
		
	}
}
