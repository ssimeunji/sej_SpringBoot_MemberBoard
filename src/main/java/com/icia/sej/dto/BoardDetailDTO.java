package com.icia.sej.dto;

import com.icia.sej.entity.BoardEntity;
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
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Column(columnDefinition = "integer default 0")
    private int count;

    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity) {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setCreateTime(boardEntity.getCreateTime());
        boardDetailDTO.setUpdateTime(boardEntity.getUpdateTime());
        return boardDetailDTO;
    }

    public static List<BoardDetailDTO> toBoardDetailDTOList(List<BoardEntity> boardEntityList) {
        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for(BoardEntity b: boardEntityList) {
            boardDetailDTOList.add(toBoardDetailDTO(b));
        }
        return boardDetailDTOList;
    }

}
