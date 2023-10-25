package kr.co.lottemarket.entity.product;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "lotte_product_review")
public class ProductReviewEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int revNo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prodNo")
	private ProductEntity product;
	private String content;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private UserEntity user;
	private int score;
	private String regip;
	@CreationTimestamp
	private LocalDateTime rDate;
	
}
