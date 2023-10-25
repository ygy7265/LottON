package kr.co.lottemarket.repository.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.user.UserEntity;

@Repository
public interface ProductOrderCompleteRepository extends JpaRepository<ProductOrderEntity, Integer>{
	
	
	public Page<ProductOrderEntity> findByUserOrderByOrdDateDesc(UserEntity user,Pageable pageable);
	public List<ProductOrderEntity> findTop5ByUserOrderByOrdDateDesc(UserEntity user);
	public ProductOrderEntity findByOrdCompleteNo(int ordNo);
	
			
}
