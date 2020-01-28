package org.zhivko.todoListApi.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zhivko.todoListApi.entities.User;
import org.zhivko.todoListApi.repositories.UserRepository;
import org.zhivko.todoListApi.services.UserService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository = null;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User findById(long id) {

        Optional<User> user = this.userRepository.findById(id);

        if(user.isPresent()) {
            throw new UsernameNotFoundException("User not found.");
        }

        return user.get();

    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(s);

        if(user == null) {
            throw new UsernameNotFoundException("Invalid Credentials.");
        }

        return user;
    }

    @Transactional
    public User make(User user) {
        return this.userRepository.save(user);
    }
}
