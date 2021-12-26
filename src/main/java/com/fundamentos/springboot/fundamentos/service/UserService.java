package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user->LOG.info("Usuario insertado: "+user))
                .forEach(user->userRepository.save(user));
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(User modUser, Long id) {
         return userRepository.findById(id)
                .map(user->{
                    user.setEmail(modUser.getEmail());
                    user.setBirthDate(modUser.getBirthDate());
                    user.setName(modUser.getName());
                    return userRepository.save(user);
                }
                ).orElse(null);
    }

    public List<User> getPageable(int page, int size) {
        return userRepository.findAll(PageRequest.of(page,size)).getContent();
    }
}
