package kr.co.lottemarket.repository.product;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.product.ProductPointEntity;
import kr.co.lottemarket.entity.user.UserEntity;

@Repository
public interface ProductOrderCompleteRepository extends JpaRepository<ProductOrderEntity, Integer>{
	
	
	public Page<ProductOrderEntity> findByUserOrderByOrdDateDesc(UserEntity user,Pageable pageable);
	public List<ProductOrderEntity> findTop5ByUserOrderByOrdDateDesc(UserEntity user);
	public ProductOrderEntity findByOrdCompleteNo(int ordNo);
	public int countByUserAndOrdComplete(UserEntity entity, int complete);
	@Query("SELECT p FROM ProductOrderEntity p JOIN p.product c WHERE p.ordDate BETWEEN :startDate AND :endDate AND user = :user")
	public Page<ProductOrderEntity> findOrdersWithinDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,UserEntity user, Pageable pageable);

	@Query("SELECT p FROM ProductPointEntity p WHERE p.pointDate BETWEEN :startDate AND :endDate AND user = :user")
	public Page<ProductPointEntity> findPointsWithinDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,UserEntity user, Pageable pageable);
}
