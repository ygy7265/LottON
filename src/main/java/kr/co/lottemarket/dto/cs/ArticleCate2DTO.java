package kr.co.lottemarket.dto.cs;

import kr.co.lottemarket.entity.ArticleCate1Entity;
import kr.co.lottemarket.entity.ArticleCate2Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCate2DTO {

	private int cate1;
	private int cate2;
	private String cate2_name;
	
	public ArticleCate2Entity toEntity () {
		return ArticleCate2Entity.builder()
								.cate1(cate1)
								.cate2(cate2)
								.cate2_name(cate2_name)
								.build();
	}
	
}
