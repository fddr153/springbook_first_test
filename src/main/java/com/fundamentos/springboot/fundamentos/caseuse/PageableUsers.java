package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageableUsers {
    private UserService userService;

    public PageableUsers(UserService userService) {
        this.userService = userService;
    }

    public List<User> getPageable(int page, int size) {
        return userService.getPageable(page,size);
    }
}
