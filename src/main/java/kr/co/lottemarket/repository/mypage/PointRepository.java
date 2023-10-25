package kr.co.lottemarket.repository.mypage;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductPointEntity;
import kr.co.lottemarket.entity.user.UserEntity;

@Repository
public interface PointRepository extends JpaRepository<ProductPointEntity, String>{
	
	public Page<ProductPointEntity> findByUser(UserEntity user,Pageable page);
}
