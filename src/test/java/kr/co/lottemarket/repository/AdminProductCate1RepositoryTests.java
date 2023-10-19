package kr.co.lottemarket.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.co.lottemarket.cs.mapper.CategoryMapper;
import kr.co.lottemarket.dto.product.ProductCate2DTO;
import kr.co.lottemarket.entity.product.ProductCate1Entity;
import kr.co.lottemarket.entity.product.ProductCate2Entity;
import kr.co.lottemarket.repository.admin.AdminProductCate1Repository;
import kr.co.lottemarket.repository.admin.AdminProductCate2Repository;
import kr.co.lottemarket.service.admin.AdminService;

@SpringBootTest
public class AdminProductCate1RepositoryTests {

	@Autowired
	private AdminService adminService;
	
	@Test
	@Transactional
	public void findcate2() {
		List<ProductCate2DTO> cate2 = adminService.selectCate2(11);
		
		System.out.println(cate2);
		
	}
	
}
