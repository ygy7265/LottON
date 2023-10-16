package kr.co.lottemarket.repository.admin;

import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.ArticleEntity;
import kr.co.lottemarket.entity.product.ProductEntity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdminProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	public Page<ProductEntity> findAll(Pageable pageable);
	
	
	
}
