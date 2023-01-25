package kr.co.farmstory.dao;

import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleDAO {

    public int insertArticle(ArticleVO vo);
    public void insertFile(FileVO vo);
    public FileVO selectFile(int fno);
    public int selectCountTotal();
    public ArticleVO selectArticle(int no);
    public List<ArticleVO> selectArticles(int start);
    public int updateArticle(ArticleVO vo);
    public int updateDownload(int fno);
    public int deleteArticle(int no);

}
