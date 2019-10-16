package ee.taltech.java1127.controller;

import ee.taltech.java1127.dao.UserDao;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDao saveUser (@RequestBody UserDao userDao){
        return userService.createNewUser(userDao);
    }
    /*
    @PostMapping
    public SynonymDao saveSynonym(@RequestBody SynonymDao synonymDao) {
        return synonymService.createNewSynonym(synonymDao);
        //TODO: Unique check
    }

    @DeleteMapping("/{synonym_id}")
    public void deleteHero(@PathVariable Long synonym_id){
        synonymService.deleteSynonym(synonym_id);
    }
    * */



}
