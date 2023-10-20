package kr.co.lottemarket.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.dto.ArticleDTO;

@Controller
public class CS_MainController {


	@Autowired
	private CsSerivce articleSerivce;
	
	@GetMapping(value = { "/cs", "/cs/index"})
	public String index(Model model) {
		
		List<ArticleDTO> noticeArticles = articleSerivce.selectIndex(1);
		List<ArticleDTO> qnaArticles = articleSerivce.selectIndex(3);
		
		model.addAttribute("noticeArticles", noticeArticles);
		model.addAttribute("qnaArticles", qnaArticles);

		return "/cs/index";
	}
}
