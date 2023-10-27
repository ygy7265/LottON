package kr.co.lottemarket.controller.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.product.PointPageRequestDTO;
import kr.co.lottemarket.dto.product.PointPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductPointDTO;
import kr.co.lottemarket.service.mypage.MyPageOrderService;
import kr.co.lottemarket.service.mypage.MyPageOrderService.dataDTO;
import kr.co.lottemarket.service.mypage.MyPageQnaService;
import kr.co.lottemarket.service.product.ProductPointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MyPagePointController {
	
	
	private final RootConfig rootConfig;
	private final ProductPointService pointService;
	private final MyPageQnaService articleSerivce;
	private final MyPageOrderService service;
	@GetMapping("/my/point")
	public String findPoint(PointPageRequestDTO pageRequestDTO,Model model,HttpServletRequest request){
		dataDTO datadto = service.countProduct();
	
		int total = 0;
		
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		PointPageResponseDTO dto = pointService.findPoint(pageRequestDTO,begin,end);
		String user = (String)rootConfig.Usersession();
		total = articleSerivce.selectMyCountTotal(user);
		
		model.addAttribute("point",datadto.getPoint());
		model.addAttribute("count",datadto.getCount());
		model.addAttribute("total", total);
		
		model.addAttribute("dto",dto);
		
		return "/my/point";
	}
}
