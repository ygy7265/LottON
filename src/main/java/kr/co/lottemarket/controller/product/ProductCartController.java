package kr.co.lottemarket.controller.product;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lottemarket.dto.product.ProductCartDTO;
import kr.co.lottemarket.entity.UserEntity;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/product")
@RestController
public class ProductCartController {
	@Autowired
	private ProductService service;
	
	@Autowired
	private ModelMapper modelmapper;
	//Cart
		
	
	@DeleteMapping("/{cartNos}")
	public void deleteCart(@PathVariable("cartNos") List<Integer> cartNos) {
		log.info("DeleteCart = " + cartNos);
		service.deleteProductByCart(cartNos);
	}
	

}
