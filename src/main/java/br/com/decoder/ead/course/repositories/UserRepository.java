package br.com.decoder.ead.course.repositories;

import br.com.decoder.ead.course.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {


}
