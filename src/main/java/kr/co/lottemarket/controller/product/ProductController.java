package kr.co.lottemarket.controller.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.PageRequestDTO;
import kr.co.lottemarket.dto.product.PageResponseDTO;
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

	@GetMapping("/productList")
	public String productList(Model model,PageRequestDTO pageRequestDTO){
		log.info("type" + pageRequestDTO.getType());
		PageResponseDTO list = service.productList(pageRequestDTO);
		model.addAttribute("list",list);
		model.addAttribute("type",pageRequestDTO.getType());
		return "/product/productList";
	}
	
	
	@GetMapping("/productView")
	public String productView(Model model,ProductDTO dto) {
		
		ProductDTO proddto = service.selectProd(dto);
		 // 현재 날짜/시간
        Date rDate = new Date();

        //2일 추가
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        Date wDate = new Date(cal.getTimeInMillis());
        // 포맷팅 정의
        SimpleDateFormat formatter = new SimpleDateFormat("(E) MM/dd");

        // 포맷팅 적용
        String formatedNow = formatter.format(wDate);
        model.addAttribute("formatedNow",formatedNow);
	    model.addAttribute("dto",proddto);
		
		return "/product/productView";
	}
	
	@GetMapping("/productSearch")
	public String productSearch(Admin_ProductPageRequestDTO pageRequestDTO, Model model, String chk , String search,String max,String min) {
		
	    Admin_ProductPageResponseDTO pageResponseDTO = null;

	    pageResponseDTO = service.searchProucts(pageRequestDTO,chk,search,max,min);
	    model.addAttribute("pageResponse", pageResponseDTO);
	    model.addAttribute("search", search);
	    model.addAttribute("chk", chk);
	    model.addAttribute("max", max);
	    model.addAttribute("min", min);
	    
	    return "/product/productSearch";
	}
	
	
	
}
