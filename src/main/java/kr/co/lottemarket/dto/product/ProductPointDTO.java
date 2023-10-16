package kr.co.lottemarket.dto.product;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import kr.co.lottemarket.entity.product.ProductPointEntity;
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
public class ProductPointDTO {

	private int pointNo;
	private String uid;
	private int ordNo;
	private int point;
	private LocalDateTime pointDate;
	
	public ProductPointEntity toEntity() {
		
		return ProductPointEntity.builder()
				.pointNo(pointNo)
				.uid(uid)
				.ordNo(ordNo)
				.point(point)
				.pointDate(pointDate)
				.build();
	}
}
