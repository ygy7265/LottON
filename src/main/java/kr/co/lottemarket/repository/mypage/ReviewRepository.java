package kr.co.lottemarket.repository.mypage;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.product.ProductReviewEntity;
import kr.co.lottemarket.entity.user.UserEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ProductReviewEntity, String>{
	
	public Page<ProductReviewEntity> findByUserOrderByRevNoDesc(UserEntity entity,Pageable pageable);
	public List<ProductReviewEntity> findByProductOrderByRevNoDesc(ProductEntity entity);
	public List<ProductReviewEntity> findTop5ByUserOrderByRegDateDesc(UserEntity entity);
	
}
