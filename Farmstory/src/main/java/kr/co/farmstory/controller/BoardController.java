package kr.co.farmstory.controller;

import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private ArticleService service;

    @GetMapping("board/list")
    public String list(Model model, String group, String cate){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/list";
    }
    @GetMapping("board/modify")
    public String modify(){
        return "board/modify";
    }
    @GetMapping("board/view")
    public String view(){
        return "board/view";
    }
    @GetMapping("board/write")
    public String write(Model model, String group, String cate){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);

        return "board/write";
    }
    @PostMapping("board/write")
    public String write(String group, ArticleVO vo){
        service.insertArticle(vo);
        return "redirect:/board/list";
    }
}
