package br.com.decoder.ead.course.services.impl;

import br.com.decoder.ead.course.repositories.CourseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseUserServiceImpl {

    @Autowired
    CourseUserRepository courseUserRepository;

}
