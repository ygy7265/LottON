package kr.co.lottemarket.dto;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.lottemarket.entity.article.ArticleCate1Entity;
import kr.co.lottemarket.entity.article.ArticleCate2Entity;
import kr.co.lottemarket.entity.article.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

	private int no;
	private int parent;
	private int group;
	private int cate1;
	private int cate2;
	private String uid;
	private String title;
	private String content;
	private String notice_cate1_name;
	private String f;
	private String f2;
	private String q;
	private String q2;
	private LocalDateTime rdate;
	private String cate1_name;
	private String cate2_name;
	private String cate1_discription;
	
	 public ArticleEntity toEntity(){
	        return ArticleEntity.builder()
	                .no(no)
	                .parent(parent)
	                .group(group)
	                .cate1(cate1)
	                .cate2(cate2)
	                .uid(uid)
	                .title(title)
	                .content(content)
	                .rdate(rdate)
	                .comment(comment)
	                .content(content)
	                .build();
	    }
}
