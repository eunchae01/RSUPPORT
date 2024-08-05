package com.rsupport.notice;

import com.rsupport.notice.model.Notice;
import com.rsupport.notice.repository.NoticeRepository;
import com.rsupport.notice.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class NoticeServiceTest {
    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private NoticeService noticeService;

    @Test
    public void testGetAllNotice(){
        List<Notice> noticeList = new ArrayList<>();

        /*noticeList.add(new Notice(1, "title", "1", "2", "3", "4", "5", 0));

        when(noticeRepository.findAll()).thenReturn(noticeList);

        List<NoticeDTO> result = noticeService.getAllNotices();

        assertEquals(1, result.size());
        assertEquals("title", result.get(0).getTitle());*/
    }
}
