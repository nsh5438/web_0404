package kr.hs.dgsw.web_0326.Repository;

import kr.hs.dgsw.web_0326.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsesRep extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

}
