package kr.co.lottemarket.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import kr.co.lottemarket.config.RootConfig;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.PageRequestDTO;
import kr.co.lottemarket.dto.product.PageResponseDTO;
import kr.co.lottemarket.dto.product.ProductCartDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.product.ProductCartRepository;
import kr.co.lottemarket.repository.product.ProductOrderItemRepository;
import kr.co.lottemarket.repository.product.ProductRepository;
import kr.co.lottemarket.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProductService {

	
	private final ProductRepository repo;
	private final ProductCartRepository cartrepo;
	private final ProductOrderItemRepository orderrepo;
	private final UserRepository userepo;
	private final ModelMapper modelmapper;
	int total = 0;
	
	List<ProductDTO> dtoPage = null;

	//ProductList
	public List<ProductDTO> selectsProductHit(){
		List<ProductEntity> elist = repo.findTop8ByOrderByHitDesc();
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		return dlist;
	}
	public List<ProductDTO> selectsProductScore(){
		List<ProductEntity> elist = repo.findTop8ByOrderByScoreDesc();
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		return dlist;
	}
	public List<ProductDTO> selectsProductNew(){
	
		List<ProductEntity> elist = repo.findTop8ByOrderByRegDateDesc();
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		return dlist;
	}
	public List<ProductDTO> selectsProductDiscount(){
		List<ProductEntity> elist = repo.findTop8ByOrderByDiscountDesc();
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		return dlist;
	}
	public List<ProductDTO> selectsProductSold(){
		List<ProductEntity> elist = repo.findTop5ByOrderBySoldDesc();
		List<ProductDTO> dlist = elist.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		return dlist;
	}
	
	
	//PageLogic
	public PageResponseDTO productList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = null;
		Page<ProductEntity> eList = null;
		
		switch(pageRequestDTO.getType()) {
			case 1:
				pageable = pageRequestDTO.getPageable("prodNo");
				eList = repo.findByOrderByHitDesc(pageable);
				
				break;
			
			case 2:
				pageable = pageRequestDTO.getPageable("regDate");
				eList = repo.findByOrderByRegDateDesc(pageable);
				break;
			case 3:
				pageable = pageRequestDTO.getPageable("score");
				eList = repo.findByOrderByScoreDesc(pageable);
				break;
			case 4:
				pageable = pageRequestDTO.getPageable("sold");
				eList = repo.findByOrderBySoldDesc(pageable);
				break;
			case 5:
				pageable = pageRequestDTO.getPageable("discount");
				eList = repo.findByOrderByDiscountDesc(pageable);
				break;
			case 6:
				pageable = pageRequestDTO.getPageable("sold");
				eList = repo.findByCate1AndCate2OrderBySoldDesc(pageRequestDTO.getCate1(),pageRequestDTO.getCate2(),pageable);
				break;
			case 7:
				pageable = pageRequestDTO.getPageable("price");
				eList = repo.findByCate1AndCate2OrderByPriceAsc(pageRequestDTO.getCate1(),pageRequestDTO.getCate2(),pageable);
				break;
			case 8:
				pageable = pageRequestDTO.getPageable("price");	
				eList = repo.findByCate1AndCate2OrderByPriceDesc(pageRequestDTO.getCate1(),pageRequestDTO.getCate2(),pageable);
				break;
			case 9:
				pageable = pageRequestDTO.getPageable("Score");
				eList = repo.findByCate1AndCate2OrderByScoreDesc(pageRequestDTO.getCate1(),pageRequestDTO.getCate2(),pageable);
				break;
			case 10:
				pageable = pageRequestDTO.getPageable("review");
				eList = repo.findByCate1AndCate2OrderByReviewDesc(pageRequestDTO.getCate1(),pageRequestDTO.getCate2(),pageable);
				break;
			case 11:
				pageable = pageRequestDTO.getPageable("regDate");
				eList = repo.findByCate1AndCate2OrderByRegDateDesc(pageRequestDTO.getCate1(),pageRequestDTO.getCate2(),pageable);
				break;
			default:
			pageable = pageRequestDTO.getPageable("prodNo");
			eList = repo.findByCate1AndCate2(pageRequestDTO.getCate1(),pageRequestDTO.getCate2(), pageable);
				break;	
		}
		
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		int total = (int) eList.getTotalElements();
		PageResponseDTO dto=PageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		return dto;
	}
	
	//ProductView
	public ProductDTO selectProd(ProductDTO dto) {
		Optional<ProductEntity> prod = repo.findById(dto.getProdNo());
		ProductDTO proddto = modelmapper.map(prod, ProductDTO.class);
		return proddto;
	}
	@Transactional
	public void insertDTO(ProductCartDTO dto) {
		
		dto.setTotal(dto.getCount() * dto.getPrice());
		ProductCartEntity result = cartrepo.findCartNoByProduct_ProdNo(dto.getProduct().getProdNo());
		UserEntity user = userepo.findByUid(dto.getUser().getUid());
		
		userepo.save(user);
		ProductCartEntity entity = modelmapper.map(dto, ProductCartEntity.class);
		entity.setUser(dto.getUser());
		
		log.info(dto.getProduct().getProdNo());
		
		if(result != null) {
			cartrepo.modifyCount(result.getCartNo(),entity.getCount());
		}else {
			cartrepo.save(entity);
		}
		
		
		
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
	
	public List<Object[]> selectCartItems(String uid) {
	
		List<Object[]> entitys = cartrepo.findProductsBySeller(uid);
		
		return entitys;
	}
	
	public List<ProductOrderItemDTO> findProductsByOrderItem(String uid) {
		
	
		UserEntity entity = new UserEntity();
		entity.setUid(uid);
		List<ProductOrderItemEntity> entitys = orderrepo.findByUser(entity);
		List<ProductOrderItemDTO> dto = entitys.stream().map(e-> modelmapper.map(e, ProductOrderItemDTO.class)).toList();
		return dto;
	}
	
	public void deleteProductByCart(List<Integer> cartNo) {
			for(int cartentity : cartNo) {			
				cartrepo.deleteById(cartentity);
			}
			
	}
	
	@SuppressWarnings("unused")
	public Admin_ProductPageResponseDTO searchProucts(Admin_ProductPageRequestDTO pageRequestDTO,String chk, String search,String max,String min){
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		Page<ProductEntity> eList = null;
		List<Page<ProductEntity>> entityList = new ArrayList<>();
		final String  prodName = "prodName";
		final String descript = "descript";
		final String price = "price";
		
		
		String[] searchs = search.split(" ");
		
		
		if(searchs != null) {
			searchs = search.split(" ");
		}
		if(min != null) {
			if(min.isEmpty()) {
				min = "0";
			}
			if(max.isEmpty()) {
				max = "99999999";
			}
		}
		
	    if (chk != null) {
	    	String[] type = chk.split(",");
	    	if(type.length >= 2) {
	    		if(chk.contains(prodName) && chk.contains(descript) && chk.contains(price)) {
	        		log.info("1");
	        		eList = repo.findByPriceAndProdNameAndDescriptContaining(min,max,search, pageable);
	        		
	    		}else if(chk.contains(prodName) && chk.contains(price)) {
	    			log.info("2");
	    			eList = repo.findByPriceAndProdNameContaining(min,max,search, pageable);
	    			
	    		}else if(chk.contains(descript) && chk.contains(price)) {
	    			log.info("3");
	    			eList = repo.findByPriceAndDescriptContaining(min,max,search, pageable);
	    			
	    		}else {
	    			log.info("4");
	    			eList = repo.findByProdNameAndDescriptContaining(search, pageable);
	    		}
        	
        	}else {
        		if(chk.equals(prodName) && searchs.length < 2) {
        			log.info("5");
       	    	 	eList = repo.findByProdNameLike("%" + search + "%", pageable);
       	    	 	
        		}else if(chk.equals(price) && searchs.length < 2){
        			log.info("6");
        			eList = repo.findByPriceContaining(min, max, pageable);
        			
        		}else if(chk.equals(descript) && searchs.length < 2){
        			log.info("7");
        			eList = repo.findByDescriptLike("%" + search + "%", pageable);
        		}
        		
        		
       	    }
	    	if(eList != null) {
	    		dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
				total = (int) eList.getTotalElements();
	    	}
	    	
			
	    }
	    else if(search != null) {
    		
    		if(search.length() >= 2) {
    			log.info("8");
    			for(String item :searchs) {
    				entityList.add(repo.findByProdNameLike("%" + item + "%", pageable));
    			}
    			List<List<ProductDTO>> dtopages = entityList.stream().map(page -> page.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).collect(Collectors.toList())).collect(Collectors.toList());
        		dtoPage = dtopages.stream().flatMap(List::stream).collect(Collectors.toList());
        		total = dtopages.stream().mapToInt(List::size).sum();
    		}else {
    			log.info("9");
    			eList = repo.findByProdNameLike("%" + search + "%", pageable);
    			dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
    			total = (int) eList.getTotalElements();
    		}
    		
	    }else {
	    	log.info("10");
	    	eList = repo.findAll(pageable);
	    	dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
			total = (int) eList.getTotalElements();
	    }
	    if(searchs.length >= 2) {
	    	for(String item :searchs) {
	    		log.info("item" + item);
				entityList.add(repo.findByProdNameLike("%" + item + "%", pageable));
			}
	    	log.info("11");
			List<List<ProductDTO>> dtopages = entityList.stream().map(page -> page.stream().map(entity -> modelmapper.map(entity, ProductDTO.class)).collect(Collectors.toList())).collect(Collectors.toList());
    		dtoPage = dtopages.stream().flatMap(List::stream).collect(Collectors.toList());
    		total = dtopages.stream().mapToInt(List::size).sum();
	    }
		
	    
	    int a = pageRequestDTO.getPg();
	    log.info("a =" + a);
	    for(ProductDTO search2 :dtoPage) {
	    	log.info("dtoPage =" + search2.getProdName());
	    	log.info("total" + total);
	    }
	    
	    
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		return dto;
		
	}
	
	
	
}
