package kr.co.lottemarket.dto.product;




import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPageRequestDTO {
		
	
	@Builder.Default
	private int pg = 1;
	
	@Builder.Default
	private int size = 10;
	public Pageable getPageable() {
		
		return PageRequest.of(this.pg-1, this.size);
	}
}
