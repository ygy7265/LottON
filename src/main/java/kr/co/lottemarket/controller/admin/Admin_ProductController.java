package kr.co.lottemarket.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.entity.product.ProductEntity;
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
	public String productlist(Admin_ProductPageRequestDTO pageRequestDTO, Model model, String searchCate , String search) {
	    Admin_ProductPageResponseDTO pageResponseDTO = null;
	    
	    log.info("searchCate" , searchCate);
	    if (searchCate != null) {
	        switch (searchCate) {
	            case "prodName":
	                pageResponseDTO = adminservice.selectProdNameProucts(pageRequestDTO, search);
	                break;
	            case "prodNo":
	                pageResponseDTO = adminservice.selectProdNoProucts(pageRequestDTO, Integer.parseInt(search));
	                log.info("search : " + search);
	                break;
	            case "company":
	                pageResponseDTO = adminservice.selectCompanyProucts(pageRequestDTO, search);
	                break;
	            case "seller":
	                pageResponseDTO = adminservice.selectSellerProucts(pageRequestDTO, search);
	                break;
	        }
	        
	    } else {
	    	
	        pageResponseDTO = adminservice.selectProucts(pageRequestDTO);
	        
	    }

	    log.info("pageResponseDTO: " + pageResponseDTO);
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
	
	@DeleteMapping("/admin/layout/product/productDelete/{prodNo}")
	@Transactional
	public void deleteProduct(@PathVariable("prodNo") int no) {
		
	    log.info("no :" + no);
	    adminservice.deleteProduct(no);

	}
	
	@DeleteMapping("/admin/layout/product/selectProductDelete")
	@Transactional
	public void deleteProduct(@RequestBody List<String> checkBoxArr) {
	    for (String prodNoValue : checkBoxArr) {
	        // prodNoValue를 정수로 파싱하여 상품 번호를 얻고 삭제 작업 수행
	        int no = Integer.parseInt(prodNoValue);
	        log.info("no: " + no);
	        adminservice.deleteProduct(no);
	    }
	}
	
}
