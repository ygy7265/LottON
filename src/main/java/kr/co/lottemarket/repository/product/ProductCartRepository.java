package kr.co.lottemarket.repository.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.user.UserEntity;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCartEntity, Integer>{
	  
	    @Query("SELECT pc, p.thumb1,p.prodNo, p.prodName, p.descript FROM ProductCartEntity pc " +
		       "JOIN pc.user u " +
		       "JOIN pc.product p " +
		       "WHERE u.uid = :uid " +
		       "ORDER BY pc.cartNo DESC")
		List<Object[]> findProductsBySeller(@Param("uid") String uid);
		
		ProductCartEntity findCartNoByProduct_ProdNo(int prodNo);
		
		@Modifying
		@Query("UPDATE ProductCartEntity pc SET pc.count = pc.count + :count WHERE pc.cartNo = :cartNo")
		int modifyCount(int cartNo,int count);
		

}