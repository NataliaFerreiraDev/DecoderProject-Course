package br.com.decoder.ead.course.services.impl;

import br.com.decoder.ead.course.repositories.UserRepository;
import br.com.decoder.ead.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

}
