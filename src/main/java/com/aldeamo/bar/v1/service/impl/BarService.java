package com.aldeamo.bar.v1.service.impl;

import com.aldeamo.bar.v1.model.Bar;
import com.aldeamo.bar.v1.repository.IBarRepository;
import com.aldeamo.bar.v1.service.IBarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BarService implements IBarService {

    @Autowired
    private IBarRepository iBarRepository;

    @Override
    public List<Integer> distribute(int iterations, Long id) {
        Optional<Bar> bar = this.iBarRepository.findById(id);
        List<Integer> response = new ArrayList<>();
        if (bar.isPresent()) {
            log.info("A0= " + bar.get().getGlasses());
            log.info("Q0= " + iterations);
            List<Integer> glasses = this.getGlassList(bar);
            this.mainProcess(glasses, iterations, this.createListPrimeNumbers(glasses
                    .stream()
                    .max(Comparator
                            .comparing(v -> v))
                    .orElse(0)), response);
        }
        return response;
    }

    /**
     * Metodo que genera una lista de numeros primos apartir de un numero maximo.
     * @param max
     * @return
     */
    private List<Integer> createListPrimeNumbers(int max) {
        int count = 0;
        List<Integer> primes = new ArrayList<>();
        while (count <= max) {
            if (isPrime(count)) {
                primes.add(count);
            }
            count++;
        }
        return primes;
    }

    /**
     * Metodo que valida si un numero es primo.
     * @param number
     * @return
     */
    private boolean isPrime(int number) {
        boolean response = false;
        if (number > 1) {
            int count = 0;
            for (int i = (int) Math.sqrt(number); i > 1; i--) {
                if (this.uniformDivision(number, i)) {
                    count++;
                }
            }
            response = count < 1;
        }

        return response;
    }

    /**
     * Metodo que obtiene una lista apartir del string que representa los vasos acumulados.
     * @param bar
     * @return
     */
    private List<Integer> getGlassList(Optional<Bar> bar) {
        return Arrays.stream(bar
                .orElse(new Bar())
                .getGlasses()
                .split("\\s*,\\s*"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * Metodo que valida si un numero es divisible por otro.
     * @param dividend
     * @param divisor
     * @return
     */
    private boolean uniformDivision(int dividend, int divisor) {
        return dividend % divisor == 0;
    }

    /**
     * Metodo principal que distribuye los vasos a cumlados en la barra, recibe la lista de vasos, la cantidad de
     * iteraciones, la lista de numeros primos y la referencia de la lista de respuesta.
     * @param glasses
     * @param iterations
     * @param primes
     * @param response
     */
    private void mainProcess(List<Integer> glasses, int iterations, List<Integer> primes, List<Integer> response) {
        List<Integer> glassesA = glasses;
        int aux = 0;
        while (aux < iterations) {
            List<Integer> glassesB = new ArrayList<>();
            List<Integer> glassesAux = new ArrayList<>();
            if (aux == 0) {
                glassesA = glasses;
            }
            if (glassesA.isEmpty()) {
                break;
            }
            for (int i = (glassesA.size() - 1); i >= 0; i--) {
                if (this.uniformDivision(glassesA.get(i), primes.get(aux))) {
                    glassesB.add(glassesA.get(i));
                } else {
                    glassesAux.add(glassesA.get(i));
                }
            }
            log.info("A" + (aux - 1) + "= " + glassesA.stream().map(String::valueOf).collect(Collectors.joining()));
            glassesA = glassesAux;
            response.addAll(glassesB);
            log.info("A" + aux + "= " + glassesA.stream().map(String::valueOf).collect(Collectors.joining()));
            log.info("B" + aux + "= " + glassesB.stream().map(String::valueOf).collect(Collectors.joining()));

            log.info("Respuesta= " + response.stream().map(String::valueOf).collect(Collectors.joining()));
            aux++;
        }
        response.addAll(glassesA);
    }
}
