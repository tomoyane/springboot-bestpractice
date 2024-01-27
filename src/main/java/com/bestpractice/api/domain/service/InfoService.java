package com.bestpractice.api.domain.service;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.repository.InfoRepository;
import com.bestpractice.api.common.exception.InternalServerError;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {

    private final InfoRepository infoRepository;

    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public List<Info> getInfoList() {
        List<Info> infoEntities;

        try {
            infoEntities = this.infoRepository.findAll();
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return infoEntities;
    }

    public Info getInfo(Long id) {
        Info info;

        try {
            info = this.infoRepository.findById(id).get();
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return info;
    }

    public void generateInfo(Info info) {
        try {
            this.infoRepository.save(info);
        } catch (Exception ex) {
            throw new InternalServerError();
        }
    }

    public void deleteInfo(Long id) {
        try {
            this.infoRepository.removeById(id);
        } catch (Exception ex) {
            throw new InternalServerError();
        }
    }
}
