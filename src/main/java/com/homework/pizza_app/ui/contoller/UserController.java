package com.homework.pizza_app.ui.contoller;

import com.homework.pizza_app.model.request.UserDetailsRequestModel;
import com.homework.pizza_app.model.response.UserRest;
import com.homework.pizza_app.service.OrderService;
import com.homework.pizza_app.service.PizzaClientService;
import com.homework.pizza_app.service.UserService;
import com.homework.pizza_app.share.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dritan
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    OrderService orderService;

    @Autowired
    PizzaClientService pizzaClientService;

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRest getUser(@PathVariable String id) {
        UserRest returnVal = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnVal);
        return returnVal;
    }

    @PostMapping
    public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnVal = new UserRest();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createUser, returnVal);

        return new ResponseEntity<UserRest>(returnVal, HttpStatus.CREATED);
    }

}
