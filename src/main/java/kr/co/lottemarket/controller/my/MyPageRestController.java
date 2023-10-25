package kr.co.lottemarket.controller.my;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.service.mypage.myPageOrderService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class MyPageRestController {
	
	@Autowired
	private myPageOrderService service;
	
	@GetMapping("/my/point/{ordNo}")
	public int complete(@PathVariable("ordNo") int ordNo) {
		int result = service.OrderCompleteUpdate(ordNo);
		
		
		return result;
	}
	public void review(@RequestBody Map<String, String> data) {
		
	}
}
