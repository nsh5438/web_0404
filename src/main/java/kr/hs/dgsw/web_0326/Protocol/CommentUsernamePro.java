package kr.hs.dgsw.web_0326.Protocol;

import kr.hs.dgsw.web_0326.Domain.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentUsernamePro extends Comment {
    private String username;

    public CommentUsernamePro(Comment cmt, String username){
        super(cmt);
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
