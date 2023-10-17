package kr.co.lottemarket.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.product.ProductOrderItemEntity;

@Repository
public interface ProductOrderItemRepository extends JpaRepository<ProductOrderItemEntity, String>{

}
