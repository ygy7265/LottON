package kr.co.lottemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	public UserEntity findByUid(String uid);

}
