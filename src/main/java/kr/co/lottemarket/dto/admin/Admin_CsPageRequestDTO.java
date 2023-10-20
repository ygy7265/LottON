package kr.co.lottemarket.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin_CsPageRequestDTO {

    @Builder.Default
    private int pg = 1;

    @Builder.Default
    private int size = 10;
    
    private int group;
    private int cate1;
    private int cate2;

    public Pageable getPageable(String sort) {

        return PageRequest.of(this.pg - 1, this.size, Sort.by(sort).descending());

    }
    
}
