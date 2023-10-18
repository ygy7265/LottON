package kr.co.lottemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

	public int countByUid(String uid); // public 생략 가능??
	public int countByEmail(String email);
	public int countByHp(String hp);
}
