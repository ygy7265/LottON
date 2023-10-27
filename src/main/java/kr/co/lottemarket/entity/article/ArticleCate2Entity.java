package kr.co.lottemarket.entity.article;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.cs.ArticleCate2DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "lotte_board_cate2")
public class ArticleCate2Entity {
	
	@Id
	private int cate1;
	private int cate2;
	private String cate2_name;
	
	
	public ArticleCate2DTO toDTO() {
		return ArticleCate2DTO.builder()
							.cate1(cate1)
							.cate2(cate2)
							.cate2_name(cate2_name)
							.build();
	}
	
}
