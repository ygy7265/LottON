package kr.co.lottemarket.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.lottemarket.dto.PageRequestDTO;
import kr.co.lottemarket.dto.PageResponseDTO;
import kr.co.lottemarket.dto.product.ProductCartDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.service.ProductService;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
	@GetMapping("/product/productCart")
	public String productCart(Model model)	{
		List<ProductCartDTO> dto= service.selectCartItems("seller1");
		log.info("dto = ="+ dto.toString());
		model.addAttribute("dto",dto);
		return "/product/productCart";
	}
	@PostMapping("/product/productCart")
	public String productCartPost(ProductCartDTO dto)	{
		dto.setTotal(dto.getCount() * dto.getPrice());
		log.info("dto ==" + dto);
		service.insertDTO(dto);
		return "redirect:/product/productCart";
	}
	
	@GetMapping("/product/productOrder")
	public String productOrder()	{
		return "/product/productOrder";
	}
	@PostMapping("/product/productOrder")
	public String productOrderPost(ProductOrderItemDTO dto)	{
		dto.setTotal(dto.getCount() * dto.getPrice());
		service.insertDTOBuy(dto);
		return "redirect:/product/productOrder";
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
