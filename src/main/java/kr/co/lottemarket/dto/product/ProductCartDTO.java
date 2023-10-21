package kr.co.lottemarket.dto.product;

import java.time.LocalDateTime;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.user.UserEntity;
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
	private UserEntity user;
	private ProductEntity product;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	private LocalDateTime rDate;
}
