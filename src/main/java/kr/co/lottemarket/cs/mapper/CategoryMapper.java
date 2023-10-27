package kr.co.lottemarket.cs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.cs.ArticleCate1DTO;
import kr.co.lottemarket.dto.cs.ArticleCate2DTO;
import kr.co.lottemarket.dto.product.ProductCate2DTO;
import kr.co.lottemarket.dto.user.TermsDTO;
import kr.co.lottemarket.dto.user.UserDTO;

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
	public List<ArticleDTO> selectArticleNotices(int pg, int pageSize, int pageStartNum);
	public List<ArticleDTO> selectArticleFaqs();
	public List<ArticleDTO> selectArticleQnas(int pg, int pageSize, int pageStartNum);
	public ArticleDTO selectArticleNotice(int no);
	public ArticleDTO selectArticleFaq(int no);
	public ArticleDTO selectArticleQna(int no);
	public List<ArticleDTO> selectSearchArticleNotice(int cate1,int pg, int pageSize, int pageStartNum);
	public List<ArticleDTO> selectSearchArticleFaq(int cate1, int cate2);
	public List<ArticleDTO> selectSearchArticleQna(int cate1, int cate2, int pg, int pageSize, int pageStartNum);
	public void AnswerQna(ArticleDTO dto);
	public List<TermsDTO> selectPolicy();
	public int selectCountNotices();
	public int selectCountQnas();
	public int selectCountNoticesByCate1(int cate1);
	public int selectCountQnasByCate1ANDCate2(int cate1, int cate2);
	public void commentPlus(int no);
	public List<UserDTO> selectUser();
	public int selectCountUser();
	
}
