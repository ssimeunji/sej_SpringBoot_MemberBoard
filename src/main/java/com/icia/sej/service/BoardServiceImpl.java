package com.icia.sej.service;

import com.icia.sej.common.PagingConst;
import com.icia.sej.dto.BoardDetailDTO;
import com.icia.sej.dto.BoardPagingDTO;
import com.icia.sej.dto.BoardSaveDTO;
import com.icia.sej.dto.BoardUpdateDTO;
import com.icia.sej.entity.BoardEntity;
import com.icia.sej.entity.MemberEntity;
import com.icia.sej.repository.BoardRepository;
import com.icia.sej.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;
    private final MemberRepository mr;

    // 등록
    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(boardSaveDTO.getBoardWriter());
        BoardEntity boardEntity = BoardEntity.saveBoardEntity(boardSaveDTO, memberEntity);
        Long boardId = br.save(boardEntity).getId();
        return boardId;
    }

//    // 전체 목록
//    @Override
//    public List<BoardDetailDTO> findAll() {
//        List<BoardEntity> boardEntityList = br.findAll();
//        List<BoardDetailDTO> boardDetailDTOList = BoardDetailDTO.toBoardDetailDTOList(boardEntityList);
//        return boardDetailDTOList;
//    }

    // 전체목록 페이징
    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));

        Page<BoardPagingDTO> boardList = boardEntities.map(board -> new BoardPagingDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle()));
        return boardList;
    }

    // 상세조회
    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        BoardDetailDTO boardDetailDTO = null;
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
        }
        return boardDetailDTO;
    }

    // 수정
    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = BoardEntity.updateBoardEntity(boardUpdateDTO);
        return br.save(boardEntity).getId();

    }

    // 삭제
    @Override
    public void deleteById(Long boardId) {
        br.deleteById(boardId);
    }

}
