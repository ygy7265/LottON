package kr.co.lottemarket.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.cs.ArticleCate1DTO;
import kr.co.lottemarket.dto.cs.ArticleCate2DTO;
import kr.co.lottemarket.entity.product.ProductCate1Entity;
import kr.co.lottemarket.entity.product.ProductCate2Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "lotte_board")
public class ArticleCate1Entity {

	@Id
	@Column(name = "\"group\"")
	private int group;
	private int cate1;
	private String cate1_name;
	private String cate1_discription;
	
	public ArticleCate1DTO toDTO() {
		return ArticleCate1DTO.builder()
							.group(group)
							.cate1(cate1)
							.cate1_discription(cate1_discription)
							.cate1_name(cate1_name)
							.build();
	}


}
