package com.icia.sej;

import com.icia.sej.dto.BoardSaveDTO;
import com.icia.sej.repository.BoardRepository;
import com.icia.sej.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService bs;

    @Autowired
    private BoardRepository br;

    @Test
    @DisplayName("글작성 30개")
    public void boardSaveTest30() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            bs.save(new BoardSaveDTO("작성자"+i,"제목"+i,"내용"+i));
        });
    }

}
