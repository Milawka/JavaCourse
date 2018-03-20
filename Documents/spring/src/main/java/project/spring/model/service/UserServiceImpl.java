package project.spring.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.spring.model.entities.User;
import project.spring.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;



    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User findUserbyId(Long id){
        return userRepository.getById(id);
    }

    @Override
    public User findUserByLogin(String login){
        return userRepository.getByLogin(login);
    }

    @Override
    public User createNewUser(String login, String password) {

        User user = new User(login, passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
