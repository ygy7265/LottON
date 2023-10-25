package kr.co.lottemarket.controller.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lottemarket.cs.CsSerivce;
import kr.co.lottemarket.dto.ArticleDTO;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/my")
@Log4j2
public class MyPageRestController {

	@Autowired
	private CsSerivce articleService;
	
	@ResponseBody
	@GetMapping("/qna/{no}")
	public ArticleDTO selectAjaxCate2(@PathVariable int no){
		
		ArticleDTO answer = articleService.selectComment(no);

		log.info("answer = " + answer);
		
		return answer;
	}
}
