package kr.co.lottemarket.dto.user;

import kr.co.lottemarket.entity.user.TermsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TermsDTO {

	// no 속성 없어도 되는 이유는 프론트 쪽에서 no 속성을 호출할 필요가 없기 때문
	private String terms;
	private String privacy;
	private String location;
	private String finance;
	private String buyer;
	private String seller;
	private String tax;
	
	public TermsEntity toEntity() {
		return TermsEntity.builder()
							.terms(terms)
							.privacy(privacy)
							.location(location)
							.finance(finance)
							.tax(tax)
							.build();
	}
}