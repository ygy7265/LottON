package kr.co.lottemarket.cs.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

	public void selectArticle(int no);
}
