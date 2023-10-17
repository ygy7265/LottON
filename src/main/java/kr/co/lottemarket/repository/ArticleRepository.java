package kr.co.lottemarket.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.lottemarket.entity.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer>{
	//전체 게시글 출력
	public Page<ArticleEntity> findByParentAndGroup(int parent, int group, Pageable pageable);
	//카테별 게시글 출력
	public Page<ArticleEntity> findByParentAndGroupAndCate1(int parent, int group, int cate1, Pageable pageable);
	
	//faqList출력
	public Page<ArticleEntity> findByParentAndGroupAndCate1AndCate2(int parent, int group, int cate1, int cate2,Pageable pageable);
	
	//글 보기
	public Optional<ArticleEntity> findLotteON_boardByNo(int no);
	
	public Optional<ArticleEntity> findByParentAndGroupAndCate1OrderByNoDesc(int parent, int group, int cate1);
}