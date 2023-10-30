package kr.co.lottemarket.company.service;


import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.lottemarket.cs.mapper.ArticleMapper;
import kr.co.lottemarket.dto.ArticleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyService {


	private final ArticleMapper mapper;
	
	
	public List<ArticleDTO> selectNoticeArticles(){
		return mapper.selectNoticeArticles();
	}
	
	
}
