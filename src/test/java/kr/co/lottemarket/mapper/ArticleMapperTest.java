package kr.co.lottemarket.mapper;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.lottemarket.cs.mapper.ArticleMapper;
import kr.co.lottemarket.dto.ArticleDTO;

@SpringBootTest
public class ArticleMapperTest {

	
	@Autowired
	private ArticleMapper articleMapper;
	
	
	@DisplayName("selectArticles 테스트")
	public void selectArticles() {
		System.out.println("테스트1번 입니다.");
		List<ArticleDTO> articles = articleMapper.selectArticles(2, 1);
		System.out.println(articles);
		
	}
	
	@Test
	@DisplayName("selectCate1s 테스트")
	public void selectCate1() {
		System.out.println("테스트2번 입니다.");
		List<ArticleDTO> cate1 = articleMapper.selectCate1(1);
		System.out.println(cate1);
		
	}
	
}
