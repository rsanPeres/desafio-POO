package br.com.dio.desafio.infra;

import java.util.Random;

public abstract class IdGenerator {
    public static long idGenerate(){
        Random rand = new Random();
        return rand.nextLong(1233466);
    }
}
