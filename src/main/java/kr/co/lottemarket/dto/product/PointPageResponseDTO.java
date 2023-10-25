package kr.co.lottemarket.dto.product;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Data;

@Data
public class PointPageResponseDTO {
	
	
	private List<ProductPointDTO> dtoList;
	private int cate1;
	private int cate2;
	private int pg;
	private int size;
	private int total;
	private int start,end;
	private boolean prev,next;
	
	@Builder
	public PointPageResponseDTO(PointPageRequestDTO PointPageRequestDTO,List<ProductPointDTO> dtoList, int total) {
		this.pg = PointPageRequestDTO.getPg();
		this.size = PointPageRequestDTO.getSize();
		this.total = total;
		this.dtoList = dtoList;
		this.cate1 = PointPageRequestDTO.getCate1();
		this.cate2 = PointPageRequestDTO.getCate2();
		this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
		this.start = this.end - 9;
		int last = (int) (Math.ceil(total / (double) size));
		
		this.end = end > last ? last : end;
		this.prev = this.start > 1 ;
		this.next = total > this.end * this.size;
		
		
	}
}
