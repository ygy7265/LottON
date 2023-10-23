package kr.co.lottemarket.repository.admin;

import org.springframework.stereotype.Repository;
import kr.co.lottemarket.entity.product.ProductCate1Entity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdminProductCate1Repository extends JpaRepository<ProductCate1Entity, Integer>{
	
	public List<ProductCate1Entity> findByCate1(int cate1);
	
}
