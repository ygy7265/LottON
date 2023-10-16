package kr.co.lottemarket.dto.product;

import kr.co.lottemarket.entity.product.ProductCate1Entity;
import kr.co.lottemarket.entity.product.ProductCate2Entity;
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
public class ProductCate2DTO {
	private ProductCate1Entity cate1;
	private int cate2;
	private String c2Name;
	
	public ProductCate2Entity toEntity() {
		return ProductCate2Entity.builder()
				.cate1(cate1)
				.cate2(cate2)
				.c2Name(c2Name)
				.build();
	}
}
