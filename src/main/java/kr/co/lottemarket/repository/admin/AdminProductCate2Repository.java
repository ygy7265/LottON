package kr.co.lottemarket.repository.admin;

import org.springframework.stereotype.Repository;
import kr.co.lottemarket.entity.product.ProductCate2Entity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdminProductCate2Repository extends JpaRepository<ProductCate2Entity, Integer>{
	
}
