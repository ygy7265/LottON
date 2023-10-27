package kr.co.lottemarket.service.mypage;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kr.co.lottemarket.cs.mapper.ArticleMapper;
import kr.co.lottemarket.repository.mypage.PointRepository;
import kr.co.lottemarket.repository.product.ProductOrderCompleteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MyPagePointService {
	private final ArticleMapper articleMapper;
		
	public int selectMyCountTotal(String user) {
		return articleMapper.selectMyCountTotal(user);
	}
	
}
