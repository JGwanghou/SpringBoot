package kr.co.voard.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.voard.dao.ArticleDAO;
import kr.co.voard.vo.ArticleVO;

@Service
public class ArticleService {

	@Autowired
	private ArticleDAO dao;
	
	public void insertArticle(HttpServletRequest req, ArticleVO vo) {
		
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		
		dao.insertArticle(vo);
	}
}
