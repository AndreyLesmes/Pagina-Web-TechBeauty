package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.Buy;
import com.tech.techy.Repository.BuyRepository;
import com.tech.techy.Service.BuyService;

@Service
public class BuyImp implements BuyService {

    @Autowired
    private BuyRepository buyRepository;

    @Override
    public List<Buy> findAll() {
        return this.buyRepository.findAll();
    }

    @Override
    public Buy findById(int pkId) {
        Buy buy = this.buyRepository.findById(pkId);
        return buy;
    }

    @Override
    public void create(Buy buy) {
        this.buyRepository.save(buy);
    }

    @Override
    public void update(Buy buy) {
        this.buyRepository.save(buy);
    }

    @Override
    public void delete(Buy buy) {
        this.buyRepository.delete(buy);
    }
}
