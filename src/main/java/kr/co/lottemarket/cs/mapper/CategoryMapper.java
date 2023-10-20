package kr.co.lottemarket.cs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.cs.ArticleCate1DTO;
import kr.co.lottemarket.dto.cs.ArticleCate2DTO;
import kr.co.lottemarket.dto.product.ProductCate2DTO;

@Mapper
public interface CategoryMapper {
	
	public List<ProductCate2DTO> selectProductCate2(int cate1);
	public List<ArticleCate1DTO> selectArticleNoticeCate1(int group);
	public List<ArticleCate1DTO> selectArticleFaqCate1(int group);
	public List<ArticleCate1DTO> selectArticleQnaCate1(int group);
	public List<ArticleCate2DTO> selectArticleFaqCate2(int group, int cate1);
	public List<ArticleCate2DTO> selectArticleQnaCate2(int group, int cate1);
	public void noticemodify(ArticleDTO dto);
	public void faqmodify(ArticleDTO dto);
	public List<ArticleDTO> selectArticleNotices();
	public List<ArticleDTO> selectArticleFaqs();
	public List<ArticleDTO> selectArticleQnas();
	public ArticleDTO selectArticleNotice(int no);
	public ArticleDTO selectArticleFaq(int no);
	public ArticleDTO selectArticleQna(int no);
	public ArticleDTO selectSearchArticleNotice(int cate1);
	public ArticleDTO selectSearchArticleFaq(int cate1, int cate2);
	public ArticleDTO selectSearchArticleQna(int cate1, int cate2);
	
}
