package kr.co.lottemarket.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lottemarket.dto.ArticleDTO;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class CsRestController {

	@Autowired
	private CsSerivce articleService;

	
	@ResponseBody
	@GetMapping("/cs/board/write/3/{cate1}")
	public List<ArticleDTO> selectAjaxCate2(@PathVariable int cate1){
		
		List<ArticleDTO> cate2List = articleService.selectAjaxCate2(3, cate1);

		log.info("cate1 = " + cate1);
		log.info("cate2List = " + cate2List);
		
		return cate2List;
	}
	
}
