package br.com.decoder.ead.course.services;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UtilService {

    String createUrlGetAllUsersByCourse(UUID courseId, Pageable pageable);

}
