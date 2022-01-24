package com.icia.sej.service;

import com.icia.sej.dto.MemberDetailDTO;
import com.icia.sej.dto.MemberLoginDTO;
import com.icia.sej.dto.MemberSaveDTO;
import com.icia.sej.entity.MemberEntity;
import com.icia.sej.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository mr;

    // 회원가입
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IOException {
        MultipartFile memberFile = memberSaveDTO.getMemberFile();
        String memberFileName = memberFile.getOriginalFilename();
        memberFileName = System.currentTimeMillis()+"_"+memberFileName;
        String savePath = "C:\\Users\\WRAPCORE\\Desktop\\icia\\20220118_심은지_SpringBoot_회원제게시판\\MemberBoard\\src\\main\\resources\\static\\image\\"+memberFileName;
        if (!memberFile.isEmpty()) {
            memberFile.transferTo(new File(savePath));
        }
        memberSaveDTO.setMemberFileName(memberFileName);
        MemberEntity memberEntity = MemberEntity.saveMemberEntity(memberSaveDTO);

        return mr.save(memberEntity).getId();
    }

    @Override
    public String emailDuplicate(String memberEmail) {
        String result = "NO";
        try {
            MemberEntity memberEntity = mr.findByMemberEmail(memberEmail);
            if (memberEntity.equals(null)) {
                result = "OK";
                return result;
            } else {
                return result;
            }
        } catch (NullPointerException nullPointerException) {
            result = "OK";
            return result;
        }
    }

//    // 로그인
//    @Override
//    public boolean login(MemberLoginDTO memberLoginDTO) {
//        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
//        if (memberEntity != null) {
//            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    // 전체 목록
    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();
        List<MemberDetailDTO> memberDetailDTOList = MemberDetailDTO.toMemberDetailDTOList(memberEntityList);
        return memberDetailDTOList;
    }

    // 상세조회
    @Override
    public MemberDetailDTO findById(Long memberId) {
        return MemberDetailDTO.toMemberDetailDTOEntity(mr.findById(memberId).get());
    }

    // 삭제
    @Override
    public void deleteById(Long memberId) {
        mr.deleteById(memberId);

    }

    // 수정화면
    @Override
    public MemberDetailDTO findByEmail(String memberEmail) {
        MemberEntity memberEntity =mr.findByMemberEmail(memberEmail);
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTOEntity(memberEntity);
        return memberDetailDTO;
    }
    // 수정
    @Override
    public Long update(MemberDetailDTO memberDetailDTO) {
        MemberEntity memberEntity = MemberEntity.toUpdateMember(memberDetailDTO);
        Long memberId = mr.save(memberEntity).getId();
        return memberId;
    }

    @Override
    public MemberDetailDTO findByEmail(MemberLoginDTO memberLoginDTO) {
        try {
            MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
                MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTOEntity(memberEntity);
                return memberDetailDTO;
            } else {
                return null;
            }
        } catch (NullPointerException nullPointerException) {
            return null;
        }
    }

}
