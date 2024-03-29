package br.com.decoder.ead.course.services;

import br.com.decoder.ead.course.models.CourseModel;
import br.com.decoder.ead.course.models.CourseUserModel;

import java.util.UUID;

public interface CourseUserService {

    boolean existsByCouserAndUserId(CourseModel courseModel, UUID userId);

    CourseUserModel save(CourseUserModel courseUserModel);

    CourseUserModel saveAndSendSubscriptionUserInCourse(CourseUserModel courseUserModel);

}
