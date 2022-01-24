package com.icia.sej.controller;

import com.icia.sej.dto.CommentDetailDTO;
import com.icia.sej.dto.CommentSaveDTO;
import com.icia.sej.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment/*")
public class CommentController {
    private final CommentService cs;

    @PostMapping("save")
    public @ResponseBody List<CommentDetailDTO> save(@ModelAttribute CommentSaveDTO commentSaveDTO) {
//        cs.save(commentSaveDTO);
        Long commentId = cs.save(commentSaveDTO);
        List<CommentDetailDTO> commentList = cs.findAll(commentSaveDTO.getBoardId());
//        model.addAttribute("comment", commentList);
        return commentList;
    }
}
