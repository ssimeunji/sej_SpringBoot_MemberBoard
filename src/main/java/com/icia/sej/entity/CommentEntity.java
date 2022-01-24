package com.icia.sej.entity;

import com.icia.sej.dto.CommentDetailDTO;
import com.icia.sej.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String commentWriter;

    @Column
    private String commentContents;

    // 원 글의 게시글 번호를 참조하기 위한 설정(댓글:게시글 = N:1)
    // 게시글 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    // 회원 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


    public static CommentEntity toCommentSaveEntity(CommentSaveDTO commentSaveDTO, BoardEntity boardEntity, MemberEntity memberEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(memberEntity.getMemberEmail());
        commentEntity.setCommentContents(commentSaveDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setMemberEntity(memberEntity);
        return commentEntity;
    }
    public static List<CommentDetailDTO> toCommentEntityList(List<CommentEntity> commentEntityList) {
        List<CommentDetailDTO> commentDetailDTOList = new ArrayList<>();
        for (CommentEntity c: commentEntityList) {
            commentDetailDTOList.add(CommentDetailDTO.toCommentDetailDTO(c));
        }
        return commentDetailDTOList;
    }
}
