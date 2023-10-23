package kr.co.lottemarket.repository.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.entity.user.UserEntity;

@Repository
public interface ProductOrderCompleteRepository extends JpaRepository<ProductOrderEntity, String>{
	
	
	public List<ProductOrderEntity> findByUser(UserEntity user);
	
	@Query("SELECT pc, p.thumb1,p.prodNo, p.prodName, p.descript FROM ProductOrderEntity pc " +
		       "JOIN pc.user u " +
		       "JOIN pc.product p " +
		       "WHERE u.uid = :uid ")
	List<Object[]> findProductsByUser(@Param("uid") String uid);
			
}
