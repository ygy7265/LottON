package kr.co.lottemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.TermsEntity;


@Repository
public interface TermsRepository extends JpaRepository<TermsEntity, Integer>{ // TermsEntity 의 Id는 no이고 type은 int, int의 클래스는 Integer

}
