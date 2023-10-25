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

	// 중복체크
	public int countByUid(String uid); // public 생략 가능?? ㅇㅇ
	public int countByEmail(String email);
	public int countByHp(String hp);
	
	// 이메일 인증
	public int countByNameAndEmail(String name, String email); // ***Count 오타 때문에 아예 실행 자체가 안됨, Count가 아니라 count!!!  / selectCountNameAndEmail은 없다, 그리고 By 빠트리면 안됨, 
	public int countByUidAndEmail(String uid, String email);
	
	// 아이디 찾기
	public UserEntity findByNameAndEmail(String name, String email);
	
	// 비밀번호 찾기
	public UserEntity findByUidAndEmail(String uid, String email);
	
	
	@Modifying
	@Query("UPDATE UserEntity u SET u.point = u.point - :point WHERE u.uid = :uid")
	int modifyPoint(String uid,int point);
	@Modifying
	@Query("UPDATE UserEntity u SET u.point = u.point + :point WHERE u.uid = :uid")
	int modifyPointAdd(String uid,int point);
	
	
}
