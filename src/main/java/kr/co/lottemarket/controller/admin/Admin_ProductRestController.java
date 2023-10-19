package kr.co.lottemarket.controller.admin;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lottemarket.dto.cs.ArticleCate2DTO;
import kr.co.lottemarket.dto.product.ProductCate1DTO;
import kr.co.lottemarket.dto.product.ProductCate2DTO;
import kr.co.lottemarket.entity.product.ProductCate1Entity;
import kr.co.lottemarket.service.admin.AdminService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class Admin_ProductRestController {
    
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/layout/product/cate2/{cate1}")
    @ResponseBody
    public List<ProductCate2DTO> getCate2ByCate1(@PathVariable int cate1) {
        List<ProductCate2DTO> cate2List = adminService.selectCate2(cate1);
        
        log.info("cate2List : " + cate2List);
        
        return cate2List;
        
    }
    
    @GetMapping("/admin/layout/cs/faqcate2/{cate1}")
    @ResponseBody
    public List<ArticleCate2DTO> getFaqCate2ByCate1(@PathVariable int cate1) {
    	List<ArticleCate2DTO> cate2List = adminService.selectFaqCate2(cate1);
    	
    	log.info("cate2List : " + cate2List);
    	
    	return cate2List;
    	
    }
    
    @GetMapping("/admin/layout/cs/qnacate2/{cate1}")
    @ResponseBody
    public List<ArticleCate2DTO> getQnaCate2ByCate1(@PathVariable int cate1) {
    	List<ArticleCate2DTO> cate2List = adminService.selectQnaCate2(cate1);
    	
    	log.info("cate2List : " + cate2List);
    	
    	return cate2List;
    	
    }
}


