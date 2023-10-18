package kr.co.lottemarket.service.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import groovyjarjarantlr4.v4.parse.ANTLRParser.option_return;
import kr.co.lottemarket.cs.mapper.Category2Mapper;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_CsPageResponseDTO;
import kr.co.lottemarket.dto.admin.Admin_FileDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.product.ProductCate1DTO;
import kr.co.lottemarket.dto.product.ProductCate2DTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.entity.ArticleEntity;
import kr.co.lottemarket.entity.product.ProductCate1Entity;
import kr.co.lottemarket.entity.product.ProductCate2Entity;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.repository.admin.AdminProductRepository;
import kr.co.lottemarket.repository.admin.Admin_FileRepository;
import kr.co.lottemarket.repository.admin.AdminCsRepository;
import kr.co.lottemarket.repository.admin.AdminProductCate1Repository;
import kr.co.lottemarket.repository.admin.AdminProductCate2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.apache.ibatis.session.SqlSession;
import org.modelmapper.ModelMapper;

@Log4j2
@Service
@RequiredArgsConstructor 
public class AdminService {

	private final AdminProductRepository repo;
	private final ModelMapper modelmapper;
	private final AdminCsRepository adminCsRepository;
	private final Admin_FileRepository admin_FileRepository;
	private final AdminProductCate1Repository adminProductCate1Repository;
	private final Category2Mapper category2Mapper;
	
	@Value("${spring.servlet.multipart.location}")
    private String filePath;
	
	
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
									/////////////////////////Product service part//////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void insertProduct(ProductDTO dto) {
		
		
		ProductEntity entity = dto.toEntity();
		
	    List<Admin_FileDTO> fileDTOs = fileUpload(dto);
	    log.info("fileDTO : " + fileDTOs);
	    
	    
	    if (fileDTOs != null) {
	    	
	        for (Admin_FileDTO adminFileDTO : fileDTOs) {
	        	
	            adminFileDTO.setAno(entity.getProdNo());
	            
	            log.info("adminFileDTO : " + adminFileDTO);
	            
	        }
	        
	        admin_FileRepository.saveAll(fileDTOs.stream().map(Admin_FileDTO::toEntity).collect(Collectors.toList()));
	        
	        entity.setThumb1(fileDTOs.get(0).getNewName());
	        entity.setThumb2(fileDTOs.get(1).getNewName());
	        entity.setThumb3(fileDTOs.get(2).getNewName());
	        entity.setDetail(fileDTOs.get(3).getNewName());
	    }
	    
	    
	    ProductEntity insertEntity = repo.save(entity);
	    log.info("insertEntity : " + insertEntity);
	    
	    
	}

	public List<Admin_FileDTO> fileUpload(ProductDTO dto) {
	    List<Admin_FileDTO> uploadedFiles = new ArrayList<>();
	    
	    MultipartFile[] files = new MultipartFile[]{
	        dto.getFileThumb1(),
	        dto.getFileThumb2(),
	        dto.getFileThumb3(),
	        dto.getFileDetail()
	    };
	    
	    for (MultipartFile mf : files) {
	        if (mf != null && !mf.isEmpty()) {
	            String path = new File(filePath).getAbsolutePath();
	            String originalFileName = mf.getOriginalFilename();
	            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	            String newFileName = UUID.randomUUID().toString() + fileExtension;
	            
	            try {
	                mf.transferTo(new File(path, newFileName));
	                uploadedFiles.add(Admin_FileDTO.builder().oriName(originalFileName).newName(newFileName).build());
	                
	            } catch (IOException e) {
	                log.error(e.getMessage());
	            }
	        }
	    }
	    
	    return uploadedFiles;
	}
	
	public Admin_ProductPageResponseDTO selectProucts(Admin_ProductPageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		log.info("pageable...."+pageable);
		log.info("pageRequestDTO...."+pageRequestDTO.toString());
		Page<ProductEntity> eList = repo.findAll(pageable);
		log.info("eList...."+eList.toString());
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		
		log.info("dtoPage...."+dtoPage.toString());
		int total = (int) eList.getTotalElements();
		
		log.info("total...."+total);
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		log.info("dto...."+dto);
		return dto;
    }
	
	public Admin_ProductPageResponseDTO selectProdNameProucts(Admin_ProductPageRequestDTO pageRequestDTO, String prodName) {
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		log.info("pageable...."+pageable);
		log.info("pageRequestDTO...."+pageRequestDTO.toString());
		Page<ProductEntity> eList = repo.findByProdNameLike("%" + prodName + "%", pageable);
		log.info("eList...."+eList.toString());
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		
		log.info("dtoPage...."+dtoPage.toString());
		int total = (int) eList.getTotalElements();
		
		log.info("total...."+total);
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		log.info("dto...."+dto);
		return dto;
		
	}
	
		public Admin_ProductPageResponseDTO selectProdNoProucts(Admin_ProductPageRequestDTO pageRequestDTO, int prodNo) {
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		log.info("pageable...."+pageable);
		log.info("pageRequestDTO...."+pageRequestDTO.toString());
		 Page<ProductEntity> eList = repo.findByProdNo(prodNo, pageable);
		 log.info("prodNo" + prodNo);
		log.info("eList...."+eList.toString());
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		
		log.info("dtoPage...."+dtoPage.toString());
		int total = (int) eList.getTotalElements();
		
		log.info("total...."+total);
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		log.info("dto...."+dto);
		return dto;
		
	}
	
	public Admin_ProductPageResponseDTO selectCompanyProucts(Admin_ProductPageRequestDTO pageRequestDTO, String company) {
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		log.info("pageable...."+pageable);
		log.info("pageRequestDTO...."+pageRequestDTO.toString());
		Page<ProductEntity> eList = repo.findByCompanyLike("%"+company+"%", pageable);
		log.info("eList...."+eList.toString());
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		
		log.info("dtoPage...."+dtoPage.toString());
		int total = (int) eList.getTotalElements();
		
		log.info("total...."+total);
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		log.info("dto...."+dto);
		return dto;
		
	}
	public Admin_ProductPageResponseDTO selectSellerProucts(Admin_ProductPageRequestDTO pageRequestDTO, String seller) {
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		log.info("pageable...."+pageable);
		log.info("pageRequestDTO...."+pageRequestDTO.toString());
		Page<ProductEntity> eList = repo.findBySellerLike("%"+seller+"%", pageable);
		log.info("eList...."+eList.toString());
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		
		log.info("dtoPage...."+dtoPage.toString());
		int total = (int) eList.getTotalElements();
		
		log.info("total...."+total);
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		log.info("dto...."+dto);
		return dto;
		
	}
    
    public void deleteProduct(int no) {
    	
    	repo.deleteByprodNo(no);
    	
    }
    
    public List<ProductCate1DTO> selectCate1() {
        List<ProductCate1Entity> cate1 = adminProductCate1Repository.findAll();	

        List<ProductCate1DTO> dtoList = cate1.stream()
            .map(entity -> modelmapper.map(entity, ProductCate1DTO.class))
            .collect(Collectors.toList());

        return dtoList;
    }
    
    

    public List<ProductCate2DTO> selectCate2(int cate1) {
    	
    	List<ProductCate2DTO> cate2 = category2Mapper.selectProductCate2(cate1);
    	
        return cate2;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    									/////////////////////////CS service part//////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public Admin_CsPageResponseDTO selectArticles(Admin_CsPageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<ArticleEntity> articleEntities = adminCsRepository.findByParentAndGroup(0, pageRequestDTO.getGroup(), pageable);

        Page<ArticleDTO> articles = articleEntities.map(ArticleEntity::toDTO);

        List<ArticleDTO> dtoList = articles.getContent()
                                            .stream()
                                            .map(entity -> modelmapper.map(entity, ArticleDTO.class))
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
    
    public void insertArticle(ArticleDTO dto) {
    	
    	ArticleEntity entity = dto.toEntity();
    	
    	ArticleEntity insert = adminCsRepository.save(entity); 
    	
    }
    
    public void deleteArticle(int no) {
    	
    	adminCsRepository.deleteByNo(no);
    	
    }
    
}
