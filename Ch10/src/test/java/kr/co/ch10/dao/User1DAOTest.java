package kr.co.ch10.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.ch10.vo.User1VO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class User1DAOTest {

	@Autowired
	User1DAO dao;
	
	@Test
	public void insert() {
		
		User1VO user = new User1VO();
		user.setUid("b202");
		user.setName("무길동");
		user.setHp("010-2139-1293");
		user.setAge(42);
		
		dao.insertUser1(user);
	}
	@Test
	public void select() {
		User1VO user = dao.selectUser1("A101");
		
		log.info(user.toString());
	}
}
