package kr.co.lottemarket.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.lottemarket.dto.PageRequestDTO;
import kr.co.lottemarket.dto.PageResponseDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.service.admin.AdminService;
import kr.co.lottemarket.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ProductController {
	
	private final ProductService service;
	private final AdminService adminservice;

	@GetMapping("/productList")
	public String productList(Model model,PageRequestDTO pageRequestDTO){
		
		PageResponseDTO list = service.productList(pageRequestDTO);
		model.addAttribute("list",list);
		
		return "/product/productList";
	}
	
	
	@GetMapping("/productView")
	public String productView(Model model,ProductDTO dto) {
		
		ProductDTO proddto = service.selectProd(dto);
		model.addAttribute("dto",proddto);
		
		return "/product/productView";
	}
	
	@GetMapping("/productSearch")
	public String productSearch(Admin_ProductPageRequestDTO pageRequestDTO, Model model, String chk , String search) {
		
	    Admin_ProductPageResponseDTO pageResponseDTO = null;
	    pageResponseDTO = service.selectProdNameProucts(pageRequestDTO,chk,search);
	    model.addAttribute("pageResponse", pageResponseDTO);

	    return "/product/productSearch";
	}
	
	
	
}
