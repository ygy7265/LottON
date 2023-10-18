package kr.co.lottemarket.service.product;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.lottemarket.dto.product.ProductCartDTO;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.entity.UserEntity;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.repository.ProductOrderItemRepository;
import kr.co.lottemarket.repository.ProductRepository;
import kr.co.lottemarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductOrderItemService {
	
	
	private final ModelMapper modelmapper;
	private final ProductOrderItemRepository repo;
	private final ProductRepository productrepo;
	private final UserRepository userrepo;
	
	public List<ProductOrderItemDTO> selectOrderItem(String uid){
		uid = "sellr1";
		List<Object[]> dto= repo.findProductsByOrderItem(uid);
		
		List<ProductOrderItemDTO> itemdto = new ArrayList<>();
		for(Object[] item: dto) {
	        log.info("dto"+dto);
	        log.info("item"+item);
	        
			 ProductOrderItemDTO entity = modelmapper.map(item[0], ProductOrderItemDTO.class);
			itemdto.add(entity);
		}
		return itemdto;
	}
	
	public void insertOrder(String productOrderItemEntity) {
		JsonElement jsonElement = JsonParser.parseString(productOrderItemEntity);
		
		JsonArray jsonarray = jsonElement.getAsJsonArray();
		
		for(JsonElement item : jsonarray) {
			JsonObject itemObject = item.getAsJsonObject();
			int listCount = itemObject.get("count").getAsInt();
            int listProdNo = itemObject.get("prodNo").getAsInt();
            int listpriceValue = itemObject.get("price").getAsInt();
            int listdelivery = itemObject.get("delivery").getAsInt();
            int listdiscountValue = itemObject.get("discount").getAsInt();
            int listpoint = itemObject.get("point").getAsInt();
            String listUid = itemObject.get("uid").getAsString();
            String listprodName = itemObject.get("prodName").getAsString();
            int totalprice = listpriceValue * listCount;
            
            ProductEntity productdto = productrepo.findByProdNo(listProdNo);
            UserEntity user = userrepo.findByUid(listUid);
            ProductOrderItemDTO dto = ProductOrderItemDTO.builder()
            .count(listCount)
            .delivery(listdelivery)
            .discount(listdiscountValue)
            .point(listpoint)
            .price(listpriceValue)
            .total(totalprice)
            .prodNo(productdto)
            
            .uid(user)
            .build();
            
            
            ProductOrderItemEntity entity = modelmapper.map(dto, ProductOrderItemEntity.class);
            repo.save(entity);
            log.info("listCount"+listCount);
            log.info("listProdNo"+listProdNo);
            log.info("listpriceValue"+listpriceValue);
            log.info("listdelivery"+listdelivery);
            log.info("listdiscountValue"+listdiscountValue);
            log.info("listpoint"+listpoint);
            log.info("listUid"+listUid);
            log.info("listUid"+listUid);
            log.info("listprodName"+listprodName);
            log.info("end");
		}
	}
	
	
}
