package kr.co.lottemarket.repository.admin;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import kr.co.lottemarket.entity.article.ArticleEntity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AdminCsRepository extends JpaRepository<ArticleEntity, Integer>{
	
	Optional<ArticleEntity> findById(int no);
    
    @Transactional
	public void deleteByNo(int no);
    
}
