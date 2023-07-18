package br.com.decoder.ead.course.services.impl;

import br.com.decoder.ead.course.clients.AuthUserClient;
import br.com.decoder.ead.course.models.CourseModel;
import br.com.decoder.ead.course.models.CourseUserModel;
import br.com.decoder.ead.course.repositories.CourseUserRepository;
import br.com.decoder.ead.course.services.CourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CourseUserServiceImpl implements CourseUserService {

    @Autowired
    CourseUserRepository courseUserRepository;

    @Autowired
    AuthUserClient authUserClient;

    @Override

    public boolean existsByCouserAndUserId(CourseModel courseModel, UUID userId) {
        return courseUserRepository.existsByCourseAndUserId(courseModel, userId);
    }

    @Override
    public CourseUserModel save(CourseUserModel courseUserModel) {
        return courseUserRepository.save(courseUserModel);
    }

    @Transactional
    @Override
    public CourseUserModel saveAndSendSubscriptionUserInCourse(CourseUserModel courseUserModel) {
        courseUserModel = courseUserRepository.save(courseUserModel);

        authUserClient.postSubscriptionUserInCourse(courseUserModel.getCourse().getCourseId(), courseUserModel.getUserId());

        return courseUserModel;
    }

}
