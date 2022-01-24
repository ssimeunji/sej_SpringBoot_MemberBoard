package com.icia.sej.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {
    private Long memberId;
    @NotBlank
    private String boardWriter;
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContents;

    private MultipartFile boardFile;
    private String boardFileName;

}
