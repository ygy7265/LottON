package kr.co.lottemarket.cs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.lottemarket.dto.product.ProductCate2DTO;

@Mapper
public interface Category2Mapper {
	
	public List<ProductCate2DTO> selectProductCate2(int cate1);
	
}
