package kr.co.lottemarket.dto.cs;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import kr.co.lottemarket.entity.ArticleCate1Entity;
import kr.co.lottemarket.entity.ArticleEntity;
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
public class ArticleCate1DTO {
	
	private ArticleEntity group;
	private int cate1;
	private String cate1_name;
	private String cate1_discription;
	
	public ArticleCate1Entity toEntity () {
		
		return ArticleCate1Entity.builder()
								.group(group)
								.cate1(cate1)
								.cate1_name(cate1_name)
								.cate1_discription(cate1_discription)
								.build();
	}
	
}
