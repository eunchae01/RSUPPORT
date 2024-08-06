package com.rsupport.notice.controller;

import com.rsupport.notice.dto.exception.ResponseDTO;
import com.rsupport.notice.model.Attach;
import com.rsupport.notice.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @GetMapping("/{no}")
    public ResponseEntity<?> getAttach(@PathVariable Long no) throws MalformedURLException {
        Attach attach = attachService.getAttach(no);

        UrlResource resource = attachService.getAttachResource(attach.getSavePath());

        String encodedFileName = UriUtils.encode(attach.getOriName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
