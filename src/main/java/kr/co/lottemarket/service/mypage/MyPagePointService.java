package kr.co.lottemarket.service.mypage;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.cs.mapper.ArticleMapper;
import kr.co.lottemarket.dto.product.ProductPointDTO;
import kr.co.lottemarket.entity.product.ProductPointEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.mypage.PointRepository;
import kr.co.lottemarket.repository.mypage.ReviewRepository;
import kr.co.lottemarket.repository.product.ProductOrderCompleteRepository;
import kr.co.lottemarket.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MyPagePointService {
	private final RootConfig user;
	private final ReviewRepository reviewRepo;
	private final ProductOrderCompleteRepository orderRepo;
	private final PointRepository pointRepo;
	private final ModelMapper modelmapper;
	
	public List<ProductPointDTO> pointFindTop5(){
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		UserEntity entity = new UserEntity();
		entity.setUid(uid);
		List<ProductPointEntity> entityList= pointRepo.findTop5ByUserOrderByPointDateDesc(entity);
		List<ProductPointDTO> dtoList = entityList.stream().map(e -> modelmapper.map(e, ProductPointDTO.class)).toList();
		
		return dtoList;
		
		
	}
}
