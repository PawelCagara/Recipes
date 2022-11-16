package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.Recipe;
import recipes.model.User;
import recipes.service.UserServiceImpl;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        if(userService.checkIfEmailIsInDatabase(createdUser.getEmail()) == null) {
            userService.saveUser(createdUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
