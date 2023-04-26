package kr.co.voard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.voard.service.ArticleService;
import kr.co.voard.vo.ArticleVO;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {

	@Autowired
	private ArticleService service;
	
//	@GetMapping()
//	public List<ArticleVO> selectArticles(){
//		
//	}
	
	@PostMapping("/article/register")
	public void insertArticle(HttpServletRequest req, @RequestBody ArticleVO vo) {
		service.insertArticle(req, vo);
	}
}
