package com.exmaple.guava;


import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;


public class GuavaTest {

    @Test
    public void testImmutableMap(){
        Map<Integer, String> map = ImmutableMap.of(1, "A", 2, "B");
        System.out.println(map);
        System.out.println(map.getClass());
    }

    @Test
    public void testMaps(){
        Map<Integer,String> map = Maps.newHashMap();
        map.put(2,"B");
        System.out.println(map);
    }

    @Test
    public void testOptional(){
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());
    }
}
