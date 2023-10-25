package kr.co.lottemarket.service.mypage;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.product.ProductReviewDTO;
import kr.co.lottemarket.dto.product.ReviewPageRequestDTO;
import kr.co.lottemarket.dto.product.ReviewPageResponseDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.product.ProductPointEntity;
import kr.co.lottemarket.entity.product.ProductReviewEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.mypage.OrderRepository;
import kr.co.lottemarket.repository.mypage.PointRepository;
import kr.co.lottemarket.repository.mypage.ReviewRepository;
import kr.co.lottemarket.repository.product.ProductOrderCompleteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MyPageReviewService {
	
	
	private final RootConfig user;
	private final ReviewRepository reviewRepo;
	private final ProductOrderCompleteRepository orderRepo;
	private final PointRepository pointRepo;
	private final ModelMapper modelmapper;
	
	
	public UserEntity entity() {
		String uid = (String)user.Usersession();
		UserEntity entity = new UserEntity();
		uid = "seller1";
		entity.setUid(uid);
		
		return entity;
	}
	@Transactional
	public void reviewSave(ProductReviewDTO dto,int ordCompleteNo) {
		dto.setUser(entity());
		UserEntity user = entity();
		user.setUid("seller1");
		ProductOrderEntity orderEntity = orderRepo.findByOrdCompleteNo(ordCompleteNo);
		orderEntity.setOrdCompleteNo(ordCompleteNo);
		orderEntity.setRvComplete(1);
		ProductPointEntity pointEntity = new ProductPointEntity();
		pointEntity.setOrder(orderEntity);
		pointEntity.setPoint(300);
		pointEntity.setUser(user);
		pointEntity.setType("상품 리뷰 작성 추가 포인트");
		pointRepo.save(pointEntity);
		ProductReviewEntity entity = modelmapper.map(dto, ProductReviewEntity.class);
		orderRepo.save(orderEntity);
		reviewRepo.save(entity);
		
	}
	
	public ReviewPageResponseDTO reviewFind(ReviewPageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable();
		UserEntity entity = entity();
		entity.setUid("seller1");
		Page<ProductReviewEntity> entityList =  reviewRepo.findByUserOrderByRevNoDesc(entity, pageable);
		List<ProductReviewDTO> dtoList = entityList.map(e -> modelmapper.map(e, ProductReviewDTO.class)).toList();
		int total = (int) entityList.getTotalElements();
		 
		ReviewPageResponseDTO list =  ReviewPageResponseDTO.builder().total(total).dtoList(dtoList).reviewPageRequestDTO(pageRequestDTO).build();
		 
		 return list;
	}
}
