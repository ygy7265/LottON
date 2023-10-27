package kr.co.lottemarket.service.mypage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.lottemarket.cs.mapper.ArticleMapper;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.OrderPageRequestDTO;
import kr.co.lottemarket.dto.product.OrderPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.entity.product.ProductPointEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.mypage.PointRepository;
import kr.co.lottemarket.repository.product.ProductOrderCompleteRepository;
import kr.co.lottemarket.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MyPageOrderService {
	
	private final ArticleMapper articleMapper;
	private final ProductOrderCompleteRepository comRepo;
	private final PointRepository pointRepo;
	private final ModelMapper modelMapper;
	private final UserService userService;
	
	public OrderPageResponseDTO OrderList(OrderPageRequestDTO pageRequestDTO,String begin,String end) {
		 Pageable pageable = pageRequestDTO.getPageable();
		 
		 UserEntity entity = new UserEntity();
		 entity.setUid("seller1");
		 Page<ProductOrderEntity> list = null;
		 if(begin !=null && end != null) {
			 
			 if(!begin.isEmpty() && !end.isEmpty()) {
				 
			        LocalDateTime startDate = LocalDateTime.parse(begin + "T23:59:59");
			        LocalDateTime endDate = LocalDateTime.parse(end + "T23:59:59");
			        list = comRepo.findOrdersWithinDateRange(startDate,endDate,entity,pageable);
			        
			 }else {
				 
				 list = comRepo.findByUserOrderByOrdDateDesc(entity,pageable);
				 
			 }
		 }else {
			 
			 list = comRepo.findByUserOrderByOrdDateDesc(entity,pageable);
			 
		 }
		 
		 List<ProductOrderDTO> dto = list.stream().map(e -> modelMapper.map(e, ProductOrderDTO.class)).toList();
		 int total = (int) list.getTotalElements();
		 OrderPageResponseDTO orderDTO = OrderPageResponseDTO.builder().dtoList(dto).total(total).OrderPageRequestDTO(pageRequestDTO).build();
		 return orderDTO;
		
	}	public List<ProductOrderDTO> lastOrder(UserDTO userDTO){
		
		UserEntity user = new UserEntity();
		user.setUid(userDTO.getUid());
		List<ProductOrderEntity> entity = comRepo.findTop5ByUserOrderByOrdDateDesc(user);
		List<ProductOrderDTO> dto = entity.stream().map(e -> modelMapper.map(e, ProductOrderDTO.class)).toList();
		
		return dto;
		
	}
	
	@Transactional
	public int OrderCompleteUpdate(int ordNo) {
		
		ProductOrderEntity entity = comRepo.findByOrdCompleteNo(ordNo);
		entity.setOrdComplete(1);
		ProductPointEntity pointEntity = new ProductPointEntity();
		pointEntity.setOrder(entity);
		pointEntity.setUser(entity.getUser());
		pointEntity.setPoint(entity.getOrdsavePoint());
		pointEntity.setType("상품 구매 확정");
		pointRepo.save(pointEntity);
		ProductOrderEntity save = comRepo.save(entity);
		ProductOrderDTO dto = new ProductOrderDTO();
		int result = save.getOrdComplete();
		log.info("result" + result);
		return result;
	}
	
	public int selectMyCountTotal(String user) {
		return articleMapper.selectMyCountTotal(user);
	}
	
	public dataDTO countProduct() {
		UserEntity entity = new UserEntity();
		entity.setUid("seller1");
		int count = comRepo.countByUserAndOrdComplete(entity, 0);
		dataDTO dto = new dataDTO();
		UserDTO userdto = userService.findByUid("seller1");
		dto.setCount(count);
		dto.setPoint(userdto.getPoint());
		return dto;
	}
	
	@Data
	public static class dataDTO{
		private int count;
		private int point;
	}
	

}
