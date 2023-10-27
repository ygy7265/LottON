package kr.co.lottemarket.repository.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.entity.user.UserEntity;

@Repository
public interface ProductOrderItemRepository extends JpaRepository<ProductOrderItemEntity, String>{
	
	public void deleteByUser(UserEntity user);
	public List<ProductOrderItemEntity> findByUser(UserEntity uid);
		
			
}
