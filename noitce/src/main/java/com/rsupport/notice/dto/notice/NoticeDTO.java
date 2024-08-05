package com.rsupport.notice.dto.notice;

import com.rsupport.notice.model.Notice;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private String title;
    private String text;
    private LocalDateTime writeDate;
    private int hit;
    private String writer;

    public static NoticeDTO buildEntity(Notice notice){
        return NoticeDTO.builder()
                .title(notice.getTitle())
                .text(notice.getText())
                .writeDate(notice.getWriteDate())
                .hit(notice.getHit())
                .writer(notice.getWriter())
                .build();
    }

    public static List<NoticeDTO> buildListEntity(List<Notice> noticeList){
        List<NoticeDTO> list = new ArrayList<>();

        for (Notice notice : noticeList){
            list.add(buildEntity(notice));
        }

        return list;
    }
}



