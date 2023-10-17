package kr.co.lottemarket.repository.admin;

import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.entity.ArticleEntity;
import kr.co.lottemarket.entity.product.ProductCate1Entity;
import kr.co.lottemarket.entity.product.ProductEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdminProductCate1Repository extends JpaRepository<ProductCate1Entity, Integer>{
	
	public List<ProductCate1Entity> findAll();
	
}
