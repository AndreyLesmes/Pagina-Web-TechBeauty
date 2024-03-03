package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.BuyDetail;

public interface BuyDetailService {
    List<BuyDetail> findAll();

    BuyDetail findById(int pkId);

    void create(BuyDetail buyDetail);

    void update(BuyDetail buyDetail);

    void delete(BuyDetail buyDetail);
}
