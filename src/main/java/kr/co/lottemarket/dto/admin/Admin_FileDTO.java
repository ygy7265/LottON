package kr.co.lottemarket.dto.admin;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import kr.co.lottemarket.entity.admin.Admin_ProductFIleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin_FileDTO {
	
	private int fno;
	private int ano;
	private int download;
	private String newName;
	private String oriName;
	private LocalDateTime rdate;
	
	public Admin_ProductFIleEntity toEntity() {
		return Admin_ProductFIleEntity.builder()
									.fno(fno)
									.ano(ano)
									.download(download)
									.newName(newName)
									.oriName(oriName)
									.rdate(rdate)
									.build();
	}
	
}
