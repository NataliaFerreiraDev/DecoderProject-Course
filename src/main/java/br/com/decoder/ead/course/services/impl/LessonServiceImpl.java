package br.com.decoder.ead.course.services.impl;

import br.com.decoder.ead.course.repositories.LessonRepository;
import br.com.decoder.ead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

}
