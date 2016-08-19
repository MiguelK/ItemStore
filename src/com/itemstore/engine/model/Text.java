package com.itemstore.engine.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Set;

public interface Text {

    default boolean isSimilarInContext(String text){
        throw new IllegalStateException("No a SimilarContext object");
    }

    static Text createSimilarTextContext(String text,Set<String> synonyms, Set<String> contextWords){
        return new SimilarTextContextImpl(text,synonyms,contextWords);
    }

    static boolean isSimilar(String a, String b){

        if(a==null | b==null){
            throw new IllegalArgumentException("Invalid parameter null");
        }
        if(a.isEmpty() | b.isEmpty()){
            throw new IllegalArgumentException("Invalid parameter empty");
        }

        String[] splitA = StringUtils.split(a);
        String[] splitB = StringUtils.split(b);


        double jaroWinklerDistance = StringUtils.getJaroWinklerDistance(a, b);
        System.out.println("A=" + Arrays.toString(splitA));
        System.out.println("B=" + Arrays.toString(splitB));
        System.out.println("jaroWinklerDistance=" + jaroWinklerDistance);


        return jaroWinklerDistance>0.8;
    }
}
