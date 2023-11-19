package kr.co.lottemarket.controller.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;

import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.service.product.ProductOrderItemService;
import kr.co.lottemarket.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ProductOrderController {
	
	
	private final ProductOrderItemService itemService;
	
	@GetMapping("/productOrder")
	public String productOrder(HttpServletRequest request,Model model) {
		
		List<ProductOrderItemDTO> dto= itemService.selectOrderItem();
		UserEntity user = dto.get(0).getUser();
		Map<String, Integer> map = new HashMap<>();
		
		int price = 0;
		int discountprice = 0;
		int totalprice = 0;
		int delivery = 0;
		int count = 0;
		
		
		for(ProductOrderItemDTO dtoitem : dto) {
			
			int itemprice = dtoitem.getCount() * dtoitem.getPrice();
			price += itemprice;
			discountprice += itemprice * (dtoitem.getDiscount()/100.0);
			totalprice += itemprice - discountprice;
			delivery += dtoitem.getDelivery();
			count += dtoitem.getCount();	
			
		}
		
		int total =  price-discountprice+delivery;
		
		map.put("price", price);
		map.put("discountprice", discountprice);
		map.put("totalprice", totalprice);
		map.put("delivery", delivery);
		map.put("discounttotal", total);
		map.put("count", count);
		
		model.addAttribute("dto",dto);
		model.addAttribute("user",user);
		model.addAttribute("map",map);
		
		request.getSession().setAttribute("map", map);
		request.getSession().setAttribute("dto", dto);
		
		return "/product/productOrder";
	}
	
	@PostMapping("/productOrder")
	public String productOrderPost(@RequestParam("productOrderItemEntity") String productOrderItemEntity) {
		
		itemService.insertOrder(productOrderItemEntity);
		
		return "redirect:/product/productOrder";
	}
	@PostMapping("/productOrderBuy")
	public String productBuy(ProductOrderItemDTO dto) {
		itemService.saveOrderItem(dto);
		return "redirect:/product/productOrder";
	}
	
	@GetMapping("/productComplete")
	public String productOrderComplete(Model model, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<ProductOrderItemDTO> dtoList = (List<ProductOrderItemDTO>) request.getSession().getAttribute("dto");
		@SuppressWarnings("unchecked")
		Map<String, Integer> map = (Map<String, Integer>) request.getSession().getAttribute("map");
		
		ProductOrderDTO itemdto = (ProductOrderDTO) request.getSession().getAttribute("itemdto");
		
		int total = map.get("discounttotal") - itemdto.getOrdusedPoint();
		
		model.addAttribute("dtoList",dtoList);
		model.addAttribute("map",map);
		model.addAttribute("total", total);
		model.addAttribute("itemdto",itemdto);
		
		return "/product/productComplete";
	}
	
	@PostMapping("/productComplete")
	public String productOrderCompletePost(HttpServletRequest request,@ModelAttribute ProductOrderDTO itemdto) {
		
		List<ProductOrderItemDTO> dto= itemService.selectOrderItem();
		
		request.getSession().setAttribute("dto", dto);
		request.getSession().setAttribute("itemdto", itemdto);

		itemService.insertOrderComplete(dto,itemdto);
		
		return "redirect:/product/productComplete";
		
	}
	
	
}
