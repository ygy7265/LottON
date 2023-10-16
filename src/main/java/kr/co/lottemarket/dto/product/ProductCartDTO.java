package kr.co.lottemarket.dto.product;

import java.time.LocalDateTime;

import kr.co.lottemarket.entity.*;
import kr.co.lottemarket.entity.product.ProductCartEntity;
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
public class ProductCartDTO {
	
	private int cartNo;
	private String uid;
	private int prodNo;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	private LocalDateTime rDate;
	
	public ProductCartEntity toEntity() {
		return ProductCartEntity.builder()
				.cartNo(cartNo)
				.uid(uid)
				.prodNo(prodNo)
				.count(count)
				.price(price)
				.discount(discount)
				.point(point)
				.delivery(delivery)
				.total(total)
				.rDate(rDate)
				.build();
	}
	
}
