package com.icia.sej;

import com.icia.sej.dto.BoardSaveDTO;
import com.icia.sej.dto.MemberSaveDTO;
import com.icia.sej.entity.BoardEntity;
import com.icia.sej.entity.MemberEntity;
import com.icia.sej.repository.BoardRepository;
import com.icia.sej.service.BoardService;
import com.icia.sej.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardTest {
    @Autowired
    private MemberService ms;

    @Autowired
    private BoardService bs;

    @Autowired
    private BoardRepository br;

//    @Test
//    @DisplayName("회원가입테스트")
//    public void memberSaveTest() {
//        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
//        memberSaveDTO.setMemberEmail("테스트회원이메일");
//        memberSaveDTO.setMemberPassword("테스트비번");
//        memberSaveDTO.setMemberName("테스트회원이름");
//        memberSaveDTO.setMemberPhone("테스트회원전화번호");
//
//        ms.save(memberSaveDTO);
//
//    }
//
//    @Test
//    @DisplayName("글작성 30개")
//    public void boardSaveTest30() {
//        IntStream.rangeClosed(1, 30).forEach(i -> {
//            ms.save(new MemberSaveDTO("이메일"+i, "비번"+i, "이름"+i, "전화번호"+i));
//        });
//        IntStream.rangeClosed(1, 30).forEach(i -> {
//            bs.save(new BoardSaveDTO("이메일"+i,"제목"+i,"내용"+i));
//        });
//    }


}
