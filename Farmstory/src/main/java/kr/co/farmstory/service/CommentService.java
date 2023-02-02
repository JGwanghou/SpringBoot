package kr.co.farmstory.service;

import kr.co.farmstory.dao.CommentDAO;
import kr.co.farmstory.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDAO dao;

    public int insertComment(CommentVO vo){
        int result = dao.insertComment(vo);
        return result;
    };

    public CommentVO selectComment(int parent){
        return dao.selectComment(parent);
    }

    public List<CommentVO> selectComments(){
        List<CommentVO> comment =  dao.selectComments();
        return comment;
    };
    public void updateComment(){};
    public void deleteComment(){};
}
