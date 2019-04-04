package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Service.CommentService;
import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public User adduser(@RequestBody User user){
        return  this.userService.addUser(user);
    }

    @GetMapping("/listuser")
    public List<User> listuser (){
        return this.userService.lstAllUser();
    }

    @GetMapping("/listuser/{id}")
    public User view(@PathVariable Long id){
        return this.userService.view(id);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id, @RequestBody User user){
        return this.userService.update(id,user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){
        return this.userService.delete(id);
    }

}
