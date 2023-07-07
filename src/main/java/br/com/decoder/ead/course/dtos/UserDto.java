package br.com.decoder.ead.course.dtos;

import br.com.decoder.ead.course.enums.UserStatus;
import br.com.decoder.ead.course.enums.UserType;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID userId;

    private String username;

    private String email;

    private UserStatus userStatus;

    private UserType userType;

    private String fullName;

    private String phoneNumber;

    private String cpf;

    private String imageUrl;

}
