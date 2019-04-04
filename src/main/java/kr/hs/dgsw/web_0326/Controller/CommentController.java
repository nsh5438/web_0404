package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernamePro;
import kr.hs.dgsw.web_0326.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public List<CommentUsernamePro> list(){
        return this.commentService.lstAllComment();
    }

    @GetMapping("/list/{id}")
    public List<CommentUsernamePro> view(@PathVariable Long id) {return this.commentService.lstComment(id);}

    @PostMapping("/addcmt")
    public CommentUsernamePro addcmt(@RequestBody Comment cmt) {return this.commentService.addcmt(cmt);}

    @PutMapping("/updatecmt/{id}")
    public Comment updatecmt(@PathVariable Long id,@RequestBody Comment cmt) {return this.commentService.updatecmt(id,cmt);}

    @DeleteMapping("/deletecmt/{id}")
    public boolean deletecmt(@PathVariable Long id){ return this.commentService.deletecmt(id); }

}
