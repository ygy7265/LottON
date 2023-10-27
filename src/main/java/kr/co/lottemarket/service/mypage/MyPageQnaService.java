package kr.co.lottemarket.service.mypage;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lottemarket.cs.mapper.ArticleMapper;
import kr.co.lottemarket.dto.ArticleDTO;
import kr.co.lottemarket.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyPageQnaService {
	
	private final ArticleMapper mapper;
	
	public List<ArticleDTO> selectMyQna(String user,int start , int end){
		return mapper.selectMyQna(user,start, end);
	}
	
	
	public ArticleDTO selectComment(int parent) {
		return mapper.selectAnswer(parent);
	}
	
	
	public int selectMyCountTotal(String user) {
		return mapper.selectMyCountTotal(user);
	}
	
	
	
	
}
