package kr.co.lottemarket.repository.admin;

import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import kr.co.lottemarket.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdminProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	public Page<ProductEntity> findAll(Pageable pageable);
	
	@Transactional
	public void deleteByprodNo(int prodNo);

	public Page<ProductEntity> findByProdNameLike(String ProdName,Pageable pageable);
	
	public Page<ProductEntity> findByProdNo(int ProdNo, Pageable pageable);
	
	public Page<ProductEntity> findByCompanyLike(String Company, Pageable pageable);
	
	public Page<ProductEntity> findBySellerLike(String Seller, Pageable pageable);
	
}
