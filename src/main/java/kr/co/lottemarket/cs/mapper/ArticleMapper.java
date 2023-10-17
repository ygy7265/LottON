package kr.co.lottemarket.cs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.lottemarket.dto.ArticleDTO;

@Mapper
public interface ArticleMapper {
	
	public List<ArticleDTO> selectArticles(int group,int cate1);
	
}
