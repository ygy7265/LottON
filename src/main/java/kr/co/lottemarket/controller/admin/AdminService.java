package kr.co.lottemarket.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageResponseDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.entity.ArticleEntity;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.repository.admin.AdminProductRepository;
import kr.co.lottemarket.repository.admin.AdminCsRepository;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor 
public class AdminService {
	
	
	private final ModelMapper modelMapper;
	private final AdminProductRepository adminProductRepository;
	private final AdminCsRepository adminCsRepository;
	
	public Admin_ProductPageResponseDTO selectProucts(Admin_ProductPageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("prodNo");
        
        Page<ProductEntity> productEntities = adminProductRepository.findAll(pageable);

        Page<ProductDTO> products = productEntities.map(ProductEntity::toDTO);

        List<ProductDTO> dtoList = products.getContent()
                                            .stream()
                                            .map(entity -> modelMapper.map(entity, ProductDTO.class))
                                            .toList();

        int totalElement = (int) products.getTotalElements();

        return Admin_ProductPageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElement)
                .build();
    }
	
	public Admin_CsPageResponseDTO selectArticles(Admin_CsPageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<ArticleEntity> articleEntities = adminCsRepository.findByParentAndGroup(0, pageRequestDTO.getGroup(), pageable);

        Page<ArticleDTO> articles = articleEntities.map(ArticleEntity::toDTO);

        List<ArticleDTO> dtoList = articles.getContent()
                                            .stream()
                                            .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                                            .toList();

        int totalElement = (int) articles.getTotalElements();

        return Admin_CsPageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElement)
                .build();
    }

    public ArticleDTO selectArticle(int no) {
        Optional<ArticleEntity> articleEntityOptional = adminCsRepository.findById(no);
        ArticleEntity articleEntity = articleEntityOptional.get();
        return articleEntity.toDTO();
    }

}
