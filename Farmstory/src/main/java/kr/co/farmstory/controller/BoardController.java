package kr.co.farmstory.controller;

import kr.co.farmstory.Entity.UserEntity;
import kr.co.farmstory.security.MyUserDetails;
import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.service.CommentService;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private ArticleService service;
    @Autowired
    private CommentService cmtservice;

    @GetMapping("board/list")
    public String list(Model model, String group, String cate, String pg){

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);

        int total = service.getTotalCount();
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<ArticleVO> articles = service.selectArticles(cate, start);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("articles", articles);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/list";
    }

    @GetMapping("board/modify")
    public String modify(Model model, int no, String group, String cate){
        ArticleVO article = service.selectArticle(no);

        model.addAttribute("article", article);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/modify";
    }
    @PostMapping("board/modify")
    public String modify(String group, ArticleVO vo) {
        int result = service.updateArticle(vo);
        log.info("result : "+result);
        return "redirect:/board/list?group="+group+"&cate="+vo.getCate();
    }

    @GetMapping("board/view")
    public String view(@AuthenticationPrincipal MyUserDetails myUser, Model model,String group, String cate, String pg, int no){
        UserEntity user = myUser.getUser();
        ArticleVO article = service.selectArticle(no);

        model.addAttribute("user", user);
        model.addAttribute("article", article);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("pg", pg);

        CommentVO cmt = cmtservice.selectComment(no);
        model.addAttribute("cmt", cmt);
        return "board/view";
    }
    @GetMapping("board/write")
    public String write(@AuthenticationPrincipal MyUserDetails myUser, Model model, String group, String cate){
        UserEntity user = myUser.getUser();
        model.addAttribute("user", user);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);

        return "board/write";
    }
    @PostMapping("board/write")
    public String write(String group, ArticleVO vo){
        service.insertArticle(vo);
        return "redirect:/board/list?group="+group+"&cate="+vo.getCate();
    }

    @GetMapping("board/delete")
    public String delete(String group, String cate, int no){
        service.deleteArticle(no);

        return "redirect:/board/list?group="+group+"&cate="+cate;
    }

}
