package com.icia.sej.controller;

import com.icia.sej.common.SessionConst;
import com.icia.sej.dto.MemberDetailDTO;
import com.icia.sej.dto.MemberLoginDTO;
import com.icia.sej.dto.MemberSaveDTO;
import com.icia.sej.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;


import java.io.IOException;
import java.util.List;

import static com.icia.sej.common.SessionConst.LOGIN_EMAIL;
import static com.icia.sej.common.SessionConst.LOGIN_ID;

@Controller
@RequiredArgsConstructor
@Slf4j // 로그를 기록할 수 있는 라이브러리(어노테이션)
@RequestMapping("/member/*")
public class MemberController {

    private final MemberService ms;

    // 회원가입
    @GetMapping("save")
    public String saveForm(Model model) {
        model.addAttribute("member", new MemberSaveDTO());
        return "member/save";
    }
    @PostMapping("save")
    public String save(@Validated @ModelAttribute MemberSaveDTO memberSaveDTO, BindingResult bindingResult) throws IllegalAccessException, IOException {
        if (bindingResult.hasErrors()) {
            return "member/save";
        }
        Long memberId = ms.save(memberSaveDTO);
        return "index";
    }
    @PostMapping("emailDuplicate")
    public ResponseEntity emailDuplicate(@RequestParam("memberEmail") String memberEmail) {
        String result = ms.emailDuplicate(memberEmail);
        if (result.equals("OK")) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    // 로그인
//    @PostMapping("login")
//    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session) {
////        if (ms.login(memberLoginDTO)) {
////            session.setAttribute(LOGIN_EMAIL, memberLoginDTO.getMemberEmail());
////            return "redirect:/board/paging";
////        } else {
////            return "index";
////        }
//        MemberDetailDTO memberDetailDTO = ms.findByEmail(memberLoginDTO);
//        session.setAttribute("memberId", memberDetailDTO.getMemberId());
//        session.setAttribute("memberEamil", memberDetailDTO.getMemberEmail());
//        return "redirect:/board/paging";
//    }
    @PostMapping("login")
    public ResponseEntity login(@RequestBody MemberLoginDTO memberLoginDTO, HttpSession session) {
        MemberDetailDTO memberDetailDTO = ms.findByEmail(memberLoginDTO);
        try {
            if (!memberDetailDTO.equals(null)) {
                if (memberDetailDTO.getMemberEmail().equals("admin")) {
                    session.setAttribute("loginId", memberDetailDTO.getMemberId());
                    session.setAttribute("loginEmail", memberDetailDTO.getMemberEmail());
                    return new ResponseEntity<String>("admin", HttpStatus.OK);
                }
                session.setAttribute("loginId", memberDetailDTO.getMemberId());
                session.setAttribute("loginEmail", memberDetailDTO.getMemberEmail());
                String redirectURL = (String) session.getAttribute("redirectURL");
                if (redirectURL != null) {
                    return new ResponseEntity<String>(redirectURL, HttpStatus.OK);

                } else {
                    return new ResponseEntity<String>("/board/paging", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException nullPointerException) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    // 관리자페이지
    @GetMapping("admin")
    public String adminPage() {
        return "member/admin";
    }

    // 회원 전체목록
    @GetMapping("findAll")
    public String findAll(Model model) {
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList", memberList);
        return "member/findAll";
    }

    // 상세조회
    @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model) {
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "member/findById";
    }

    // 삭제
    @GetMapping("delete/{memberId}")
    public String deleteById(@PathVariable("memberId") Long memberId) {
        ms.deleteById(memberId);
        return "redirect:/member/findAll";
    }

    // 마이페이지
    @GetMapping("myPage/{memberId}")
    public String myPage(@PathVariable("memberId") Long memberId, Model model) {
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "member/myPage";
    }

    // 수정
    @GetMapping("update/{memberId}")
    public String updateForm(Model model, HttpSession session, @PathVariable Long memberId) {
//        String memberEmail = (String) session.getAttribute(LOGIN_EMAIL);
//        MemberDetailDTO member = ms.findByEmail(memberEmail);
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "member/update";
    }
    @PostMapping("update")
    public String update(@ModelAttribute MemberDetailDTO memberDetailDTO) {
        Long memberId = ms.update(memberDetailDTO);
        return "redirect:/member/myPage/"+memberDetailDTO.getMemberId();
    }

}
