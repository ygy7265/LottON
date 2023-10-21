package kr.co.lottemarket.repository.product;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	public List<ProductEntity> findTop8ByOrderByHitDesc();
	public List<ProductEntity> findTop8ByOrderByRegDateDesc();
	public List<ProductEntity> findTop8ByOrderByDiscountDesc();
	public List<ProductEntity> findTop5ByOrderBySoldDesc();
	public List<ProductEntity> findTop8ByOrderByScoreDesc();
	
	public Page<ProductEntity> findByOrderByHitDesc(Pageable pageable);
	public Page<ProductEntity> findByOrderByRegDateDesc(Pageable pageable);
	public Page<ProductEntity> findByOrderByDiscountDesc(Pageable pageable);
	public Page<ProductEntity> findByOrderBySoldDesc(Pageable pageable);
	public Page<ProductEntity> findByOrderByScoreDesc(Pageable pageable);
	public Page<ProductEntity> findByProdNameLike(String ProdName,Pageable pageable);
	
	@Query("SELECT p FROM ProductEntity p WHERE p.prodName LIKE %:search% OR p.descript LIKE %:search%")
    public Page<ProductEntity> findByProdNameAndDescriptContaining(@Param("search") String search, Pageable pageable);
	@Query("SELECT p FROM ProductEntity p WHERE p.prodName LIKE %:search% OR p.descript LIKE %:search%")
	public Page<ProductEntity> findByProdNameAndPriceContaining(@Param("search") String search, Pageable pageable);
	@Query("SELECT p FROM ProductEntity p WHERE p.price BETWEEN :min AND :max")
	public Page<ProductEntity> findByPriceContaining(@Param("min") String min,@Param("max") String max, Pageable pageable);
	@Query("SELECT p FROM ProductEntity p WHERE p.price BETWEEN :min AND :max AND p.prodName LIKE %:search%")
	public Page<ProductEntity> findByPriceAndProdNameContaining(@Param("min") String min,@Param("max") String max,@Param("search") String search, Pageable pageable);
	@Query("SELECT p FROM ProductEntity p WHERE p.price BETWEEN :min AND :max AND p.descript LIKE %:search%")
	public Page<ProductEntity> findByPriceAndDescriptContaining(@Param("min") String min,@Param("max") String max,@Param("search") String search, Pageable pageable);
	@Query("SELECT p FROM ProductEntity p WHERE p.price BETWEEN :min AND :max AND p.prodName LIKE %:search% OR p.descript LIKE %:search%")
	public Page<ProductEntity> findByPriceAndProdNameAndDescriptContaining(@Param("min") String min,@Param("max") String max,@Param("search") String search, Pageable pageable);
	
	public Page<ProductEntity> findByDescriptLike(String descript,Pageable pageable);
	public ProductEntity findByProdNo(int prodNo);
	public Page<ProductEntity> findAll(Pageable pageable);
	public Page<ProductEntity> findByCate1AndCate2(int cate1,int cate2, Pageable pageable);
	public Page<ProductEntity> findByCate1AndCate2OrderBySoldDesc(int cate1,int cate2, Pageable pageable);
	@Query("SELECT p FROM ProductEntity p WHERE p.cate1 = :cate1 AND p.cate2 = :cate2 ORDER BY (p.price - (p.price * (p.discount / 100.0))) DESC")
	public Page<ProductEntity> findByCate1AndCate2OrderByPriceDesc(int cate1,int cate2, Pageable pageable);
	@Query("SELECT p FROM ProductEntity p WHERE p.cate1 = :cate1 AND p.cate2 = :cate2 ORDER BY (p.price - (p.price * (p.discount / 100.0))) ASC")
	public Page<ProductEntity> findByCate1AndCate2OrderByPriceAsc(int cate1,int cate2, Pageable pageable);
	public Page<ProductEntity> findByCate1AndCate2OrderByScoreDesc(int cate1,int cate2, Pageable pageable);
	public Page<ProductEntity> findByCate1AndCate2OrderByReviewDesc(int cate1,int cate2, Pageable pageable);
	public Page<ProductEntity> findByCate1AndCate2OrderByRegDateDesc(int cate1,int cate2, Pageable pageable);
	
}
