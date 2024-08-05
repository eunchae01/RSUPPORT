package com.rsupport.notice.repository;

import com.rsupport.notice.model.Attach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachRepository extends JpaRepository<Attach, Long> {
    List<Attach> findByNoticeNo(Long noticeNo);

    void deleteAllByNoticeNo(Long noticeNo);
}
