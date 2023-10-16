package kr.co.lottemarket.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.service.admin.AdminService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class Admin_ProductController {
	
	@Autowired
	private AdminService adminservice;
	
	@Autowired
    private HttpServletRequest request;
	
	@GetMapping("/admin/layout/product/productlist")
	public String productlist(Admin_ProductPageRequestDTO pageRequestDTO, Model model) {
		
		Admin_ProductPageResponseDTO pageResponseDTO = adminservice.selectProucts(pageRequestDTO);
		log.info("pageResponseDTO"+pageResponseDTO);
		model.addAttribute("pageResponse", pageResponseDTO);

		return "/admin/layout/product/productlist";
		
	}
	
	@GetMapping("/admin/layout/product/register")
	public String register() {
		
		return "/admin/layout/product/register";
	}
	
	@PostMapping("/admin/layout/product/register")
	public String register(ProductDTO dto) {
		
		String clientIP = request.getRemoteAddr();
		dto.setIp(clientIP);
		
		log.info("dto : " + dto.toString());
		
		adminservice.insertProduct(dto);
		
		log.info("adminservice : " + adminservice.toString());
		
		return "/admin/layout/product/register";
	}
	
	
	
}
