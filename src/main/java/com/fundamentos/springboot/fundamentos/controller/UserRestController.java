package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseuse.*;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserRestController {
    //crear,obtener,eliminar,actualizar
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;
    private PageableUsers pageableUsers;



    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser, UserRepository userRepository, PageableUsers pageableUsers) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.pageableUsers = pageableUsers;
    }
    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }
    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page,@RequestParam int size){
        return pageableUsers.getPageable(page,size);
    }
    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
       deleteUser.delete(id);
       return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User modUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(modUser,id),HttpStatus.OK);
    }
}
