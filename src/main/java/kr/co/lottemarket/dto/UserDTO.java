package kr.co.lottemarket.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import kr.co.lottemarket.entity.UserEntity;
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
	private String pass;
	private String name;
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
	private String bizRegNum;
	private String tel;
	private String manager;
	private String managerHp;
	private String fax;
	private String regip;
	private LocalDateTime wdate;
	private LocalDateTime rdate;
	private String etc1;
	private String etc2;
	private String etc3;
	private String etc4;
	private String etc5;
	
	public UserEntity toEntity() {
		return UserEntity.builder()
				.uid(uid)
				.pass(pass)
				.name(name)
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
				.bizRegNum(bizRegNum)
				.tel(tel)
				.manager(manager)
				.managerHp(managerHp)
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
