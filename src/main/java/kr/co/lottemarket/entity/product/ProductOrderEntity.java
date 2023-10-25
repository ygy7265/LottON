package kr.co.lottemarket.entity.product;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.entity.user.UserEntity;
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
@Table(name = "lotte_product_order")
public class ProductOrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ordCompleteNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prodNo")
	private ProductEntity product;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private UserEntity user;
	private int ordNo;
	private int ordCount;
	private int ordPrice;
	private int ordDiscount;
	private int ordDelivery;
	private int ordsavePoint;
	private int ordusedPoint;
	private int ordTotPrice;
	private String recipName;
	private String recipHp;
	private String recipZip;
	private String recipAddr1;
	private String recipAddr2;
	private int ordPayment;
	private int ordComplete;
	private int rvComplete;
	@CreationTimestamp
	private LocalDateTime ordDate;
	
	public ProductOrderDTO toDTO() {
		return ProductOrderDTO.builder()
				.ordCompleteNo(ordCompleteNo)
				.ordNo(ordNo)
				.user(user)
				.product(product)
				.ordCount(ordCount)
				.ordPrice(ordPrice)
				.ordDiscount(ordDiscount)
				.ordsavePoint(ordsavePoint)
				.ordusedPoint(ordusedPoint)
				.ordTotPrice(ordTotPrice)
				.recipName(recipName)
				.recipHp(recipHp)
				.recipZip(recipZip)
				.recipAddr1(recipAddr1)
				.recipAddr2(recipAddr2)
				.ordPayment(ordPayment)
				.ordComplete(ordComplete)
				.rvComplete(rvComplete)
				.ordDate(ordDate)
				.build();
	}
}
