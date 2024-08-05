package com.rsupport.notice.service;

import com.rsupport.notice.model.Attach;
import com.rsupport.notice.repository.AttachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachService {
    @Value("${file.dir}")
    String fileDir;

    @Autowired
    private final AttachRepository attachRepository;

    /**
     * 첨부파일 등록
     *
     * @param files
     * @param noticeNo
     */
    @Transactional
    public void insertFile(List<MultipartFile> files, Long noticeNo) throws IOException {
        if (files != null && !files.isEmpty()){
            deleteAllAttach(noticeNo);  // DB에서 첨부파일 삭제

            Path uploadPath = Paths.get(fileDir, String.valueOf(noticeNo));

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            for(MultipartFile file : files){
                String oriName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String ext = oriName.substring(oriName.lastIndexOf("."));
                String saveName = uuid + ext;
                String savePath = uploadPath + "\\" + saveName;

                Attach attachEntity = new Attach();

                attachEntity.setNoticeNo(noticeNo);
                attachEntity.setOriName(oriName);
                attachEntity.setSaveName(saveName);
                attachEntity.setFileExt(ext);
                attachEntity.setSaveName(saveName);
                attachEntity.setSavePath(savePath);

                attachRepository.save(attachEntity);

                file.transferTo(new File(savePath));
            }
        }
    }

    @Transactional
    public void deleteAllAttach(Long noticeNo) throws IOException {
        List<Attach> files = attachRepository.findByNoticeNo(noticeNo);
        
        if (!files.isEmpty()){
            attachRepository.deleteAllByNoticeNo(noticeNo);
        }
        
        Path path = Paths.get(fileDir, String.valueOf(noticeNo));

        for (Attach file :
                files) {
            System.out.println(file.getSavePath());

            Files.delete(Path.of(file.getSavePath()));
        }

    }


}
