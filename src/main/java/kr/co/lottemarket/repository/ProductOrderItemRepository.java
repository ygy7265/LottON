package kr.co.lottemarket.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.UserEntity;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;

@Repository
public interface ProductOrderItemRepository extends JpaRepository<ProductOrderItemEntity, String>{
	
	public void deleteByUser(UserEntity user);
	@Query("SELECT pc, p.thumb1,p.prodNo, p.prodName, p.descript FROM ProductOrderItemEntity pc " +
		       "JOIN pc.user u " +
		       "JOIN pc.product p " +
		       "WHERE u.uid = :uid ")
	List<Object[]> findProductsByOrderItem(@Param("uid") String uid);
		
		
			
}
