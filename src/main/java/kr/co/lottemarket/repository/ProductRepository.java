package kr.co.lottemarket.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	public List<ProductEntity> findTop8ByOrderByHitDesc();
	public List<ProductEntity> findTop8ByOrderByRegDateDesc();
	public List<ProductEntity> findTop8ByOrderByDiscountDesc();
	public List<ProductEntity> findTop5ByOrderBySoldDesc();
	public List<ProductEntity> findTop8ByOrderByScoreDesc();
	public ProductEntity findByProdNo(int prodNo);

	public Page<ProductEntity> findByCate1AndCate2(int cate1,int cate2, Pageable pageable);
	
}
