package kr.co.sboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sboard.dao.UserDAO;
import kr.co.sboard.repository.UserRepo;
import kr.co.sboard.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class SboardApplicationTests {

	
	void contextLoads() {
	}

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserRepo repo;
	
	public void insertTest() {
		UserVO vo = UserVO.builder()
					.uid("test02")
					.pass1("1234")
					.name("test01")
					.nick("test01")
					.email("test01")
					.hp("test01")
					.build();
		int result = dao.insertUser(vo);
		
		log.info("result : " + result);
		
	}
	
	@Test
	public void countUid() {
		int result = repo.countByUid("letary");
		
		log.info("result : " + result);
	}
	
	
}
