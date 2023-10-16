package kr.co.lottemarket.dto.product;

import kr.co.lottemarket.entity.product.ProductCate1Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCate1DTO {
	
	private int cate1;
	private String c1Name;
	
	public ProductCate1Entity toEntity() {
		return ProductCate1Entity.builder()
				.cate1(cate1)
				.c1Name(c1Name)
				.build();
	}
}
