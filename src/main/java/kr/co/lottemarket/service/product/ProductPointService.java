package kr.co.lottemarket.service.product;

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

@Service
public class ProductPointService {

	@Autowired
	private PointRepository pointRepo;
	@Autowired
	private ModelMapper modelmapper;
	
	public PointPageResponseDTO findPoint(PointPageRequestDTO pageRequestDTO){
		Pageable pageable = pageRequestDTO.getPageable();
		RootConfig config = new RootConfig();
		String uid = (String)config.Usersession();
		UserEntity entity = new UserEntity();
		entity.setUid("seller1");
		Page<ProductPointEntity> entityList =  pointRepo.findByUser(entity,pageable);
		List<ProductPointDTO> dtoList = entityList.stream().map(e -> modelmapper.map(e, ProductPointDTO.class)).toList();
		int total = (int) entityList.getTotalElements();
		PointPageResponseDTO dto = PointPageResponseDTO.builder().dtoList(dtoList).total(total).PointPageRequestDTO(pageRequestDTO).build();
		
		return dto;
		
	}
}
