package kr.co.lottemarket.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.UserEntity;
import kr.co.lottemarket.entity.product.ProductCartEntity;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCartEntity, Integer>{
	  
	    @Query("SELECT pc, p.thumb1,p.prodNo, p.prodName, p.descript FROM ProductCartEntity pc " +
		       "JOIN pc.uid u " +
		       "JOIN pc.prodNo p " +
		       "WHERE u.uid = :uid " +
		       "ORDER BY pc.cartNo DESC")
		List<Object[]> findProductsBySeller(@Param("uid") String uid);
		
		

}
