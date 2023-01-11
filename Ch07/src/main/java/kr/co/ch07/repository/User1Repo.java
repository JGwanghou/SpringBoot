package kr.co.ch07.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.ch07.vo.User1VO;

public interface User1Repo extends JpaRepository<User1VO, String>{

	// JPA 쿼리 메서드
	// find+엔티티+조건+컬럼
	public User1VO findUser1VOByUid(String uid);
	public List<User1VO> findUser1VOByName(String name);
	public List<User1VO> findUser1VOByNameNot(String name);
	
	public User1VO findUser1VOByUidAndName(String uid, String name);
	public List<User1VO> findUser1VOByUidOrName(String uid, String name);
	
	public List<User1VO> findUser1VOByAgeGreaterThan(int age);
	public List<User1VO> findUser1VOByAgeGreaterThanEqual(int age);
	public List<User1VO> findUser1VOByAgeLessThan(int age);
	public List<User1VO> findUser1VOByAgeLessThanEqual(int age);
	
	public List<User1VO> findUser1VOByNameLike(String name); // name 앞 또는 뒤 % 명시
	public List<User1VO> findUser1VOByNameContains(String name);
	public List<User1VO> findUser1VOByNameStartsWith(String name); // name__
	public List<User1VO> findUser1VOByNameEndsWith(String name); // __name
	
	// 정렬
	public List<User1VO> findUser1VOByAgeOrderByNameAsc(String name);
	public List<User1VO> findUser1VOByNameOrderByAgeAsc(int age);
	public List<User1VO> findUser1VOByNameOrderByAgeDesc(int age);
	public List<User1VO> findUser1VOByAgeGreaterThanOrderByAgeDesc(int age);
	
	// 카운트
	public int countUser1VOByUid(String uid);
	public int countUser1VOByName(String name);
	
	// JPQL
	// (select * from user1 where age < 30)
	@Query("select u1 from User1VO as u1 where u1.age < 30")
	public List<User1VO> selectUserUnderAge30();
	
	@Query("select u1 from User1VO as u1 where u1.name = ?1") // 여기서 1은 첫번째 파라미터다 라는 뜻 
	public List<User1VO> selectUserByName(String name);
	
	@Query("select u1 from User1VO as u1 where u1.name = :name")
	public List<User1VO> selectUserByNameWithParam(@Param("name") String name);
	
	
	
}