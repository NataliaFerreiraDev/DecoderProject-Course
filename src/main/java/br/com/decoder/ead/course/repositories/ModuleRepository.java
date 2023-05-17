package br.com.decoder.ead.course.repositories;

import br.com.decoder.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {


}
