package com.icia.sej.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDTO {

    private String memberId;

    @NotBlank
    private String memberEmail;

    @NotBlank
    private String memberPassword;
}
