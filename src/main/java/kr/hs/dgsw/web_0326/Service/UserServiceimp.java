package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Repository.CommentRep;
import kr.hs.dgsw.web_0326.Repository.UsesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimp implements UserService{


    @Autowired
    private UsesRep usesRep;

    @Override
    public User addUser(User user) {
        Optional<User> found = this.usesRep.findByEmail(user.getEmail());
        if (found.isPresent()) return null;
        return this.usesRep.save(user);
    }

    @Override
    public List<User> lstAllUser() {
        return this.usesRep.findAll();
    }

    @Override
    public User view(Long id) {
        return this.usesRep.findById(id).orElse(null);
    }

    @Override
    public User update(Long id, User user) {
        return this.usesRep.findById(id)
                .map(found -> {
                    found.setUsername(Optional.ofNullable(user.getUsername()).orElse(found.getUsername()));
                    found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
                    return this.usesRep.save(found);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.usesRep.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
