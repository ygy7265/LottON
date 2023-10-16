package kr.co.lottemarket.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.TermsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="lotte_terms")
public class TermsEntity {
	
	@Id // Entity에 Id 선언 무조건 해야됨 / 이거 없다고 아예 Run 자체가 안되네
	private int no;
	private String terms;
	private String privacy;
	private String location;
	private String finance;
	private String tax;
	
	public TermsDTO toDTO() {
		return TermsDTO.builder()
						.terms(terms)
						.privacy(privacy)
						.location(location)
						.finance(finance)
						.tax(tax)
						.build();
	}

	
}