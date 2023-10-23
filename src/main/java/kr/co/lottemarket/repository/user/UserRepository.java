package kr.co.lottemarket.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	// SecurityUserService 
	public UserEntity findByUid(String uid);

	// member
	public int countByUid(String uid); // public 생략 가능??
	public int countByEmail(String email);
	public int countByHp(String hp);
	
	@Modifying
	@Query("UPDATE UserEntity u SET u.point = u.point - :point WHERE u.uid = :uid")
	int modifyPoint(String uid,int point);
	@Modifying
	@Query("UPDATE UserEntity u SET u.point = u.point + :point WHERE u.uid = :uid")
	int modifyPointAdd(String uid,int point);
	
	
}
