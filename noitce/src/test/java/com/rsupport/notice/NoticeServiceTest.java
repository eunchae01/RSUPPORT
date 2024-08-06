package com.rsupport.notice;

import com.rsupport.notice.model.Notice;
import com.rsupport.notice.repository.NoticeRepository;
import com.rsupport.notice.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Transactional
@SpringBootTest
public class NoticeServiceTest {
    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private NoticeService noticeService;

    @Test
    void testGetNotice(){
        Notice noticeEntity = new Notice();

        noticeEntity.setNo(1L);
        noticeEntity.setTitle("1");
        noticeEntity.setText("text");
        noticeEntity.setWriter("writer");
        noticeEntity.setWriteDate(LocalDateTime.now());
        noticeEntity.setStartDate(new Date(2022, 8, 8));
        noticeEntity.setEndDate(new Date(2022, 8, 8));
        noticeEntity.setHit(0);

        when(noticeRepository.save(any(Notice.class))).thenReturn(noticeEntity);

        Long insertedNoticeId = noticeService.insertNotice(noticeEntity);

        // THEN
        assertThat(insertedNoticeId).isNotNull();
        assertThat(insertedNoticeId).isEqualTo(1L);
    }
}
