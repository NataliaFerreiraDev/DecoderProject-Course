package br.com.decoder.ead.course.repositories;

import br.com.decoder.ead.course.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseUserRepository extends JpaRepository<CourseModel, UUID> {


}
