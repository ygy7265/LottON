package kr.co.lottemarket.entity.product;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.product.ProductPointDTO;
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
@Table(name = "lotte_point")
public class ProductPointEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pointNo;
	private String uid;
	private int ordNo;
	private int point;
	private LocalDateTime pointDate;
	
	public ProductPointDTO toDTO() {
		
		return ProductPointDTO.builder()
				.pointNo(pointNo)
				.uid(uid)
				.ordNo(ordNo)
				.point(point)
				.pointDate(pointDate)
				.build();
	}
	
	
}
