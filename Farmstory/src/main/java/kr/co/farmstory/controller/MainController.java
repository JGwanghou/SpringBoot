package kr.co.farmstory.controller;

import kr.co.farmstory.Entity.UserEntity;
import kr.co.farmstory.security.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "index"})
    public String index(){

        return "index";
    }
}
