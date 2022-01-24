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

import static org.mybatis.spring.SqlSessionUtils.getSqlSession;

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
    private String boardFileName;

    @Column
    private int boardHits;

    // 회원 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


    // 댓글 연관관계
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static BoardEntity toSaveBoardEntity(BoardSaveDTO boardSaveDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(memberEntity.getMemberEmail());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardFileName(boardSaveDTO.getBoardFileName());
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setBoardHits(0);
        return boardEntity;
    }

    // 수정
    public static BoardEntity toUpdateBoardEntity(BoardUpdateDTO boardUpdateDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardUpdateDTO.getBoardId());
        boardEntity.setBoardWriter(memberEntity.getMemberEmail());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
        boardEntity.setBoardFileName(boardUpdateDTO.getBoardFileName());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

//    public void boardHitsUpdate(int num) {
//        getSqlSession().update("boardHitsUpdate", num);
//    }

}
