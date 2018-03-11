package com.bestpractice.api.service;

import com.bestpractice.api.domain.entity.InfoEntity;
import com.bestpractice.api.domain.repository.InfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {

    private final InfoRepository infoRepository;

    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public List<InfoEntity> getInfoList() {
        return infoRepository.findAll();
    }

    public InfoEntity getInfo(Long id) {
        return infoRepository.findById(id);
    }

    public void generateInfo(InfoEntity infoEntity) {
        infoRepository.save(infoEntity);
    }

    public void deleteInfo(Long id) {
        infoRepository.removeById(id);
    }
}
