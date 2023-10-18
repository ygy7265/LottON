package kr.co.lottemarket.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageResponseDTO;
import kr.co.lottemarket.service.admin.AdminService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class Admin_CsController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/layout/cs/noticelist")
	public String noticelist(Admin_CsPageRequestDTO pageRequestDTO, Model model) {
		
		Admin_CsPageResponseDTO pageResponseDTO = adminService.selectArticles(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);
		
		return "/admin/layout/cs/noticelist";
	}
	
	@GetMapping("/admin/layout/cs/qnalist")
	public String qnalist(Admin_CsPageRequestDTO pageRequestDTO, Model model) {
		
		Admin_CsPageResponseDTO pageResponseDTO = adminService.selectArticles(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);
		
		return "/admin/layout/cs/qnalist";
	}
	
	@GetMapping("/admin/layout/cs/faqlist")
	public String faqlist(Admin_CsPageRequestDTO pageRequestDTO, Model model) {
		
		Admin_CsPageResponseDTO pageResponseDTO = adminService.selectArticles(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);
		
		return "/admin/layout/cs/faqlist";
	}
	
	@GetMapping("/admin/layout/cs/qnaview")
    public String qnaview(int no, Model model) {
        ArticleDTO qnaview = adminService.selectArticle(no);
        model.addAttribute("qnaview", qnaview);
        return "/admin/layout/cs/qnaview";
    }
	
	@GetMapping("/admin/layout/cs/noticeview")
    public String noticeview(int no, Model model) {
        ArticleDTO noticeview = adminService.selectArticle(no);
        model.addAttribute("noticeview", noticeview);
        return "/admin/layout/cs/noticeview";
    }
	
	@GetMapping("/admin/layout/cs/faqview")
    public String faqview(int no, Model model) {
        ArticleDTO faqview = adminService.selectArticle(no);
        model.addAttribute("faqview", faqview);
        return "/admin/layout/cs/faqview";
    }
	
	@GetMapping("/admin/layout/cs/qnaWrite")
	public String qnaWrite() {
		
		return "/admin/layout/cs/qnaWrite";
	}
	
	@GetMapping("/admin/layout/cs/noticeWrite")
	public String noticeWrite() {
		
		
		return "/admin/layout/cs/noticeWrite";
	}
	
	@GetMapping("/admin/layout/cs/faqWrite")
	public String faqWrite() {
		
		return "/admin/layout/cs/faqWrite";
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
