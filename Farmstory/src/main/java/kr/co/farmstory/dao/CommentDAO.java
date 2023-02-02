package kr.co.farmstory.dao;

import kr.co.farmstory.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDAO {

    public int insertComment(CommentVO vo);
    public CommentVO selectComment(int no);
    public List<CommentVO> selectComments();
    public void updateComment();
    public void deleteComment();

}
