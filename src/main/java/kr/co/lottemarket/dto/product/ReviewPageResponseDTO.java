package kr.co.lottemarket.dto.product;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Data;

@Data
public class ReviewPageResponseDTO {
	
	
	private List<ProductReviewDTO> dtoList;
	private int pg;
	private int size;
	private int total;
	private int start,end;
	private boolean prev,next;
	
	@Builder
	public ReviewPageResponseDTO(ReviewPageRequestDTO reviewPageRequestDTO,List<ProductReviewDTO> dtoList, int total) {
		this.pg = reviewPageRequestDTO.getPg();
		this.size = reviewPageRequestDTO.getSize();
		this.total = total;
		this.dtoList = dtoList;
		this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
		this.start = this.end - 9;
		int last = (int) (Math.ceil(total / (double) size));
		
		this.end = end > last ? last : end;
		this.prev = this.start > 1 ;
		this.next = total > this.end * this.size;
		
		
	}
}
