package kr.co.farmstory.controller;

import kr.co.farmstory.service.CommentService;
import kr.co.farmstory.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("board/writeComment")
    @ResponseBody
    public int writeComment(CommentVO vo){
        log.info("insertComment Controller...");

        int result = service.insertComment(vo);
        log.info("result : " + result);
        return result;
    }

}
