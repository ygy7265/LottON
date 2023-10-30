package kr.co.lottemarket.cs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.mode_return;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.cs.ArticleCate1DTO;
import kr.co.lottemarket.dto.cs.PageRequestDTO;
import kr.co.lottemarket.dto.cs.PageResponseDTO;

@lombok.extern.log4j.Log4j2
@Controller
public class CS_ArticleController {

	@Autowired
	private CsSerivce articleSerivce;


	@GetMapping("/cs/list")
	public String noticeList(Model model, PageRequestDTO pageRequestDTO, int group, int cate1) {
		
		if(pageRequestDTO.getGroup() != 2 ) {
			
			PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, null, 0); // 초기화
			
			if(pageResponseDTO.getCate1() == 0) {
				pageResponseDTO = articleSerivce.findByParentAndGroup(pageRequestDTO);
			}else {
				pageResponseDTO = articleSerivce.findByParentAndGroupAndCate1(pageRequestDTO);
			}
			
			
			log.info("pageResponseDTO no : " + pageResponseDTO.getNo());
			log.info("pageResponseDTO cate1 : " + pageResponseDTO.getCate1());
			log.info("pageResponseDTO cate2 : " + pageResponseDTO.getCate2());
			log.info("pageResponseDTO pg : " + pageResponseDTO.getPg());
	        log.info("pageResponseDTO size : " + pageResponseDTO.getSize());
	        log.info("pageResponseDTO total : " + pageResponseDTO.getTotal());
	        log.info("pageResponseDTO start : " + pageResponseDTO.getStart());
	        log.info("pageResponseDTO end : " + pageResponseDTO.getEnd());
	        log.info("pageResponseDTO prev : " + pageResponseDTO.isPrev());
	        log.info("pageResponseDTO next : " + pageResponseDTO.isNext());
	        log.info("pageResponseDTO group : " + pageResponseDTO.getGroup());
	        log.info("pageResponseDTO.getDtoList()...." + pageResponseDTO.getDtoList());
	        
	        //notice 카테값 출력하기
	        List<ArticleDTO> articleCates = articleSerivce.selectNoticeCate();
	        model.addAttribute("articleCates", articleCates);
	        
			model.addAttribute("pageResponseDTO", pageResponseDTO);
			
			  log.info("pageResponseDTO = " + pageResponseDTO );
			
			model.addAttribute("state", "list");
			
			 //nav
			 ArticleDTO nav = articleSerivce.selectNav(group, cate1);
			 log.info(nav);
			 
			 model.addAttribute("nav", nav);
			
			return "/cs/board/list";
			
		}else {
			 PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, null, 0); // 초기화

			 
			  List<ArticleDTO> lists = articleSerivce.selectCate1(cate1);
			  model.addAttribute("lists", lists);
			  List<ArticleDTO> articles = articleSerivce.selectArticles(group, cate1);
			  log.info("articles = " + articles );
			  log.info("pageRequestDTO cate1 = " + pageRequestDTO.getCate1() );
			  log.info("pageRequestDTO group = " + pageRequestDTO.getGroup() );
			  
			  pageResponseDTO.setCate1(cate1);
			  pageResponseDTO.setGroup(group);
			 
			  for(ArticleDTO article : articles) {
				  for(ArticleDTO list : lists) {
					  log.info("articles = " + article.getCate2());
					  log.info("lists = " + list.getCate2());  
				  }
				  
			  }
			  model.addAttribute("pageResponseDTO", pageResponseDTO);
			  model.addAttribute("articles", articles);
			  model.addAttribute("state", "list");
			  //nav
			  ArticleDTO nav = articleSerivce.selectNav(group, cate1);
			  model.addAttribute("nav", nav);
			 
			return "/cs/board/faqList";
			
		}
	}
	
	@GetMapping("/cs/view")
	public String view(Model model, int no, PageRequestDTO pageRequestDTO) {
		
			log.info("no = " + no);
			ArticleDTO article = articleSerivce.selectArticle(no);
			model.addAttribute("article", article);
			
			PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, null, 0); // 초기화
			
			pageResponseDTO.getCate1();
			
			model.addAttribute("state", "view");
			model.addAttribute(pageResponseDTO);
			
			log.info("article no = " + article.getNo() );
			log.info("article cate1 = " + article.getCate1() );
			log.info("article cate2 = " + article.getCate2());
			log.info("article title = " + article.getTitle() );
			log.info("article content = " + article.getContent() );
			log.info("article rdate = " + article.getRdate() );
			log.info("article comment = " + article.getComment());
			log.info("article cate2_name = " + article.getCate2_name());
			

			log.info(pageRequestDTO.getNo());
			//답글 출력

			//초기값
			ArticleDTO answer = null;
			
			if (article.getComment() == 1) {
				answer = articleSerivce.selectAnswer(pageRequestDTO.getNo());
			} else {
				answer = new ArticleDTO();
			}

			log.info("pageRequestDTO.getParent() = " + pageRequestDTO.getParent());
			log.info("pageRequestDTO.getNo() = " + pageRequestDTO.getNo());
			log.info("answer = " + answer);
			
			model.addAttribute("answer", answer);
			
			
			if(pageRequestDTO.getGroup() == 2) {
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
	
	
	/*
	 * @GetMapping("/cs/qna") public String qanList(Model model, ArticleDTO
	 * articleDTO) {
	 * 
	 * List<ArticleDTO> articles =
	 * articleSerivce.nfindByParentAndGroupCate1(articleDTO);
	 * 
	 * model.addAttribute("articles",articles);
	 * 
	 * return "/cs/qna/qnaList"; }
	 * 
	 * @GetMapping("/cs/qna/view") public String qanView() {
	 * 
	 * return "/cs/qna/qnaView"; }
	 */

	@GetMapping("/cs/qna/write")
	public String qanWrite(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {

		log.info("pageRequestDTO11111 = " + pageRequestDTO );
		
		PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, null, 0); // 초기화
		pageResponseDTO.setGroup(3);
		pageResponseDTO.getCate1();
		
		model.addAttribute("pageResponseDTO", pageResponseDTO);
		model.addAttribute("state", "write");
		
		log.info("pageResponseDTO1111 = " + pageResponseDTO );
		
		List<ArticleDTO> cate1List = articleSerivce.selectAjaxCate1(pageRequestDTO.getGroup());
		
		log.info("pageResponseDTO.getCate1() = " + pageResponseDTO.getCate1());
		log.info("pageResponseDTO.getGroup() = " + pageResponseDTO.getGroup());
		log.info("cate1Listcate1List.... = " + cate1List.get(0));
		
		model.addAttribute("cate1List",cate1List);
		
		return "/cs/board/qnaWrite";
	}

	@PostMapping("/cs/qna/write")
	public String qanWrite(HttpServletRequest request, ArticleDTO dto) {
		log.info("dtodtodto" + dto.getUid());
		articleSerivce.insertArticle(dto);

		return "redirect:/cs/list?group=" + dto.getGroup() + "&cate1=" + dto.getCate1();
	}
	
	
	@GetMapping("/cs/delete/{no}")
	public String delete(@PathVariable("no") int no) {
		
		log.info("no = " + no);
		
		articleSerivce.deleteArticle(no);
		
		return "redirect:/cs/list?group=3&cate1=1";
	}
	
}