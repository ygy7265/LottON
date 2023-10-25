package kr.co.lottemarket.controller.my;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nimbusds.oauth2.sdk.http.HTTPRequest;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.product.OrderPageRequestDTO;
import kr.co.lottemarket.dto.product.OrderPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.product.ProductReviewDTO;
import kr.co.lottemarket.dto.product.ReviewPageRequestDTO;
import kr.co.lottemarket.dto.product.ReviewPageResponseDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.product.ProductReviewEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.service.mypage.myPageOrderService;
import kr.co.lottemarket.service.mypage.myPageReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/my")
@RequiredArgsConstructor
@Controller
public class MyPageController {
	
	
	
	private final myPageOrderService service;
	private final myPageReviewService reviewService;

	
	
	@GetMapping("/")
	public String index(Model model) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUid("seller1");
		List<ProductOrderDTO> dto = service.lastOrder(userDTO);
		model.addAttribute("dto",dto);
		return "/my/home";
	}
	@GetMapping("/coupon")
	public String coupon() {
		return "/my/coupon";
	}
	@GetMapping("/order")
	public String order(Model model,OrderPageRequestDTO pageRequestDTO) {
		OrderPageResponseDTO dto =service.OrderList(pageRequestDTO);
		model.addAttribute("dtoList",dto);
		
		return "/my/order";
	}
	@GetMapping("/info")
	public String info() {
		return "/my/info";
	}

	@GetMapping("/qna")
	public String qna() {
		return "/my/qna";
	}
	@GetMapping("/review")
	public String reviewGet(Model model,ReviewPageRequestDTO dto) {
		ReviewPageResponseDTO list = reviewService.reviewFind(dto);
		
		model.addAttribute("list",list);
		
		return "/my/review";
		
	}
	
	@PostMapping("/review")
	public String review(ProductReviewDTO dto,@Param("ordCompleteNo") int ordCompleteNo,HttpServletRequest request) {
	
		dto.setRegip(request.getRemoteAddr());
		reviewService.reviewSave(dto,ordCompleteNo);
		return "redirect:/my/";
	}


}
