package kr.co.lottemarket.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.product.ProductCate2DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "cate1")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lotte_product_cate2")
public class ProductCate2Entity {
	
	@Id
	private int cateNo;
	
	private int cate1;
	private int cate2;
	private String c2Name;
	
	public ProductCate2DTO toDTO() {
		return ProductCate2DTO.builder()
				.cate1(cate1)
				.cate2(cate2)
				.c2Name(c2Name)
				.build();
	}
}
