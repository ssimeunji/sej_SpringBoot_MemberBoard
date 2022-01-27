package com.icia.sej.service;

import com.icia.sej.common.PagingConst;
import com.icia.sej.dto.*;
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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;
    private final MemberRepository mr;

    // 등록
    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {
        MultipartFile boardFile = boardSaveDTO.getBoardFile();
        String boardFileName = boardFile.getOriginalFilename();
        boardFileName = System.currentTimeMillis()+"-"+boardFileName;
//        String savePath = "C:/Users/WRAPCORE/Desktop/icia/20220118_심은지_SpringBoot_회원제게시판/sej_SpringBoot_MemberBoard-main/src/main/resources/static/image/"+boardFileName;
        String savePath = "C://Users//WRAPCORE//Desktop//icia//20220118_심은지_SpringBoot_회원제게시판//sej_SpringBoot_MemberBoard-main//src//main//resources//static//image//"+boardFileName;
        if (!boardFile.isEmpty()) {
            boardFile.transferTo(new File(savePath));
        }
        boardSaveDTO.setBoardFileName(boardFileName);

        MemberEntity memberEntity = mr.findById(boardSaveDTO.getMemberId()).get();
        BoardEntity boardEntity = BoardEntity.toSaveBoardEntity(boardSaveDTO, memberEntity);
        Long boardId = br.save(boardEntity).getId();
        return boardId;
    }

    // 전체목록 페이징
    @Override
    @Transactional
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));

        Page<BoardPagingDTO> boardList = boardEntities.map(board ->
//                new BoardPagingDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits()));
                new BoardPagingDTO(board.getId(), board.getMemberEntity().getMemberEmail(), board.getBoardTitle(), board.getBoardHits()));
        return boardList;
    }

    // 상세조회
    @Override
    @Transactional
    public BoardDetailDTO findById(Long boardId) {
        // Long boardHits = br.boardHits(boardId);
        BoardEntity boardEntity = br.findById(boardId).get();
        boardEntity.setBoardHits(boardEntity.getBoardHits()+1);
        br.save(boardEntity);
//        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        BoardDetailDTO boardDetailDTO = BoardDetailDTO.toBoardDetailDTOEntity(boardEntity);
//        if (optionalBoardEntity.isPresent()) {
//            BoardEntity boardEntity = optionalBoardEntity.get();
//            boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
//        }

        return boardDetailDTO;
    }

    // 수정
    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        MemberEntity memberEntity = mr.findById(boardUpdateDTO.getMemberId()).get();
        BoardEntity boardEntity = BoardEntity.toUpdateBoardEntity(boardUpdateDTO, memberEntity);
        return br.save(boardEntity).getId();

    }

    // 삭제
    @Override
    public void deleteById(Long boardId) {
        br.deleteById(boardId);
    }

    // 검색
    @Override
    public List<BoardDetailDTO> search(BoardSearchDTO boardSearchDTO) {
        if (boardSearchDTO.getSelect().equals("writer")) {
            List<BoardEntity> boardEntityList = br.findByBoardWriter(boardSearchDTO.getKeyword());
            List<BoardDetailDTO> boardDetailDTOList = BoardDetailDTO.toBoardDetailList(boardEntityList);
            return boardDetailDTOList;
        } else {
            List<BoardEntity> boardEntityList = br.findByBoardTitle(boardSearchDTO.getKeyword());
            List<BoardDetailDTO> boardDetailDTOList = BoardDetailDTO.toBoardDetailList(boardEntityList);
            return boardDetailDTOList;
        }
    }


}
