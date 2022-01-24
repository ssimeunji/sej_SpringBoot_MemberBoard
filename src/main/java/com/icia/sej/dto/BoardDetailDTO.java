package com.icia.sej.dto;

import com.icia.sej.entity.BoardEntity;
import com.icia.sej.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {

    private Long boardId;
    private Long memberId;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String boardFileName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<CommentDetailDTO> commentList;
    private int boardHits;

    public static BoardDetailDTO toBoardDetailDTOEntity(BoardEntity boardEntity) {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardFileName(boardEntity.getBoardFileName());
        boardDetailDTO.setMemberId(boardEntity.getMemberEntity().getId());
        boardDetailDTO.setCreateTime(boardEntity.getCreateTime());
        boardDetailDTO.setUpdateTime(boardEntity.getUpdateTime());
        boardDetailDTO.setBoardHits(boardEntity.getBoardHits());
        boardDetailDTO.setCommentList(CommentEntity.toCommentEntityList(boardEntity.getCommentEntityList()));
        return boardDetailDTO;
    }

    public static List<BoardDetailDTO> toBoardDetailList(List<BoardEntity> boardEntityList) {
        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for (BoardEntity b: boardEntityList) {
            boardDetailDTOList.add(toBoardDetailDTOEntity(b));
        }
        return boardDetailDTOList;
    }

}
