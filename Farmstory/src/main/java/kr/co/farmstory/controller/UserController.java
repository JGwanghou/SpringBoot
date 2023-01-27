package kr.co.farmstory.controller;

import kr.co.farmstory.repository.UserRepo;
import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepo repo;

    @GetMapping("user/login")
    public String login(){

        return "user/login";
    }

    @GetMapping("user/terms")
    public String terms(Model model){
        TermsVO tvo = service.selectTerms();
        model.addAttribute("tvo", tvo);
        return "user/terms";
    }

    @GetMapping("user/register")
    public String register(){return "user/register";}

    @PostMapping("user/register")
    public String register(UserVO vo){
        service.insertUser(vo);
        return "redirect:/user/login";
    }

    @GetMapping("user/checkUid")
    @ResponseBody
    public Map<String, Integer> checkUid(@RequestParam("uid") String uid) { // 이 주소로 넘어오는 파라미터 기재

        log.info("uid : " + uid);
        int result = repo.countByUid(uid);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    @GetMapping("user/checkNick")
    @ResponseBody
    public Map<String, Integer> checkNick(@RequestParam("nick") String nick){
        log.info("nick : " + nick);
        int result = repo.countByNick(nick);

        Map<String, Integer> resultMapNick = new HashMap<>();
        resultMapNick.put("result", result);

        return resultMapNick;
    }
}
