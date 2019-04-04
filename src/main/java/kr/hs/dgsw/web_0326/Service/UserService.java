package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> lstAllUser();

    User view(Long id);

    User update(Long id,User user);

    boolean delete(Long id);
}
