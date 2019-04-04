package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernamePro;
import kr.hs.dgsw.web_0326.Repository.CommentRep;
import kr.hs.dgsw.web_0326.Repository.UsesRep;
import kr.hs.dgsw.web_0326.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceimp implements CommentService {

    @Autowired
    private CommentRep commentRep;
    @Autowired
    private UsesRep usesRep;

    @PostConstruct
    private void init() {

        if(this.commentRep.count() > 0) return;

        User u = this.usesRep.save(new User("abc", "abcdgsw"));
        this.commentRep.save(new Comment(u.getId(), "hi 111"));
        this.commentRep.save(new Comment(u.getId(), "hi 222"));
        this.commentRep.save(new Comment(u.getId(), "hi 333"));

    }

    @Override
    public List<CommentUsernamePro> lstAllComment() {
        List<Comment> commentList = this.commentRep.findAll();
        List<CommentUsernamePro> cmtlst = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = this.usesRep.findById(comment.getUserid());
            String username = null;
            if (found.isPresent()) username = found.get().getUsername();
            cmtlst.add(new CommentUsernamePro(comment, username));

        });
        return cmtlst;
    }

    @Override
    public CommentUsernamePro addcmt(Comment cmt) {
//        User u = this.usesRep.findById(cmt.getUserid()).orElse(null);
        Comment comment = this.commentRep.save(cmt);
        return new CommentUsernamePro(
                comment,
                this.usesRep.findById(comment.getUserid()).map(user->user.getUsername()).orElse(null));
    }

    @Override
    public List<CommentUsernamePro> lstComment(Long userid) {

        List<Comment> commentList = this.commentRep.findAll();
        List<CommentUsernamePro> cmtlst = new ArrayList<>();

        commentList.forEach(comment -> {
            Optional<User> found = this.usesRep.findById(userid);
            String username = null;
            if (userid == comment.getUserid()){
                username = found.get().getUsername();
                cmtlst.add(new CommentUsernamePro(comment,username));
            }
        });
        return cmtlst;

    }

    @Override
    public Comment updatecmt(Long id, Comment cmt) {
        return this.commentRep.findById(id)
                .map(found -> {
                    found.setComment(Optional.ofNullable(cmt.getComment()).orElse(found.getComment()));
                    return this.commentRep.save(found);
                })
                .orElse(null);
    }

    @Override
    public boolean deletecmt(Long id) {
        Optional<Comment> comment = this.commentRep.findById(id);
        if (comment.isPresent()) {
            this.commentRep.delete(comment.get());
            return true;
        } else
            return false;
    }


}
