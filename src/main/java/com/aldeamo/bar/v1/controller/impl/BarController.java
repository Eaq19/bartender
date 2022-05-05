package com.aldeamo.bar.v1.controller.impl;

import com.aldeamo.bar.v1.controller.IBarController;
import com.aldeamo.bar.v1.service.IBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("BarController")
public class BarController implements IBarController {

    @Autowired
    private IBarService iBarService;

    @Override
    public List<Integer> getDistribute(int iterations, Long id) {
        return this.iBarService.distribute(iterations, id);
    }

    @Override
    public Boolean getPing() {
        return true;
    }
}
