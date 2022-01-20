package com.icia.sej.dto;

import com.icia.sej.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;

    public static MemberDetailDTO toMemberDetailDTO(MemberEntity memberEntity) {
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(memberEntity.getId());
        memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDetailDTO.setMemberName(memberEntity.getMemberName());
        memberDetailDTO.setMemberPhone(memberEntity.getMemberPhone());
        return memberDetailDTO;
    }

    public static List<MemberDetailDTO> change(List<MemberEntity> memberEntityList) {
        List<MemberDetailDTO> memberDetailDTOList = new ArrayList<>();
        for (MemberEntity m: memberEntityList) {
            memberDetailDTOList.add(toMemberDetailDTO(m));
        }
        return memberDetailDTOList;
    }
}
