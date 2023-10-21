package kr.co.lottemarket.entity.admin;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.lottemarket.dto.admin.Admin_FileDTO;
import kr.co.lottemarket.entity.article.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "lotte_file")
public class Admin_ProductFIleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fno;
	private int ano;
	private int download;
	private String newName;
	private String oriName;
	@CreationTimestamp
	private LocalDateTime rdate;
	
	public Admin_FileDTO toDTO() {
		return Admin_FileDTO.builder()
						.fno(fno)
						.ano(ano)
						.download(download)
						.newName(newName)
						.oriName(oriName)
						.rdate(rdate)
						.build();
	}
	
}
