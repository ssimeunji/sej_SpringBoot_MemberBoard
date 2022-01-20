package com.icia.sej.service;

import com.icia.sej.dto.MemberDetailDTO;
import com.icia.sej.dto.MemberLoginDTO;
import com.icia.sej.dto.MemberSaveDTO;
import com.icia.sej.entity.MemberEntity;
import com.icia.sej.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository mr;

    // 회원가입
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);

        return mr.save(memberEntity).getId();
    }

    // 로그인
    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        if (memberEntity != null) {
            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // 전체 목록
    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();
        List<MemberDetailDTO> memberDetailDTOList = MemberDetailDTO.change(memberEntityList);
        return memberDetailDTOList;
    }

    // 상세조회
    @Override
    public MemberDetailDTO findById(Long memberId) {
        return MemberDetailDTO.toMemberDetailDTO(mr.findById(memberId).get());
    }

    // 삭제
    @Override
    public void deleteById(Long memberId) {
        mr.deleteById(memberId);

    }

}
