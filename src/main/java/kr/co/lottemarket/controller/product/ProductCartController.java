package kr.co.lottemarket.controller.product;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.lottemarket.dto.product.ProductCartDTO;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
@Controller
public class ProductCartController {
	
	private final ProductService service;
	private final ModelMapper modelmapper;
	
	@GetMapping("/productCart")
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
	    model.addAttribute("dto", prodDTOList);
	    model.addAttribute("dtocart", prodDTOList);

	    return "/product/productCart";
	}

	@PostMapping("/productCart")
	public String productCartPost(ProductCartDTO dto)	{
		
		dto.setTotal(dto.getCount() * dto.getPrice());
		service.insertDTO(dto);
		
		return "redirect:/product/productCart";
	}
	
	
	@DeleteMapping("/{cartNos}")
	@ResponseBody
	public void deleteCart(@PathVariable("cartNos") List<Integer> cartNos) {
		log.info("DeleteCart = " + cartNos);
		service.deleteProductByCart(cartNos);
	}
	

}
