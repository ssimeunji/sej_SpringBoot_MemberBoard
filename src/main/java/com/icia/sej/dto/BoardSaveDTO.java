package com.icia.sej.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {
    @NotBlank
    private String boardWriter;
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContents;

}
