package com.aldeamo.bar.v1.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBarService {

    List<Integer> distribute(int iterations, Long id);

}
