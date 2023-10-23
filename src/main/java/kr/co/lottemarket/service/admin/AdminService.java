package kr.co.lottemarket.service.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import kr.co.lottemarket.cs.mapper.CategoryMapper;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.dto.admin.Admin_FileDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageRequestDTO;
import kr.co.lottemarket.dto.admin.Admin_ProductPageResponseDTO;
import kr.co.lottemarket.dto.cs.ArticleCate1DTO;
import kr.co.lottemarket.dto.cs.ArticleCate2DTO;
import kr.co.lottemarket.dto.product.ProductCate1DTO;
import kr.co.lottemarket.dto.product.ProductCate2DTO;
import kr.co.lottemarket.dto.product.ProductDTO;
import kr.co.lottemarket.entity.article.ArticleEntity;
import kr.co.lottemarket.entity.product.ProductCate1Entity;
import kr.co.lottemarket.entity.product.ProductEntity;
import kr.co.lottemarket.repository.admin.AdminProductRepository;
import kr.co.lottemarket.repository.admin.Admin_FileRepository;
import kr.co.lottemarket.repository.admin.AdminCsRepository;
import kr.co.lottemarket.repository.admin.AdminProductCate1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
	private final CategoryMapper category2Mapper;
	
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
		
		Page<ProductEntity> eList = repo.findAll(pageable);
		
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		
		int total = (int) eList.getTotalElements();
		
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();

		return dto;
    }
	
	public Admin_ProductPageResponseDTO selectProdNameProucts(Admin_ProductPageRequestDTO pageRequestDTO, String prodName) {
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		
		Page<ProductEntity> eList = repo.findByProdNameLike("%" + prodName + "%", pageable);
		
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		
		int total = (int) eList.getTotalElements();
		
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();

		return dto;
		
	}
	
		public Admin_ProductPageResponseDTO selectProdNoProucts(Admin_ProductPageRequestDTO pageRequestDTO, int prodNo) {
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		Page<ProductEntity> eList = repo.findByProdNo(prodNo, pageable);
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		int total = (int) eList.getTotalElements();
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		return dto;
		
	}
	
	public Admin_ProductPageResponseDTO selectCompanyProucts(Admin_ProductPageRequestDTO pageRequestDTO, String company) {
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		Page<ProductEntity> eList = repo.findByCompanyLike("%"+company+"%", pageable);
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		int total = (int) eList.getTotalElements();
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
		return dto;
		
	}
	public Admin_ProductPageResponseDTO selectSellerProucts(Admin_ProductPageRequestDTO pageRequestDTO, String seller) {
		
		Pageable pageable = pageRequestDTO.getPageable("prodNo");
		Page<ProductEntity> eList = repo.findBySellerLike("%"+seller+"%", pageable);
		List<ProductDTO> dtoPage = eList.map(entity -> modelmapper.map(entity, ProductDTO.class)).toList();
		int total = (int) eList.getTotalElements();
		Admin_ProductPageResponseDTO dto= Admin_ProductPageResponseDTO.builder().pageRequestDTO(pageRequestDTO).dtoList(dtoPage).total(total).build();
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
    
    public List<ArticleCate1DTO> selectNoticeCate1() {

        List<ArticleCate1DTO> dtoList = category2Mapper.selectArticleNoticeCate1(1);

        return dtoList;
        
    }
    
    public List<ArticleCate1DTO> selectFaqCate1() {
    	
    	List<ArticleCate1DTO> dtoList = category2Mapper.selectArticleNoticeCate1(2);
    	
    	return dtoList;
    	
    }
    
    public List<ArticleCate1DTO> selectQnaCate1() {
    	
    	List<ArticleCate1DTO> dtoList = category2Mapper.selectArticleNoticeCate1(3);
    	
    	return dtoList;
    	
    }
    
    public List<ArticleCate2DTO> selectFaqCate2(int cate1) {
    	
    	List<ArticleCate2DTO> dtoList = category2Mapper.selectArticleFaqCate2(2, cate1);
    	
    	return dtoList;
    	
    }
    
    public List<ArticleCate2DTO> selectQnaCate2(int cate1) {
    	
    	List<ArticleCate2DTO> dtoList = category2Mapper.selectArticleQnaCate2(3, cate1);
    	
    	return dtoList;
    	
    }
    
    public List<ArticleDTO> selectArticleNotices() {
    	
    	List<ArticleDTO> dtoList = category2Mapper.selectArticleNotices();
    	
    	return dtoList;
    	
    }
    
    public List<ArticleDTO> selectArticleFaqs() {
    	
    	List<ArticleDTO> dtoList = category2Mapper.selectArticleFaqs();
    	
    	return dtoList;
    	
    }
    
    public List<ArticleDTO> selectArticleQnas() {
    	
    	List<ArticleDTO> dtoList = category2Mapper.selectArticleQnas();
    	
    	return dtoList;
    	
    }
    
    public List<ArticleDTO> selectSearchArticleNotices(int cate1) {
    	
    	List<ArticleDTO> dtoList = category2Mapper.selectSearchArticleNotice(cate1);
    	
    	return dtoList;
    	
    }
    
    public List<ArticleDTO> selectSearchArticleFaqs(int cate1, int cate2) {
    	
    	List<ArticleDTO> dtoList = category2Mapper.selectSearchArticleFaq(cate1,cate2);
    	
    	return dtoList;
    	
    }
    
    public List<ArticleDTO> selectSearchArticleQnas(int cate1, int cate2) {
    	
    	List<ArticleDTO> dtoList = category2Mapper.selectSearchArticleQna(cate1,cate2);
    	
    	return dtoList;
    	
    }

    
    public ArticleDTO selectArticleNotice(int no) {
    	
    	ArticleDTO dtoList = category2Mapper.selectArticleNotice(no);
    	
    	return dtoList;
    	
    }
    
    public ArticleDTO selectArticleFaq(int no) {
    	
    	ArticleDTO dtoList = category2Mapper.selectArticleFaq(no);
    	
    	return dtoList;
    	
    }
    
    public ArticleDTO selectArticleQna(int no) {
    	
    	ArticleDTO dtoList = category2Mapper.selectArticleQna(no);
    	
    	return dtoList;
    	
    }
    
    public void insertArticle(ArticleDTO dto) {
    	
    	ArticleEntity entity = dto.toEntity();
    	
    	adminCsRepository.save(entity); 
    	
    }
    
    public void deleteArticle(int no) {
    	
    	adminCsRepository.deleteByNo(no);
    	
    }
    
    public void noticemodify(ArticleDTO dto) {
    	
    	category2Mapper.noticemodify(dto);
    	
    }
    
    public void faqmodify(ArticleDTO dto) {
    	
    	category2Mapper.faqmodify(dto);
    	
    }
    
    public void Answer(ArticleDTO dto) {
    	
    	category2Mapper.AnswerQna(dto);
    
    }

    
}
