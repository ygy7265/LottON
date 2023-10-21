package kr.co.lottemarket.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import kr.co.lottemarket.dto.user.TermsDTO;
import kr.co.lottemarket.repository.user.TermsRepository;

@Service
public class TermsService {

	
	@Autowired
	private TermsRepository termsRepository;
	
	public TermsDTO findById() {
		return termsRepository.findById(0).get().toDTO(); // get을 통해 TermsEntity를 선언하는 효과, 그리고 TermsEntity의 메서드를 호출할 수 있다.
	}
							
}