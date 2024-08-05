package com.rsupport.notice.service;

import com.rsupport.notice.exception.CommonException;
import com.rsupport.notice.exception.ErrCode;
import com.rsupport.notice.model.Attach;
import com.rsupport.notice.model.Notice;
import com.rsupport.notice.dto.notice.NoticeDTO;
import com.rsupport.notice.repository.AttachRepository;
import com.rsupport.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    @Autowired
    private final NoticeRepository noticeRepository;

    @Autowired
    private final AttachRepository attachRepository;

    @Autowired
    private final AttachService attachService;

    /**
     * NOTICE ENTITY를 NoticeDTO로 변환
     *
     * @param notice DTO로 변환할 param
     * @return DTO로 변환된 NOTICE ENTITY
     */
    private NoticeDTO convertToDto(Notice notice){
        NoticeDTO noticeDTO = NoticeDTO.buildEntity(notice);

        return noticeDTO;
    }

    /**
     * 공지사항 전체 조회
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<NoticeDTO> getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();

        return NoticeDTO.buildListEntity(notices);
    }

    /**
     * 글 번호(id)로 해당 글 조회하는 함수
     *
     * @param no 조회할 글 번호(id)
     * @return 해당 글 존재할 경우 NoticeDTO, 존재하지 않을 경우 NULL
     */
    @Transactional
    public NoticeDTO getNotice(Long no) {
        Notice notice = noticeRepository.findById(no)
                .orElseThrow(() -> {
                    throw new CommonException(ErrCode.NOTICE_NOT_FOUND);
                });

        notice.setHit(notice.getHit() + 1); // 조회수 증가
        noticeRepository.save(notice); // 업데이트된 공지사항 저장

        return convertToDto(notice);
    }


    /**
     * 글 삭제
     *
     * @param no 삭제할 글 번호(pk)
     */
    @Transactional
    public void deleteNotice(Long no) throws IOException {
        noticeRepository.findById(no)
                .orElseThrow(() -> {
                    throw new CommonException(ErrCode.ALREADY_DELETED);
                });

        // 공지사항 삭제
        noticeRepository.deleteById(no);

        List<Attach> attaches = attachRepository.findByNoticeNo(no);

        for (Attach attach :
             attaches) {
            attachService.deleteAllAttach(attach.getNoticeNo());
            attachRepository.delete(attach);
        }
    }


    /**
     * 현재 시간 조회
     *
     * @return LocalDateTime 타입으로 현재 시간 리턴
     */
    private LocalDateTime getTime(){
        return LocalDateTime.now();
    }

    /**
     * 글 작성
     *
     *
     * @param notice
     * @return
     */
    @Transactional
    public Long insertNotice(Notice notice){
        Notice noticeEntity = Notice.builder()
                        .title(notice.getTitle())
                        .writer(notice.getWriter())
                        .text(notice.getText())
                        .writeDate(getTime())
                        .startDate(notice.getStartDate())
                        .endDate(notice.getEndDate())
                        .hit(0)
                        .build();

        return noticeRepository.save(noticeEntity).getNo();
    }

    /**
     * 글 수정
     *
     * @param no 수정할 글 번호(id)
     * @param notice
     */
    @Transactional
    public void updateNotice(Long no, Notice notice){
        Notice noticeEntity = noticeRepository.findById(no)
                .orElseThrow(() -> {
                    throw new CommonException(ErrCode.NOTICE_NOT_FOUND);
                });

        noticeEntity.setTitle(notice.getTitle());
        noticeEntity.setText(notice.getText());
        noticeEntity.setStartDate(notice.getStartDate());
        noticeEntity.setEndDate(notice.getEndDate());

        noticeRepository.save(noticeEntity);

        // 파일 있는지 찾기
        // 기존파일 삭제하고 다시등록

    }
}
