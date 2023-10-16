package kr.co.lottemarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductCartEntity;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCartEntity, String>{
	public List<ProductCartEntity> findByUid(String id);
}
