package com.icia.sej.service;

import com.icia.sej.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    // 등록
    Long save(BoardSaveDTO boardSaveDTO) throws IOException;

    // 전체목록 페이징
    Page<BoardPagingDTO> paging(Pageable pageable);

    // 상세조회
//    BoardDetailDTO findById(Long boardId, int boardHits);
    BoardDetailDTO findById(Long boardId);

    // 수정
    Long update(BoardUpdateDTO boardUpdateDTO);

    // 삭제
    void deleteById(Long boardId);

    // 검색
    List<BoardDetailDTO> search(BoardSearchDTO boardSearchDTO);
}
