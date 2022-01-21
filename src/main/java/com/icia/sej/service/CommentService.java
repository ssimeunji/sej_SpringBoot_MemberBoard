package com.icia.sej.service;

import com.icia.sej.dto.CommentDetailDTO;
import com.icia.sej.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);
}
