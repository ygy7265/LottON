package kr.co.lottemarket.controller.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;

import jakarta.persistence.Entity;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.entity.UserEntity;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.service.product.ProductOrderItemService;
import lombok.extern.log4j.Log4j2;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Log4j2
@Controller
public class ProductOrderController {
	
	@Autowired
	private ProductOrderItemService itemService;
	
	
	@GetMapping("/product/productOrder")
	public String productOrder(Model model,String uid) {
		List<ProductOrderItemDTO> dto= itemService.selectOrderItem(uid);
		model.addAttribute("dto",dto);
		return "/product/productOrder";
	}
	
	@PostMapping("/product/productOrder")
	public String productOrderPost(@RequestParam("productOrderItemEntity") String productOrderItemEntity) {
		itemService.insertOrder(productOrderItemEntity);
		return "redirect:/product/productOrder";
	}
}
