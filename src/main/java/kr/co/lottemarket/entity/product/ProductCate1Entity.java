package kr.co.lottemarket.entity.product;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.product.ProductCate1DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lotte_product_cate1")
public class ProductCate1Entity {
	
	
	@Id
	private int cate1;
	private String c1Name;
	
	
	
	public ProductCate1DTO toDTO() {
		return ProductCate1DTO.builder()
				.cate1(cate1)
				.c1Name(c1Name)
				.build();
	}
}
