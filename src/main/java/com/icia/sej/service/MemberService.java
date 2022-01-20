package com.icia.sej.service;

import com.icia.sej.dto.MemberDetailDTO;
import com.icia.sej.dto.MemberLoginDTO;
import com.icia.sej.dto.MemberSaveDTO;

import java.util.List;

public interface MemberService {

    // 회원가입
    Long save(MemberSaveDTO memberSaveDTO);

    // 로그인
    boolean login(MemberLoginDTO memberLoginDTO);

    // 전체목록
    List<MemberDetailDTO> findAll();

    // 상세조회
    MemberDetailDTO findById(Long memberId);

    // 삭제
    void deleteById(Long memberId);

}
