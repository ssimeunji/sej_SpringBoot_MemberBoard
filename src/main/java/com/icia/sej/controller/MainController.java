package com.icia.sej.controller;

import com.icia.sej.dto.MemberLoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j // 로그를 기록할 수 있는 라이브러리(어노테이션)
public class MainController {
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("member", new MemberLoginDTO());
        return "index";
    }
}
