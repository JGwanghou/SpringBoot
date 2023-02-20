package kr.co.todo.dao;

import kr.co.todo.vo.TodoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TodoDAO {

    public int insertTodo(String tit);
    public List<TodoVO> selectTodo();
    public int updateTodoList(Map<String, String> data);
    public int deleteTodo(String no);
}
