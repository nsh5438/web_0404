package kr.hs.dgsw.web_0326.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private Long userid;
    private String comment;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;


    public Comment(Long userid, String comment)
    {
        this.userid = userid;
        this.comment = comment;
    }

    public Comment(Comment cmt){
        this.id = cmt.getId();
        this.comment = cmt.getComment();
        this.userid = cmt.getUserid();
        this.created = cmt.getCreated();
        this.updated = cmt.getUpdated();
    }

}
