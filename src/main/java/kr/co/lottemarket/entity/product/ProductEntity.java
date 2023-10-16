package kr.co.lottemarket.entity.product;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import groovy.transform.Generated;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.product.ProductDTO;
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
@Table(name = "lotte_product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodNo;
	private int cate1;
	private int cate2;
	private String prodName;
	private String descript;
	private String company;
	private String seller;
	private int price;
	private int discount;
	private int point;
	private int stock;
	private int sold;
	private int delivery;
	private int hit;
	@Nullable
	private Integer score;
	private String review;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String detail;
	private String status;
	private String duty;
	private String receipt;
	private String bizType;
	private String origin;
	private String ip;
	@Column(name = "rDate")
	@CreationTimestamp
	private LocalDateTime regDate;
	private String etc1;
	private String etc2;
	private String etc3;
	private String etc4;
	private String etc5;
	
	public ProductDTO toDTO() {
		return ProductDTO.builder()
				.prodNo(prodNo)
				.cate1(cate1)
				.cate2(cate2)
				.prodName(prodName)
				.descript(descript)
				.company(company)
				.seller(seller)
				.price(price)
				.discount(discount)
				.point(point)
				.stock(stock)
				.sold(sold)
				.delivery(delivery)
				.hit(hit)
				.score(score)
				.review(review)
				.thumb1(thumb1)
				.thumb2(thumb2)
				.thumb3(thumb3)
				.detail(detail)
				.status(status)
				.receipt(receipt)
				.bizType(bizType)
				.origin(origin)
				.ip(descript)
				.regDate(regDate)
				.etc1(etc1)
				.etc2(etc2)
				.etc3(etc3)
				.etc4(etc4)
				.etc5(etc5)
				.build();
	}
}
