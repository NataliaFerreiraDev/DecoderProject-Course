package br.com.decoder.ead.course.clients;

import br.com.decoder.ead.course.dtos.ResponsePageDto;
import br.com.decoder.ead.course.dtos.UserDto;
import br.com.decoder.ead.course.services.UtilService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
public class CourseClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UtilService utilService;

    public Page<UserDto> getAllUsersByCourse(UUID courseId, Pageable pageable){

        List<UserDto> searchResult = null;
        ResponseEntity<ResponsePageDto<UserDto>> result = null;

        String url = utilService.createUrlGetAllUsersByCourse(courseId, pageable);

        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);

        try{
            ParameterizedTypeReference<ResponsePageDto<UserDto>> responseType =
                    new ParameterizedTypeReference<ResponsePageDto<UserDto>>() {};
            result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            searchResult = result.getBody().getContent();
            log.debug("Response Number of Elements: {} ", searchResult.size());
        } catch (HttpStatusCodeException e){
            log.error("Error request /courses {} ", e);
        }
        log.info("Ending request /users courseId {} ", courseId);

        return result.getBody();
    }

}
