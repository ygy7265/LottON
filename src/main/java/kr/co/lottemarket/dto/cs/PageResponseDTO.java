package kr.co.lottemarket.dto.cs;

import java.util.List;

import kr.co.lottemarket.dto.ArticleDTO;
import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO {

    private List<ArticleDTO> dtoList;
    private int pg;
    private int size;
    private int total;
    private int group;
    private int cate1;
    private int cate2;
    private int no;
    
    private int start, end;
    private boolean prev, next;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<ArticleDTO> dtoList, int total){

    	this.no = pageRequestDTO.getNo();
    	this.group = pageRequestDTO.getGroup();
        this.cate1 = pageRequestDTO.getCate1();
        this.cate2 = pageRequestDTO.getCate2();
        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
        this.start = this.end - 9;
        int last = (int) (Math.ceil(total / (double)size));

        this.end = end > last ? last : end;
        this.end = end < 1 ? 1 : end;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }

}