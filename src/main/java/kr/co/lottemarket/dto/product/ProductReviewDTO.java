package kr.co.lottemarket.dto.product;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import groovy.transform.Generated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import lombok.Data;

@Data
public class ProductReviewDTO {
	
	private int revNo;
	private ProductEntity product;
	private String content;
	private UserEntity user;
	private int score;
	private String regip;
	private LocalDateTime rDate;
  
}
