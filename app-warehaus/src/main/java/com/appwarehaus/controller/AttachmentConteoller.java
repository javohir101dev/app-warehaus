package com.appwarehaus.controller;

import com.appwarehaus.entity.Attachment;
import com.appwarehaus.entity.AttachmentContent;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.AttachmentContentRepository;
import com.appwarehaus.repository.AttachmentRepository;
import com.appwarehaus.service.AttchmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentConteoller {

//    @Autowired
    private final AttchmentService attchmentService;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

//    CREATE
    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) {
        return attchmentService.uploadFile(request);
    }

//    READ
    @GetMapping("/{id}")
    public AttachmentContent getAttachmentById(@PathVariable Integer id){
        return attchmentService.getAttachmentById(id);
    }
    @GetMapping("/download/{id}")
    public void downloadDBFileById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(attachment.getId());
            if (optionalAttachment.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();

//                Fileni nomini berish uchun
                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");

//                Fileni content typeni berish uchun
                response.setContentType(attachment.getContentType());

//                MAincontentni olib responsega yozish uchun
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }
        }
    }

//    UPDATE
    @PutMapping("/{id}")
    public Result editAttachmentById(@PathVariable Integer id, MultipartHttpServletRequest request){
        return attchmentService.editAttachment(id, request);
    }

//    DELETE
    @DeleteMapping("/{id}")
    public Result deleteAttachmentById(@PathVariable Integer id){
        return attchmentService.deleteAttachment(id);
    }




}
