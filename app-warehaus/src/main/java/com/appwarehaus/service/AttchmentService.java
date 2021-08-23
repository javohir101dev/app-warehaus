package com.appwarehaus.service;

import com.appwarehaus.entity.Attachment;
import com.appwarehaus.entity.AttachmentContent;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.AttachmentContentRepository;
import com.appwarehaus.repository.AttachmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AttchmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(savedAttachment);
        attachmentContent.setBytes(file.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new Result("File is saved", true, savedAttachment.getId());
    }

    public Attachment getAttachmentById(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new Attachment();
        }
        return optionalAttachment.get();
    }

    @SneakyThrows
    public Result editAttachment(Integer id, MultipartRequest request){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()){
            return new Result("Attachment is not found", false);
        }
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = optionalAttachment.get();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(savedAttachment);
        attachmentContent.setBytes(file.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new Result("File is editeded", true, savedAttachment.getId());
    }

    public Result deleteAttachment(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new Result("Attachment is not found", true);
        }
        try {
            attachmentRepository.deleteById(id);
            return new Result("Attachment is deleted", true);
        } catch (Exception e) {
            return new Result("Error in deletig", false);
        }
    }


}
