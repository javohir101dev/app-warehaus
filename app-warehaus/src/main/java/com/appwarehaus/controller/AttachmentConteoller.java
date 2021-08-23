package com.appwarehaus.controller;

import com.appwarehaus.entity.Attachment;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.AttchmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/attachment")
public class AttachmentConteoller {

    @Autowired
    AttchmentService attchmentService;

//    CREATE
    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) {
        return attchmentService.uploadFile(request);
    }

//    READ
    @GetMapping("/{id}")
    public Attachment getAttachmentById(@PathVariable Integer id){
        return attchmentService.getAttachmentById(id);
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
