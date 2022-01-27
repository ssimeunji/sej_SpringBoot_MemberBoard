package com.icia.sej.repository;

import com.icia.sej.entity.BoardEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // 검색
    List<BoardEntity> findByBoardWriter(String keyword);
    List<BoardEntity> findByBoardTitle(String keyword);

    // native query
    // jpql(java persistence query language)
    // 반드시 테이블에 대한 약칭을 써야함
    // query dsl
//    @Modifying
//    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id = :boardId")
//    Long boardHits(@Param("boardId") Long boardId);

}
