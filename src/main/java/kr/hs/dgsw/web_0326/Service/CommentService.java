package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernamePro;
import kr.hs.dgsw.web_0326.Domain.User;

import java.util.List;

public interface CommentService {

    List<CommentUsernamePro> lstAllComment();

    List<CommentUsernamePro> lstComment(Long id);

    CommentUsernamePro addcmt(Comment cmt);

    Comment updatecmt(Long id, Comment cmt);

    boolean deletecmt(Long id);

}
