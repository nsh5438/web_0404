package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.AttachmentPro;
import kr.hs.dgsw.web_0326.Repository.UsesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UsesRep usesRep;

    @PostMapping("/attachment")
    public AttachmentPro upload(@RequestPart MultipartFile uploadFile){
        String destFilename = "D:/3102_남가영/IdeaProjects/web_0326/upload/" + UUID.randomUUID().toString() + " " + uploadFile.getOriginalFilename();
        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            uploadFile.transferTo(destFile);
            return new AttachmentPro(destFilename, uploadFile.getOriginalFilename());
        }catch (Exception e){
            return null;
        }
    }

    @PutMapping("/uploadfile/{id}")
    public User uploadfile(@PathVariable Long id,@RequestBody User user){
//        String destFilename = "D:/3102_남가영/IdeaProjects/web_0326/upload/" + UUID.randomUUID().toString() + " " + uploadFile.getOriginalFilename();
        try {

            return this.usesRep.findById(id)
                    .map(found->{
                        found.setSavepath(Optional.ofNullable(user.getSavepath()).orElse(found.getSavepath()));
                        found.setOrdinaryname(Optional.ofNullable(user.getOrdinaryname()).orElse(found.getOrdinaryname()));
                        return usesRep.save(found);
                    })
                    .orElse(null);
        }catch (Exception e){
            return null;
        }
    }
}
