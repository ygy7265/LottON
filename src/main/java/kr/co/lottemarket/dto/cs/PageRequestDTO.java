package kr.co.lottemarket.dto.cs;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int pg = 1;

    @Builder.Default
    private int size = 10;

    private int group;
    
    @Builder.Default
    private int cate1 = 0;
    
    private int cate2;
    
    private int no;
    
    private int parent;
    
    public Pageable getPageable(String sort){          
        return PageRequest.of(this.pg - 1, this.size, Sort.by(sort).descending());
    }
}