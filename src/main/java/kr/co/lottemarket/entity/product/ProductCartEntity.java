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
import kr.co.lottemarket.dto.product.ProductCartDTO;
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
@Table(name = "lotte_product_cart")
public class ProductCartEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private UserEntity uid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prodNo")
	private ProductEntity prodNo;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	@CreationTimestamp
	private LocalDateTime rDate;
	
}
