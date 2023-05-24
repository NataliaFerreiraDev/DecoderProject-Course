package br.com.decoder.ead.course.controllers;

import br.com.decoder.ead.course.dtos.CourseDto;
import br.com.decoder.ead.course.models.CourseModel;
import br.com.decoder.ead.course.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    private static final String NOT_FOUND = "Course not found!";

    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDto courseDto){
        var courseModel = new CourseModel();

        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseModel));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId){

        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);

        if(!courseModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }

        courseService.delete(courseModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully!");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable(value = "courseId") UUID courseId,
                                               @RequestBody @Valid CourseDto courseDto ) {

        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);

        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }

        var courseModel = courseModelOptional.get();

        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseModel));
    }

    @GetMapping
    public ResponseEntity<List<CourseModel>> getAllCourses(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getOneCourses(@PathVariable(value = "courseId") UUID courseId){

        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);

        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(courseModelOptional.get());

    }


}