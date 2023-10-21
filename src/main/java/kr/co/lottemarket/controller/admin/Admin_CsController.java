package kr.co.lottemarket.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageResponseDTO;
import kr.co.lottemarket.dto.cs.ArticleCate1DTO;
import kr.co.lottemarket.dto.cs.ArticleCate2DTO;
import kr.co.lottemarket.dto.product.ProductCate2DTO;
import kr.co.lottemarket.entity.article.ArticleEntity;
import kr.co.lottemarket.service.admin.AdminService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class Admin_CsController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/layout/cs/noticelist")
	public String noticelist(Model model) {
		
		List<ArticleCate1DTO> cate1List = adminService.selectNoticeCate1();
        model.addAttribute("cate1List", cate1List);
		
        List<ArticleDTO> noticelist = adminService.selectArticleNotices();
        
        model.addAttribute("noticelist", noticelist);
        
		return "/admin/layout/cs/noticelist";
	}

	@GetMapping("/admin/layout/cs/qnalist")
	public String qnalist(Model model) {
		
		List<ArticleCate1DTO> cate1List = adminService.selectQnaCate1();
		model.addAttribute("cate1List", cate1List);
		
		List<ArticleDTO> qnalist = adminService.selectArticleQnas();
        model.addAttribute("qnalist", qnalist);
		
		return "/admin/layout/cs/qnalist";
	}
	
	@GetMapping("/admin/layout/cs/faqlist")
	public String faqlist(Model model) {
		
		List<ArticleCate1DTO> cate1List = adminService.selectFaqCate1();	
		model.addAttribute("cate1List", cate1List);
        
		List<ArticleDTO> faqlist  = adminService.selectArticleFaqs();
        model.addAttribute("faqlist", faqlist);
		
		return "/admin/layout/cs/faqlist";
		
	}
	
	@Transactional
	@GetMapping("/admin/layout/cs/qnaview")
    public String qnaview(int no, Model model) {
		
        ArticleDTO qnaview = adminService.selectArticleQna(no);
        model.addAttribute("qnaview", qnaview);
        return "/admin/layout/cs/qnaview";
        
    } 
	
	@GetMapping("/admin/layout/cs/noticeview")
    public String noticeview(int no, Model model) {
        ArticleDTO noticeview = adminService.selectArticleNotice(no);
        model.addAttribute("noticeview", noticeview);
        return "/admin/layout/cs/noticeview";
    }
	
	@GetMapping("/admin/layout/cs/faqview")
    public String faqview(int no, Model model) {
		
        ArticleDTO faqview = adminService.selectArticleFaq(no);
        model.addAttribute("faqview", faqview);
        
        return "/admin/layout/cs/faqview";
    }
	
	@GetMapping("/admin/layout/cs/noticemodify")
	public String noticemodify(int no, Model model) {
		
	
		List<ArticleCate1DTO> cate1List = adminService.selectNoticeCate1();
		model.addAttribute("cate1List", cate1List);
		
		ArticleDTO noticemodify = adminService.selectArticleNotice(no);
		model.addAttribute("noticemodify", noticemodify);
		
		return "/admin/layout/cs/noticemodify";
	}
	
	@GetMapping("/admin/layout/cs/faqmodify")
	public String faqmodify(int no, Model model) {
		
		List<ArticleCate1DTO> cate1List = adminService.selectFaqCate1();
		model.addAttribute("cate1List", cate1List);
		
		ArticleDTO faqmodify = adminService.selectArticleFaq(no);
		model.addAttribute("faqmodify", faqmodify);
		
		return "/admin/layout/cs/faqmodify";
	}
	
	@PostMapping("/admin/layout/cs/noticemodify")
	public String cSnoticemodify(ArticleDTO dto, Model model) {

		adminService.noticemodify(dto);
		
		return "redirect:/admin/layout/cs/noticelist";
		
	}
	
	@PostMapping("/admin/layout/cs/faqmodify")
	public String cSfaqmodify(ArticleDTO dto, Model model) {
		
		adminService.faqmodify(dto);
		
		return "redirect:/admin/layout/cs/faqlist";
	}
	
	@GetMapping("/admin/layout/cs/qnaWrite")
	public String qnaWrite(int no, Model model) {

		ArticleDTO qnaWrite = adminService.selectArticleQna(no);
		model.addAttribute("qnaWrite", qnaWrite);
		
		return "/admin/layout/cs/qnaWrite";
	}
	
	@GetMapping("/admin/layout/cs/noticeWrite")
	public String noticeWrite(Model model) {
		
		List<ArticleCate1DTO> cate1List = adminService.selectNoticeCate1();
		
		model.addAttribute("cate1List", cate1List);
		
		return "/admin/layout/cs/noticeWrite";
	}
	
	@GetMapping("/admin/layout/cs/faqWrite")
	public String faqWrite(Model model) {
		
		List<ArticleCate1DTO> cate1List = adminService.selectFaqCate1();
		
		model.addAttribute("cate1List", cate1List);
		
		return "/admin/layout/cs/faqWrite";
	}
	
	@PostMapping("/admin/layout/cs/qnaWrite")
	public String qnaWriter(ArticleDTO dto) {
		
		adminService.Answer(dto);
		
		return "redirect:/admin/layout/cs/qnalist?group=3";
	}
	
	@PostMapping("/admin/layout/cs/noticeWrite")
	public String noticeWriter(ArticleDTO dto) {
		
		adminService.insertArticle(dto);
		
		return "redirect:/admin/layout/cs/noticelist?group=1";
	}
	
	@PostMapping("/admin/layout/cs/faqWrite")
	public String faqWriter(ArticleDTO dto) {
		
		adminService.insertArticle(dto);
		
		return "redirect:/admin/layout/cs/faqlist?group=2";
	}
	
	@DeleteMapping("/admin/layout/cs/noticeDelete/{no}")
	@Transactional
	public void noticedeleteAriticle(@PathVariable("no") int no) {
		
	    log.info("no :" + no);
	    adminService.deleteArticle(no);

	}
	
	@DeleteMapping("/admin/layout/cs/faqDelete/{no}")
	@Transactional
	public void faqdeleteAriticle(@PathVariable("no") int no) {
		
		log.info("no :" + no);
		adminService.deleteArticle(no);
		
	}
	
	@DeleteMapping("/admin/layout/cs/qnaDelete/{no}")
	@Transactional
	public void qnadeleteAriticle(@PathVariable("no")int no) {
		
		log.info("no :" + no);
		adminService.deleteArticle(no);
		
	}
	
	@DeleteMapping("/admin/layout/cs/selectCsDelete")
	@Transactional
	public void deleteArticles(@RequestBody List<String> checkBoxArr) {
	    for (String noValue : checkBoxArr) {
	        int no = Integer.parseInt(noValue);
	        log.info("no: " + no);
	        adminService.deleteArticle(no);
	    }
	}
	
}
