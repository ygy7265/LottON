package kr.co.lottemarket.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.lottemarket.dto.PageRequestDTO;
import kr.co.lottemarket.dto.PageResponseDTO;
import kr.co.lottemarket.dto.product.ProductCartDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.repository.ProductCartRepository;
import kr.co.lottemarket.repository.ProductOrderItemRepository;
import kr.co.lottemarket.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	@Autowired
	private ProductCartRepository cartrepo;
	@Autowired
	private ProductOrderItemRepository orderrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	//ProductList
	public List<ProductDTO> selectsProductHit(){
		List<ProductEntity> elist = repo.findTop8ByOrderByHitDesc();
		log.info("hit List..." + elist);
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		log.info("hit List..." + dlist);
		return dlist;
	}
	public List<ProductDTO> selectsProductScore(){
		List<ProductEntity> elist = repo.findTop8ByOrderByScoreDesc();
		log.info("selectsProductScore List..." + elist);
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		log.info("selectsProductScore List..." + dlist);
		return dlist;
	}
	public List<ProductDTO> selectsProductNew(){
	
		List<ProductEntity> elist = repo.findTop8ByOrderByRegDateDesc();
		log.info("selectsProductNew List..." + elist);
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		log.info("selectsProductNew List..." + dlist);
		return dlist;
	}
	public List<ProductDTO> selectsProductDiscount(){
		List<ProductEntity> elist = repo.findTop8ByOrderByDiscountDesc();
		log.info("selectsProductDiscount List..." + elist);
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		log.info("selectsProductDiscount List..." + dlist);
		return dlist;
	}
	public List<ProductDTO> selectsProductSold(){
		modelmapper = new ModelMapper();
		List<ProductEntity> elist = repo.findTop5ByOrderBySoldDesc();
		log.info("selectsProductDiscount List..." + elist);
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		log.info("selectsProductDiscount List..." + dlist);
		return dlist;
	}
	
	
	//PageLogic
	public PageResponseDTO productList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		log.info("pageable...."+pageable);
		log.info("pageRequestDTO...."+pageRequestDTO.toString());
		Page<ProductEntity> eList = repo.findByCate1AndCate2(pageRequestDTO.getCate1(),pageRequestDTO.getCate2(), pageable);
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		log.info("eList...."+eList.toString());
		log.info("dtoPage...."+dtoPage.toString());
		int total = (int) eList.getTotalElements();
		
		log.info("total...."+total);
		PageResponseDTO dto=PageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		log.info("dto...."+dto);
		return dto;
	}
	
	//ProductView
	public ProductDTO selectProd(ProductDTO dto) {
		Optional<ProductEntity> prod = repo.findById(dto.getProdNo());
		ProductDTO proddto = modelmapper.map(prod, ProductDTO.class);
		log.info("selectprod = "+ proddto);
		return proddto;
	}
	
	public void insertDTO(ProductCartDTO dto) {
		ProductCartEntity entity = modelmapper.map(dto, ProductCartEntity.class);
		cartrepo.save(entity);
	}
	
	public void insertDTOBuy(ProductOrderItemDTO dto) {
		ProductOrderItemEntity entity = modelmapper.map(dto, ProductOrderItemEntity.class);
		orderrepo.save(entity);
		
	}
	
	//ProductOrder
	public List<ProductOrderItemDTO> selectOrderItems() {
		List<ProductOrderItemEntity > entitys = orderrepo.findAll();
		List<ProductOrderItemDTO> dto = entitys.stream().map(entity -> modelmapper.map(entity, ProductOrderItemDTO.class)).toList();	
		return dto;
	}
	
	public List<ProductCartDTO> selectCartItems(String uid) {
		List<ProductCartEntity> entitys = cartrepo.findByUid(uid);
		List<ProductCartDTO> dto = entitys.stream().map(entity -> modelmapper.map(entity, ProductCartDTO.class)).toList();	
		return dto;
	}
	
	
}
