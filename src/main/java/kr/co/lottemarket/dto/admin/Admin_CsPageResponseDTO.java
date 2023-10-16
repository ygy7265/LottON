package kr.co.lottemarket.dto.admin;

import java.util.List;

import kr.co.lottemarket.dto.ArticleDTO;
import lombok.Builder;
import lombok.Data;

@Data
public class Admin_CsPageResponseDTO {

	private int group;
    private List<ArticleDTO> dtoList;
    private int pg;
    private int size;
    private int total;

    private int start, end;
    private boolean prev, next;

    @Builder
    public Admin_CsPageResponseDTO(Admin_CsPageRequestDTO pageRequestDTO, List<ArticleDTO> dtoList, int total) {
    	
    	this.group = pageRequestDTO.getGroup();
        this.pg = pageRequestDTO.getPg();
        this.size =  pageRequestDTO.getSize();
        this.dtoList = dtoList;
        this.total = total;

        this.end = (int) (Math.ceil(this.pg/10.0)) * 10;
        this.start = this.end - 9;
        int last = (int) (Math.ceil(total / (double) size));

        this.end = end > last ? last : end;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }

}