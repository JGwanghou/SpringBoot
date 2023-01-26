package kr.co.farmstory.service;

import kr.co.farmstory.dao.UserDAO;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    @Autowired
    private PasswordEncoder encoder;

    public int insertUser(UserVO vo){
        // 암호화
        String pass = vo.getPass1();
        String cryptedPass = encoder.encode(pass);
        vo.setPass(cryptedPass);

        return dao.insertUser(vo);
    };
    public UserVO selectUser(String uid){
        return dao.selectUser(uid);
    };
    public List<UserVO> selectUsers(){
        return dao.selectUsers();
    };
    public TermsVO selectTerms(){
        return dao.selectTerms();
    };
    public int updateUser(UserVO vo){
        return dao.updateUser(vo);
    };
    public int deleteUser(String uid){
        return dao.deleteUser(uid);
    };

}
