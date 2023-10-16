package kr.co.lottemarket.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductDTO;

@Controller
public class Admin_ProductController {
	
	@Autowired
	private AdminService adminservice;
	
	@GetMapping("/admin/layout/product/productlist")
	public String productlist(Admin_ProductPageRequestDTO pageRequestDTO, Model model) {
		Admin_ProductPageResponseDTO pageResponseDTO = adminservice.selectProucts(pageRequestDTO);
		
		model.addAttribute("pageResponse", pageResponseDTO);

		
		return "/admin/layout/product/productlist";
	}
	
	@GetMapping("/admin/layout/product/register")
	public String register() {
		return "/admin/layout/product/register";
	}
	
}
