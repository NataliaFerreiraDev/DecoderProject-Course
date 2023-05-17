package br.com.decoder.ead.course.services.impl;

import br.com.decoder.ead.course.repositories.CourseRepository;
import br.com.decoder.ead.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

}
