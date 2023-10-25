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

import jakarta.transaction.Transactional;
import kr.co.lottemarket.dto.product.ProductCartDTO;
import kr.co.lottemarket.dto.product.ProductOrderDTO;
import kr.co.lottemarket.dto.product.ProductOrderItemDTO;
import kr.co.lottemarket.entity.product.ProductCartEntity;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.entity.product.ProductOrderEntity;
import kr.co.lottemarket.entity.product.ProductOrderItemEntity;
import kr.co.lottemarket.entity.user.UserEntity;
import kr.co.lottemarket.repository.product.ProductOrderCompleteRepository;
import kr.co.lottemarket.repository.product.ProductOrderItemRepository;
import kr.co.lottemarket.repository.product.ProductRepository;
import kr.co.lottemarket.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductOrderItemService {
	
	
	private final ModelMapper modelmapper;
	private final ProductOrderItemRepository repo;
	private final ProductOrderCompleteRepository orderCompleterepo;
	private final ProductRepository productrepo;
	private final UserRepository userrepo;
	
	//상품주문하기완료
	@Transactional
	public void insertOrderComplete(List<ProductOrderItemDTO> dto,ProductOrderDTO order) {
		ProductOrderEntity orderentity = modelmapper.map(order, ProductOrderEntity.class);
		UserEntity user = userrepo.findByUid(order.getUser().getUid());
		int point = 0;
		for(ProductOrderItemDTO itemdto : dto) {
			int total = itemdto.getPrice() * itemdto.getCount();
			ProductEntity product = productrepo.findByProdNo(itemdto.getProduct().getProdNo());
			
			ProductOrderEntity entity = ProductOrderEntity.builder()
					.ordCount(itemdto.getCount())
					.ordPayment(orderentity.getOrdPayment())
					.ordNo(dto.get(0).getOrdNo())					
					.ordDelivery(itemdto.getDelivery())
					.ordDiscount(itemdto.getDiscount())
					.ordPrice(itemdto.getPrice())
					.ordTotPrice((int)(total * (itemdto.getDiscount()/100.0)))
					.ordsavePoint(itemdto.getPoint())
					.ordusedPoint(orderentity.getOrdusedPoint())
					.recipAddr1(orderentity.getRecipAddr1())
					.recipAddr2(orderentity.getRecipAddr2())
					.recipHp(orderentity.getRecipHp())
					.recipName(orderentity.getRecipName())
					.recipZip(orderentity.getRecipZip())
					.product(product)
					.user(user)
					.build();
			point += itemdto.getPoint();
			orderCompleterepo.save(entity);
		}
		if(point > order.getOrdusedPoint()) {
			point  = point - order.getOrdusedPoint();
			userrepo.modifyPointAdd(order.getUser().getUid(), point);
		}
		else {
			point = order.getOrdusedPoint() - point;
			userrepo.modifyPoint(order.getUser().getUid(),point);
		}
		
		 
	}
	
	//카트리스트 출력
	public List<ProductOrderItemDTO> selectOrderItem(String uid){
		uid = "seller1";
		UserEntity user = UserEntity.builder().uid(uid).build();
		
		List<Object[]> dto= repo.findProductsByOrderItem(uid);
		
		List<ProductOrderItemDTO> itemdto = new ArrayList<>();
		for(Object[] item: dto) {
			ProductOrderItemDTO entity = modelmapper.map(item, ProductOrderItemDTO.class);

			itemdto.add(entity);
		}
		return itemdto;
	}
	
	//카트 > 상품 주문하기 페이지
	@Transactional
	public void insertOrder(String productOrderItemEntity,String uid) {
		JsonElement jsonElement = JsonParser.parseString(productOrderItemEntity);
		uid = "seller1";
		JsonArray jsonarray = jsonElement.getAsJsonArray();
		UserEntity user = userrepo.findByUid(uid);
		repo.deleteByUser(user);
		for(JsonElement item : jsonarray) {
			JsonObject itemObject = item.getAsJsonObject();
			int listCount = itemObject.get("count").getAsInt();
            int listProdNo = itemObject.get("prodNo").getAsInt();
            int listpriceValue = itemObject.get("price").getAsInt();
            int listdelivery = itemObject.get("delivery").getAsInt();
            int listdiscountValue = itemObject.get("discount").getAsInt();
            int listpoint = itemObject.get("point").getAsInt();
            String listUid = itemObject.get("uid").getAsString();
            int totalprice = listpriceValue * listCount;
            
            ProductEntity productdto = productrepo.findByProdNo(listProdNo);
            user = userrepo.findByUid(listUid);
            ProductOrderItemDTO dto = ProductOrderItemDTO.builder()
            .count(listCount)
            .delivery(listdelivery)
            .discount(listdiscountValue)
            .point(listpoint)
            .price(listpriceValue)
            .total(totalprice)
            .product(productdto)
            
            .user(user)
            .build();
            
           
            ProductOrderItemEntity entity = modelmapper.map(dto, ProductOrderItemEntity.class);
            repo.save(entity);

		}
	}
	@Transactional
	public void saveOrderItem(ProductOrderItemDTO dto) {
		UserEntity user = userrepo.findByUid(dto.getUser().getUid());
		log.info("test = " + user.getUid());
		repo.deleteByUser(user);
		ProductOrderItemEntity entity = modelmapper.map(dto, ProductOrderItemEntity.class);
		repo.save(entity);
		
	}

}
