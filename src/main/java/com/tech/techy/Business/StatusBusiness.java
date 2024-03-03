package com.tech.techy.Business;

import com.tech.techy.Dtos.StatusDto;
import com.tech.techy.Entity.Status;
import com.tech.techy.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusBusiness {

    @Autowired
    private StatusService statusService;

    private List<Status> statusList;

    private List<StatusDto> statusDtoList = new ArrayList<>();

    public List<StatusDto> findAll() {
        this.statusList = this.statusService.findAll();
        this.statusList.stream().forEach(status -> {
            StatusDto statusDto = new StatusDto();
            statusDto.setPkId(status.getPkId());
            statusDto.setName(status.getName());

            statusDtoList.add(statusDto);
        });
        return this.statusDtoList;
    }

    public Status findById(int id) {
        return  this.statusService.findById(id);
    }

    public void createStatus(StatusDto statusDto) throws Exception {
        Status status = new Status();

        status.setName(statusDto.getName());

        this.statusService.create(status);
    }

    public void updateStatus(int id, StatusDto updatedStatusDto) throws Exception {
        Status existingStatus = statusService.findById(id);

        existingStatus.setName(updatedStatusDto.getName());

        this.statusService.update(existingStatus);
    }

    public void deleteStatus(int id) throws Exception{
        Status existingStatus = statusService.findById(id);
        if (existingStatus == null) {
            throw new Exception("Estado no encontrado!");
        }

        this.statusService.delete(existingStatus);
    }
}
