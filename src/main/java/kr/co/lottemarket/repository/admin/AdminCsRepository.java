package kr.co.lottemarket.repository.admin;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import kr.co.lottemarket.entity.ArticleEntity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


@Repository
public interface AdminCsRepository extends JpaRepository<ArticleEntity, Integer>{
	
	Optional<ArticleEntity> findById(int no);
    public Page<ArticleEntity> findByParentAndGroup(int parent, int group, Pageable pageable);
    
    @Transactional
	public void deleteByNo(int no);
    
}
