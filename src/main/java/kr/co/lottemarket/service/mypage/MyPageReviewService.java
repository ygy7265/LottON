package kr.co.lottemarket.service.mypage;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.cs.mapper.ArticleMapper;
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
import kr.co.lottemarket.security.MyUserDetails;
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
	private final ArticleMapper articleMapper;
	
	
	public UserEntity entity() {
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		UserEntity entity = new UserEntity();
		entity.setUid(uid);
		
		return entity;
	}
	@Transactional
	public void reviewSave(ProductReviewDTO dto,int ordCompleteNo) {
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		dto.setUser(entity());
		UserEntity user = entity();
		user.setUid(uid);
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
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		Pageable pageable = pageRequestDTO.getPageable();
		UserEntity entity = entity();
		entity.setUid(uid);
		Page<ProductReviewEntity> entityList =  reviewRepo.findByUserOrderByRevNoDesc(entity, pageable);
		List<ProductReviewDTO> dtoList = entityList.map(e -> modelmapper.map(e, ProductReviewDTO.class)).toList();
		int total = (int) entityList.getTotalElements();
		 
		ReviewPageResponseDTO list =  ReviewPageResponseDTO.builder().total(total).dtoList(dtoList).reviewPageRequestDTO(pageRequestDTO).build();
		 
		 return list;
	}
	
	
	public int selectMyCountTotal(String user) {
		return articleMapper.selectMyCountTotal(user);
	}
	
	public List<ProductReviewDTO> reviewFindTop5(){
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = userDetails.getUsername();
		UserEntity entity = new UserEntity();
		entity.setUid(uid);
		List<ProductReviewEntity> entityList= reviewRepo.findTop5ByUserOrderByRegDateDesc(entity);
		List<ProductReviewDTO> dtoList = entityList.stream().map(e -> modelmapper.map(e, ProductReviewDTO.class)).toList();
		
		return dtoList;
		
		
	}
	
	
}
