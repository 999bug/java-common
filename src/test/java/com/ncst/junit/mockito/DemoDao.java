package com.ncst.junit.mockito;

import java.util.Random;

public class DemoDao {

    public int getDemoStatus(){
        return new Random().nextInt();
    }
}
  