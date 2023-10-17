package kr.co.lottemarket.controller.product;

import java.util.ArrayList;
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
import kr.co.lottemarket.service.ProductService;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	@Autowired
	private ModelMapper modelmapper;
	
	
	//Cart
	@GetMapping("/product/productCart")
	public String productCart(Model model, String uid) {
	    uid = "seller1";
	    List<Object[]> dto = service.selectCartItems(uid);

	    // DTO 리스트를 생성하고 엔티티를 DTO로 변환하여 추가
	    List<ProductCartDTO> prodDTOList = new ArrayList<>();
	    for (Object[] objArray : dto) {
	        ProductCartEntity entity = (ProductCartEntity) objArray[0];
	        ProductCartDTO prodDTO = modelmapper.map(entity, ProductCartDTO.class);
	        prodDTOList.add(prodDTO);
	    }
	    log.info("prodprod"+prodDTOList.toString());
	    model.addAttribute("dto", prodDTOList);

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
	@DeleteMapping
	
	
	
	
	//Order
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
		String name = proddto.getProdName();
		
		model.addAttribute("dto",proddto);
		return "/product/productView";
	}
	
	
	
}
