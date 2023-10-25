package kr.co.lottemarket.controller.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.dto.product.PointPageRequestDTO;
import kr.co.lottemarket.dto.product.PointPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductPointDTO;
import kr.co.lottemarket.service.product.ProductPointService;

@Controller
public class Point {
	
	@Autowired
	private ProductPointService pointService;
	
	@GetMapping("/my/point")
	public String findPoint(PointPageRequestDTO pageRequestDTO,Model model){
		
		PointPageResponseDTO dto = pointService.findPoint(pageRequestDTO);
		model.addAttribute("dto",dto);
		
		return "/my/point";
	}
}
