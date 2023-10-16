package kr.co.lottemarket.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "LotteON_board")
public class ArticleEntity {

	@Id
	private int no;
	private int parent;
	private int group;
	private int cate1;
	private int cate2;
	private String uid;
	private String title;
	private String content;
	
	@CreationTimestamp
	private LocalDateTime rdate;
	
	 public ArticleDTO toDTO(){
	        return ArticleDTO.builder()
	                .no(no)
	                .parent(parent)
	                .group(group)
	                .cate1(cate1)
	                .cate2(cate2)
	                .uid(uid)
	                .title(title)
	                .content(content)
	                .rdate(rdate)
	                .build();
	    }



}