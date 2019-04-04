package kr.hs.dgsw.web_0326.Repository;

import kr.hs.dgsw.web_0326.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRep extends JpaRepository<Comment, Long> {
    public Optional<Comment> findByUserid(Long userid);
}
