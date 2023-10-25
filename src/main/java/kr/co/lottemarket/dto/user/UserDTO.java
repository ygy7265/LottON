package kr.co.lottemarket.dto.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.persistence.Column;
import kr.co.lottemarket.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String uid;
	private String pass1;
	private String pass2;
	private String name;
	private int birth;
	private int gender;
	private String hp;
	private String email;
	private int type;
	private int point;
	private int level;
	private String zip;
	private String addr1;
	private String addr2;
	private String company;
	private String ceo;
	private String bizRegNum;
	private String comRegNum;
	private String tel;
	// manager, managerHp 는 프론트에서 name과 hp로 설정 되어있기 때문에 프론트에서 객체의 속성을 담당하는 DTO에서는 manager, managerHp가 필요없다
	private String fax;
	
	private String regip;
	private LocalDateTime wdate;
	@CreationTimestamp
	private LocalDateTime rdate;
	private String etc1;
	private String etc2;
	private String etc3;
	private String etc4;
	private String etc5;
	
	// Entity로 변환할 때 Entity의 모든 속성을 설정해준다(Entity에서 DTO로 변환할 때도 마찬가지)
	public UserEntity toEntity() {
		return UserEntity.builder()
				.uid(uid)
				.pass(pass1)
				.name(name)
				.birth(birth)
				.gender(gender)
				.hp(hp)
				.email(email)
				.type(type)
				.point(point)
				.level(level)
				.zip(zip)
				.addr1(addr1)
				.addr2(addr2)
				.company(company)
				.ceo(ceo)
				.bizRegNum(bizRegNum)
				.comRegNum(comRegNum)
				.tel(tel)
				.manager(name) // Entity는 manager, managerHp 속성이 있기 때문에 DTO의 name, hp로 설정해준다
				.managerHp(hp)
				.fax(fax)
				.regip(regip)
				.wdate(wdate)
				.rdate(rdate)
				.etc1(etc1)
				.etc2(etc2)
				.etc3(etc3)
				.etc4(etc4)
				.etc5(etc5)
				.build();
	}
}
