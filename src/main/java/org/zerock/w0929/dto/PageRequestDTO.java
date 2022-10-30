package org.zerock.w0929.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    private String keyword;

    private LocalDate from, to;

    private boolean complete;

    public int getSkip(){
        size = size <= 10? 10: size >= 100? 100:size;

        return (page -1) * size;
    }

}
