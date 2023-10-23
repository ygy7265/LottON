package kr.co.lottemarket.dto.product;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDTO {
	
	private int ordNo;
	private int ordCompleteNo;	
	private UserEntity user;
	private ProductEntity product;
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
	private LocalDateTime ordDate;
	
	public ProductOrderEntity toEntity() {
		return ProductOrderEntity.builder()
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
				.ordDate(ordDate)
				.build();
	}

}
