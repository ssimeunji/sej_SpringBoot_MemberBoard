package com.icia.sej.controller;

import com.icia.sej.common.PagingConst;
import com.icia.sej.dto.*;
import com.icia.sej.entity.BoardEntity;
import com.icia.sej.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j // 로그를 기록할 수 있는 라이브러리(어노테이션)
@RequestMapping("/board/*")
public class BoardController {
    private final BoardService bs;

    // 등록
    @GetMapping("save")
    public String saveForm(Model model) {
        model.addAttribute("board" , new BoardSaveDTO());
        return "board/save";
    }
    @PostMapping("save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException {
        Long boardId = bs.save(boardSaveDTO);
        return "redirect:/board/paging";
    }

    // 페이징처리된 전체목록
    @GetMapping("paging")
    public String paging(@PageableDefault(page =1) Pageable pageable, Model model) {
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/paging";
    }

    // 상세조회
    @GetMapping("{boardId}")
    public String findById(@PathVariable Long boardId, Model model) {
        BoardDetailDTO boardDetailDTO = bs.findById(boardId);
        model.addAttribute("board", boardDetailDTO);
        model.addAttribute("comment", boardDetailDTO.getCommentList());
        return "board/findById";

    }

    // 수정
    @GetMapping("update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model) {
        BoardDetailDTO boardDetailDTO = bs.findById(boardId);
        model.addAttribute("board", boardDetailDTO);
        return "board/update";
    }
    @PostMapping("update")
    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO) {
        bs.update(boardUpdateDTO);
//        return "redirect:/board/paging"+boardUpdateDTO.getBoardId();
        return "redirect:/board/findById"+boardUpdateDTO.getBoardId();
    }

    // 삭제
    @GetMapping("delete/{boardId}")
    public String deleteById(@PathVariable("boardId") Long boardId) {
        bs.deleteById(boardId);
        return "redirect:/board/paging";
    }

    // 검색
    @GetMapping("search")
    public String search(@ModelAttribute BoardSearchDTO boardSearchDTO, Model model) {
        List<BoardDetailDTO> boardDetailDTOList = bs.search(boardSearchDTO);
        model.addAttribute("boardList", boardDetailDTOList);
        return "board/search";
    }


}
