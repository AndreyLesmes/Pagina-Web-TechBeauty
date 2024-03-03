package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.BuyDetail;
import com.tech.techy.Repository.BuyDetailRepository;
import com.tech.techy.Service.BuyDetailService;

@Service
public class BuyDetailImp implements BuyDetailService {

    @Autowired
    private BuyDetailRepository buyDetailRepository;

    @Override
    public List<BuyDetail> findAll() {
        return this.buyDetailRepository.findAll();
    }

    @Override
    public BuyDetail findById(int pkId) {
        BuyDetail buyDetail = this.buyDetailRepository.findById(pkId);
        return buyDetail;
    }

    @Override
    public void create(BuyDetail buyDetail) {
        this.buyDetailRepository.save(buyDetail);
    }

    @Override
    public void update(BuyDetail buyDetail) {
        this.buyDetailRepository.save(buyDetail);
    }

    @Override
    public void delete(BuyDetail buyDetail) {
        this.buyDetailRepository.delete(buyDetail);
    }
}
