package kr.co.lottemarket.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.cs.PageRequestDTO;
import kr.co.lottemarket.dto.cs.PageResponseDTO;

@lombok.extern.log4j.Log4j2
@Controller
public class CS_ArticleController {

	@Autowired
	private CsSerivce articleSerivce;
	
	
	
	@GetMapping("/cs/list")
	public String noticeList(Model model, PageRequestDTO pageRequestDTO) {
		
		PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, null, 0); // 초기화
		
		if(pageResponseDTO.getCate1() == 0) {
			pageResponseDTO = articleSerivce.findByParentAndGroup(pageRequestDTO);
		}else {
			pageResponseDTO = articleSerivce.findByParentAndGroupAndCate1(pageRequestDTO);
		}
		
		log.info("pageResponseDTO no : " + pageResponseDTO.getNo());
		log.info("pageResponseDTO cate1 : " + pageResponseDTO.getCate1());
		log.info("pageResponseDTO pg : " + pageResponseDTO.getPg());
        log.info("pageResponseDTO size : " + pageResponseDTO.getSize());
        log.info("pageResponseDTO total : " + pageResponseDTO.getTotal());
        log.info("pageResponseDTO start : " + pageResponseDTO.getStart());
        log.info("pageResponseDTO end : " + pageResponseDTO.getEnd());
        log.info("pageResponseDTO prev : " + pageResponseDTO.isPrev());
        log.info("pageResponseDTO next : " + pageResponseDTO.isNext());
		
		model.addAttribute(pageResponseDTO);
		
		if(pageRequestDTO.getGroup() == 2 ) {
			return "/cs/board/faqlist";
		}else {
			return "/cs/board/list";
		}
	}
	
	@GetMapping("/cs/view")
	public String view(Model model, int no, PageRequestDTO pageRequestDTO) {
		
		ArticleDTO article = articleSerivce.findLotteON_boardByNo(no);
		model.addAttribute("article", article);
		
		PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, null, 0); // 초기화
		
		pageResponseDTO.getCate1();
		
		
		model.addAttribute(pageResponseDTO);
		
		log.info("article no = " + article.getNo() );
		log.info("article cate1 = " + article.getCate1() );
		log.info("article title = " + article.getTitle() );
		log.info("article content = " + article.getContent() );
		log.info("article rdate = " + article.getRdate() );
		
		if(pageRequestDTO.getGroup() == 2 ) {
			return "/cs/board/faqView";
		}else {
			return "/cs/board/view";
		}
		
	}
	
	@GetMapping("/cs/faq")
	public String faqList() {

		return "/cs/faq/faqList";
	}
	
	@GetMapping("/cs/faq/view")
	public String faqView() {

		return "/cs/faq/faqView";
	}
	
	
	@GetMapping("/cs/qna")
	public String qanList(Model model, ArticleDTO articleDTO) {
		
		List<ArticleDTO> articles = articleSerivce.nfindByParentAndGroupCate1(articleDTO);
		
		model.addAttribute("articles",articles);
		
		return "/cs/qna/qnaList";
	}
	
	@GetMapping("/cs/qna/view")
	public String qanView() {
		
		return "/cs/qna/qnaView";
	}
	
	
	@GetMapping("/cs/qna/write")
	public String qanWrite(@ModelAttribute PageRequestDTO pageRequestDTO) {

		return "/cs/qna/qnaWrite";
	}
	
	@PostMapping("/cs/qna/write")
	public String qanWrite(HttpServletRequest request, ArticleDTO dto) {
		dto.setUid("seller1");
		log.info("dtodtodto" +  dto.getUid());
		articleSerivce.insertArticle(dto);
		
		
		
		return "redirect:/cs/qna?group=" + dto.getGroup() + "&cate1=" + dto.getCate1();
	}
	
	
	
	

	
	
	
	
	
	
}
