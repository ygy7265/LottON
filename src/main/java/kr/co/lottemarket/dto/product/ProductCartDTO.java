package kr.co.lottemarket.dto.product;

import java.time.LocalDateTime;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import kr.co.lottemarket.entity.*;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.product.ProductEntity;
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
	@OneToMany
	@JoinColumn(name = "prodNo")
	private ProductEntity prodNo;
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
