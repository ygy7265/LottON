package kr.co.lottemarket.entity.product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.entity.UserEntity;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ordNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prodNo")
	private ProductEntity prodNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private UserEntity uid;
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
