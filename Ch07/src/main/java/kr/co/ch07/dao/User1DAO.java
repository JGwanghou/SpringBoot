package kr.co.ch07.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ch07.vo.User1VO;


@Mapper
@Repository
public class User1DAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertUser1(User1VO vo) {
		mybatis.insert("kr.co.ch07.dao.User1DAO.insertUser1", vo);
	}
	
	public User1VO selectUser1(String uid) {
		return mybatis.selectOne("kr.co.ch07.dao.User1DAO.selectUser1", uid);
	}
	
	public List<User1VO> selectUser1s() {
		return mybatis.selectList("kr.co.ch07.dao.User1DAO.selectUser1s");
		
	}
	
	public void updateUser1(User1VO vo) {
		mybatis.update("kr.co.ch07.dao.User1DAO.updateUser1", vo);
	}
	
	public void deleteUser1(String uid) {
		mybatis.delete("kr.co.ch07.dao.User1DAO.deleteUser1", uid);
	}
	
}

