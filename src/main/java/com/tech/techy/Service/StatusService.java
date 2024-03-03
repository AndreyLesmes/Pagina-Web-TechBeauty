package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Status;

public interface StatusService {
    List<Status> findAll();

    Status findById(int pkId);

    void create(Status status);

    void update(Status status);

    void delete(Status status);
}
