package kr.co.todo.controller;

import kr.co.todo.service.TodoService;
import kr.co.todo.vo.TodoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private TodoService service;

    @GetMapping(value = {"/", "index"})
    public String index(Model model){
        List<TodoVO> articles = service.selectTodo();

        model.addAttribute("articles", articles);

        return "index";
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(@RequestParam("value")String value){
        int result = 0;
        result = service.insertTodo(value);

        Map<String, Object> map = new HashMap<>();
        map.put("result", result);

        return map;
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Map<String, String> data){
        int result = 0;

        result = service.updateTodoList(data);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);

        log.info("map : "+map);
        // 리턴
        return map;
    }

    @GetMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("no") String no){
        int result = 0;
        result = service.deleteTodo(no);

        Map<String, Object> map = new HashMap<>();
        map.put("result", result);

        return map;

    }


}
