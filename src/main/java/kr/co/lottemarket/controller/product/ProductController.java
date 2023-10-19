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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.lottemarket.dto.PageRequestDTO;
import kr.co.lottemarket.dto.PageResponseDTO;
import kr.co.lottemarket.dto.product.ProductCartDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.entity.UserEntity;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.service.product.ProductService;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	@Autowired
	private ModelMapper modelmapper;
	@GetMapping("/product/productCart")
	public String productCart(Model model, String uid) {
	    uid = "sellr1";
	    List<Object[]> dto = service.selectCartItems(uid);

	    // DTO 리스트를 생성하고 엔티티를 DTO로 변환하여 추가
	    List<ProductCartDTO> prodDTOList = new ArrayList<>();
	    for (Object[] objArray : dto) {
	        ProductCartEntity entity = (ProductCartEntity) objArray[0];
	        ProductCartDTO prodDTO = modelmapper.map(entity, ProductCartDTO.class);
	        prodDTOList.add(prodDTO);
	    }
	    model.addAttribute("dto", prodDTOList);
	    model.addAttribute("dtocart", prodDTOList);

	    return "/product/productCart";
	}

	@PostMapping("/product/productCart")
	public String productCartPost(ProductCartDTO dto)	{
		UserEntity user = new UserEntity();
		user.setUid("seller1");
		dto.setTotal(dto.getCount() * dto.getPrice());
		dto.setUid(user);
		log.info("dto ==" + dto);
		service.insertDTO(dto);
		return "redirect:/product/productCart";
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
		String name = proddto.getProdName();
		
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
	
	
	
}
