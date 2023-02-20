package kr.co.todo.service;

import kr.co.todo.dao.TodoDAO;
import kr.co.todo.vo.TodoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    @Autowired
    private TodoDAO dao;

    public Integer insertTodo(String tit){
        return dao.insertTodo(tit);
    };

    public List<TodoVO> selectTodo(){
        return dao.selectTodo();
    };

    public int updateTodoList(Map<String, String> data){
        return dao.updateTodoList(data);
    }

    public Integer deleteTodo(String no){
        return dao.deleteTodo(no);
    }
}
