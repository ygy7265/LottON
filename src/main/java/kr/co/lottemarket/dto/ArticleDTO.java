package kr.co.lottemarket.dto;

import java.time.LocalDateTime;

import kr.co.lottemarket.entity.ArticleCate1Entity;
import kr.co.lottemarket.entity.ArticleCate2Entity;
import kr.co.lottemarket.entity.ArticleEntity;
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
	private LocalDateTime rdate;
	
	//add
	private ArticleCate1Entity cate1_name;
	private ArticleCate2Entity cate2_name;
	
	 public ArticleEntity toEntity(){
	        return ArticleEntity.builder()
	                .no(no)
	                .parent(parent)
	                .group(group)
	                .cate1(cate1)
	                .cate2(cate2)
	                .cate1_name(cate1_name)
	                .cate2_name(cate2_name)
	                .uid(uid)
	                .title(title)
	                .content(content)
	                .rdate(rdate)
	                .build();
	    }
}
