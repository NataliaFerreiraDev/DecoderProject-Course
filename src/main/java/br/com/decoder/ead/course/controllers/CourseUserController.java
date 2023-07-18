package br.com.decoder.ead.course.controllers;

import br.com.decoder.ead.course.clients.AuthUserClient;
import br.com.decoder.ead.course.dtos.SubscriptionDto;
import br.com.decoder.ead.course.dtos.UserDto;
import br.com.decoder.ead.course.enums.UserStatus;
import br.com.decoder.ead.course.models.CourseModel;
import br.com.decoder.ead.course.models.CourseUserModel;
import br.com.decoder.ead.course.services.CourseService;
import br.com.decoder.ead.course.services.CourseUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseUserController {

    private static final String NOT_FOUND = "Course not found!";
    private static final String ALREADY_EXIXTS = "Error: subscription already exists!";

    @Autowired
    AuthUserClient authUserClient;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseUserService courseUserService;

    @GetMapping("/courses/{courseId}/users")
    public ResponseEntity<Page<UserDto>> getAllUsersByCourse(@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
                                                             Pageable pageable,
                                                             @PathVariable(value = "courseId") UUID courseId) {

        return ResponseEntity.status(HttpStatus.OK).body(authUserClient.getAllUsersByCourse(courseId, pageable));
    }

    @PostMapping("/courses/{courseId}/users/subscription")
    public ResponseEntity<Object> saveSubscriptionUserInCourse(@PathVariable(value = "courseId") UUID courseId,
                                                               @RequestBody @Valid SubscriptionDto subscriptionDto) {

        ResponseEntity<UserDto> responseUser;

        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);

        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }

        if (courseUserService.existsByCouserAndUserId(courseModelOptional.get(), subscriptionDto.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ALREADY_EXIXTS);
        }

        try {
            responseUser = authUserClient.getOneUserById(subscriptionDto.getUserId());
            if (responseUser.getBody().getUserStatus().equals(UserStatus.BLOCKED)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ALREADY_EXIXTS);
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
            }
        }

        CourseUserModel courseUserModel = courseUserService.saveAndSendSubscriptionUserInCourse(
                courseModelOptional.get().convertCourseUserModel(subscriptionDto.getUserId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(courseUserModel);
    }


}
