package kr.co.lottemarket.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lottemarket.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/product/cart")
@RestController
public class ProductCartController {
	@Autowired
	ProductService prodService;
	
	@DeleteMapping("/{cartNos}")
	public void deleteCart(@RequestBody List<Integer> cartNos) {
		log.info("DeleteCart = " + cartNos);
		prodService.deleteProductByCart(cartNos);
	}
}
