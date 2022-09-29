package com.ncst.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        final Person mimi = new Person("杨幂", "36C");
        c.add(mimi);
        c.add(new Person("汤唯", "36B"));
        c.add(new Person("赵丽颖", "34B"));
        c.add(new Person("柳岩", "36D"));
         Person p = new Person("杨幂", "36C");
        final boolean contains = c.contains(p);
        System.out.println(contains);
    }
}