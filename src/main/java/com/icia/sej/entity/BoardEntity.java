package com.icia.sej.entity;

import com.icia.sej.dto.BoardSaveDTO;
import com.icia.sej.dto.BoardUpdateDTO;
import com.icia.sej.repository.BoardRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String boardWriter;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    @Column
    private int hits = 0;

    // 회원 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    // 댓글 연관관계
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static BoardEntity saveBoardEntity(BoardSaveDTO boardSaveDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(memberEntity.getMemberEmail());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    // 수정
    public static BoardEntity updateBoardEntity(BoardUpdateDTO boardUpdateDTO) {
       BoardEntity boardEntity = new BoardEntity();
       boardEntity.setId(boardUpdateDTO.getBoardId());
       boardEntity.setBoardWriter(boardUpdateDTO.getBoardWriter());
       boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
       boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
       return boardEntity;
    }

}
