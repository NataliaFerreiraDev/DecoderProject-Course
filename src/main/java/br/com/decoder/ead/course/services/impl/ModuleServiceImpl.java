package br.com.decoder.ead.course.services.impl;

import br.com.decoder.ead.course.models.LessonModel;
import br.com.decoder.ead.course.models.ModuleModel;
import br.com.decoder.ead.course.repositories.LessonRepository;
import br.com.decoder.ead.course.repositories.ModuleRepository;
import br.com.decoder.ead.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(ModuleModel moduleModel) {
        List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(moduleModel.getModuleId());
        if(!lessonModelList.isEmpty()){
            lessonRepository.deleteAll(lessonModelList);
        }
        moduleRepository.delete(moduleModel);

    }

}
