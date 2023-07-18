package br.com.decoder.ead.course.repositories;

import br.com.decoder.ead.course.models.CourseModel;
import br.com.decoder.ead.course.models.CourseUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseUserRepository extends JpaRepository<CourseUserModel, UUID> {

    boolean existsByCourseAndUserId(CourseModel courseModel, UUID userId);

}
