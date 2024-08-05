package com.rsupport.notice.controller;

import com.rsupport.notice.dto.exception.ResponseDTO;
import com.rsupport.notice.model.Notice;
import com.rsupport.notice.dto.notice.NoticeDTO;
import com.rsupport.notice.service.AttachService;
import com.rsupport.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Value("${file.dir}")
    String fileDir;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private AttachService attachService;

    /**
     * 공지사항 전체 항목 조회
     *
     * @return 전체 공지사항 목록을 List로 반환
     */
    @GetMapping()
    public ResponseEntity<?> getAllNotice(){
        return ResponseEntity.ok(noticeService.getAllNotices());
    }

    /**
     * 공지사항 id로 한 건 조회
     *
     * @param no 조회 할 글 번호(id)
     * @return
     */
    @GetMapping("/{no}")
    public ResponseDTO<?> getNotice(@PathVariable Long no) {
        NoticeDTO noticeDTO = noticeService.getNotice(no);

        return ResponseDTO.ok(noticeDTO);
    }

    /**
     * 글 삭제 API
     *
     * 첨부파일도 삭제
     *
     * @param no 삭제 할 굴의 id
     * @return 삭제에 성공한다면 "SUCCESS", 실패한다면 "FAIL" 반환
     */
    @DeleteMapping("/{no}")
    public ResponseDTO<?> deleteNotice(@PathVariable Long no) throws IOException {
        noticeService.deleteNotice(no);

        attachService.deleteAllAttach(no);

        return ResponseDTO.ok("ID NO : " + no + " >>> DELETED");
    }

    /**
     * 글 작성 API
     *
     * @return
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDTO<?> insertNotice(@RequestPart Notice notice,
                                       @RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        Long noticeNo = noticeService.insertNotice(notice);

        attachService.insertFile(files, noticeNo);

        return ResponseDTO.ok("NEW NOTICE >>> INSERTED");
    }

    /**
     * 글 수정 API
     *
     * FILE도 수정해야 함
     *
     * @param no 수정할 글 번호(id)
     * @param notice 수정할 내용 담긴 dto
     * @return
     */
    @PutMapping(value = "/{no}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDTO<?> updateNotice(@PathVariable Long no, @RequestPart Notice notice,
                                       @RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        noticeService.updateNotice(no, notice);

        attachService.insertFile(files, no);

        return ResponseDTO.ok("ID NO : " + no + " >>> UPDATED");
    }
}
