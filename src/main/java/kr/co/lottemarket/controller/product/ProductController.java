package kr.co.lottemarket.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.dto.PageRequestDTO;
import kr.co.lottemarket.dto.PageResponseDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.service.ProductService;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/product/productCart")
	public String productCart()	{
		return "/product/productCart";
	}
	
	@GetMapping("/product/productList")
	public String productList(Model model,PageRequestDTO pageRequestDTO){
		
		PageResponseDTO list = service.productList(pageRequestDTO);
		log.info("pageRequestDTO.... " + pageRequestDTO.toString());
		log.info("list.... " + list.toString());
		model.addAttribute("list",list);
		
		return "/product/productList";
	}
	
	
	@GetMapping("/product/productView")
	public String productView(Model model,ProductDTO dto) {
		 ProductDTO proddto = service.selectProd(dto);
		
		model.addAttribute("dto",proddto);
		return "/product/productView";
	}
	
	
}
