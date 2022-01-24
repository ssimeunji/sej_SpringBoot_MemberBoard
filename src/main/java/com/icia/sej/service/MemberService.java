package com.icia.sej.service;

import com.icia.sej.dto.MemberDetailDTO;
import com.icia.sej.dto.MemberLoginDTO;
import com.icia.sej.dto.MemberSaveDTO;

import java.io.IOException;
import java.util.List;

public interface MemberService {

    // 회원가입
    Long save(MemberSaveDTO memberSaveDTO) throws IOException;
    // 이메일 중복체크
    String emailDuplicate(String memberEmail);

//    // 로그인
//    boolean login(MemberLoginDTO memberLoginDTO);

    // 전체목록
    List<MemberDetailDTO> findAll();

    // 상세조회
    MemberDetailDTO findById(Long memberId);

    // 삭제
    void deleteById(Long memberId);

    // 수정화면
    MemberDetailDTO findByEmail(String memberEmail);
    // 수정
    Long update(MemberDetailDTO memberDetailDTO);


    MemberDetailDTO findByEmail(MemberLoginDTO memberLoginDTO);
}
