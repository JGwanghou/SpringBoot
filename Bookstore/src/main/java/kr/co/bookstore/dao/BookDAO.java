package kr.co.bookstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookstore.vo.BookVO;

@Repository
@Mapper
public class BookDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBook(BookVO vo) {
		mybatis.insert("bookstore.insertBook", vo);
	}
	public BookVO selectBook(int bookId) {
		return mybatis.selectOne("bookstore.selectBook", bookId);
	}
	public List<BookVO> selectBooks() {
		return mybatis.selectList("bookstore.selectBooks");
	}
	public void updateBook(BookVO vo) {
		mybatis.update("bookstore.updateBook", vo);
	}
	public void deleteBook(int bookId) {
		mybatis.delete("bookstore.deleteBook", bookId);
	}
}
