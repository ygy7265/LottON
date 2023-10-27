package kr.co.lottemarket.service.product;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.product.PointPageRequestDTO;
import kr.co.lottemarket.dto.product.PointPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.dto.product.ProductPointDTO;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.product.ProductPointEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.mypage.PointRepository;
import kr.co.lottemarket.repository.product.ProductOrderCompleteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductPointService {

	private final PointRepository pointRepo;
	private final ProductOrderCompleteRepository comRepo;
	private final ModelMapper modelmapper;
	
	public PointPageResponseDTO findPoint(PointPageRequestDTO pageRequestDTO,String begin,String end){
		Pageable pageable = pageRequestDTO.getPageable();
		RootConfig config = new RootConfig();
		String uid = (String)config.Usersession();
		UserEntity entity = new UserEntity();
		entity.setUid("seller1");
		Page<ProductPointEntity> entityList = null;
		if(begin !=null && end != null) {
			 
		 if(!begin.isEmpty() && !end.isEmpty()) {
		        LocalDateTime startDate = LocalDateTime.parse(begin + "T23:59:59");
		        LocalDateTime endDate = LocalDateTime.parse(end + "T23:59:59");
		        entityList = comRepo.findPointsWithinDateRange(startDate,endDate,entity,pageable);
		 }else {
			 entityList = pointRepo.findByUser(entity,pageable);
		 }
		 }else {
			 entityList = pointRepo.findByUser(entity,pageable);
		 }
		
		List<ProductPointDTO> dtoList = entityList.stream().map(e -> modelmapper.map(e, ProductPointDTO.class)).toList();
		int total = (int) entityList.getTotalElements();
		PointPageResponseDTO dto = PointPageResponseDTO.builder().dtoList(dtoList).total(total).PointPageRequestDTO(pageRequestDTO).build();
		
		
		
		
		return dto;
		
	}
}
