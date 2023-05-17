package br.com.decoder.ead.course.services.impl;

import br.com.decoder.ead.course.repositories.ModuleRepository;
import br.com.decoder.ead.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

}
