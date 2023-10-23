package kr.co.lottemarket.service.mypage;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.user.UserDTO;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.product.ProductOrderCompleteRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
public class myPageOrderService {
	
	@Autowired
	private ProductOrderCompleteRepository comRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Object[]> OrderList(UserDTO user) {
		UserEntity entity = user.toEntity();
		List<Object[]> productList = comRepo.findProductsByUser("seller1");
		//List<ProductOrderDTO> dto = (List<ProductOrderDTO>) modelMapper.map(productList, ProductOrderDTO.class);	
        return productList;
		
	}
	
	 @Data
	 @AllArgsConstructor
    static class  Result<T>{
        private T data;
    }

}
