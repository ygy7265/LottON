package kr.co.lottemarket.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageResponseDTO;
import kr.co.lottemarket.service.admin.AdminService;

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
	
}
