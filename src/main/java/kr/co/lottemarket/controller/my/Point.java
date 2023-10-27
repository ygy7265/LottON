package kr.co.lottemarket.controller.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.product.PointPageRequestDTO;
import kr.co.lottemarket.dto.product.PointPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductPointDTO;
import kr.co.lottemarket.service.mypage.MyPageOrderService;
import kr.co.lottemarket.service.mypage.MyPageQnaService;
import kr.co.lottemarket.service.mypage.MyPageReviewService;
import kr.co.lottemarket.service.product.ProductPointService;
import kr.co.lottemarket.service.user.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class Point {
	
	@Autowired
	private ProductPointService pointService;
	private final MyPageQnaService articleSerivce;
	private final RootConfig rootConfig;
	
	@GetMapping("/my/point")
	public String findPoint(PointPageRequestDTO pageRequestDTO,Model model){
		
		PointPageResponseDTO dto = pointService.findPoint(pageRequestDTO);
		model.addAttribute("dto",dto);
		
		int total = 0;
		
		String user = (String)rootConfig.Usersession();
		// 전체 상품 갯수
		total = articleSerivce.selectMyCountTotal(user);
		model.addAttribute("total", total);
		
		return "/my/point";
	}
}
