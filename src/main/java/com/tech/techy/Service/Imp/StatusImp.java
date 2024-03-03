package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.Status;
import com.tech.techy.Repository.StatusRepository;
import com.tech.techy.Service.StatusService;

@Service
public class StatusImp implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return this.statusRepository.findAll();
    }

    @Override
    public Status findById(int pkId) {
        Status status = this.statusRepository.findById(pkId);
        return status;
    }

    @Override
    public void create(Status status) {
        this.statusRepository.save(status);
    }

    @Override
    public void update(Status status) {
        this.statusRepository.save(status);
    }

    @Override
    public void delete(Status status) {
        this.statusRepository.delete(status);
    }
}
    