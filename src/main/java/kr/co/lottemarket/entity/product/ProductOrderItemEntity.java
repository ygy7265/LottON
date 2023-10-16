package kr.co.lottemarket.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lotte_product_order_item")
public class ProductOrderItemEntity {
	
	@Id
	private int ordNo;
	private int prodNo;
	private String uid;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	
	public ProductOrderItemDTO toDTO() {
		return ProductOrderItemDTO.builder()
				.ordNo(ordNo)
				.prodNo(prodNo)
				.uid(uid)
				.count(count)
				.price(price)
				.discount(discount)
				.point(point)
				.delivery(delivery)
				.total(total)
				.build();
	}
	
	
}
