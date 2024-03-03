package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Buy;

public interface BuyService {
    List<Buy> findAll();

    Buy findById(int pkId);

    void create(Buy buy);

    void update(Buy buy);

    void delete(Buy buy);
}
