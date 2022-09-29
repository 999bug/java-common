package com.ncst.design;

/**
 * @Author: Lisy
 * @Date: 2022/09/19/15:02
 * @Description:
 */
public interface Platform {

    void start(String uuid);

    void stop(String uuid);

    void operate(String operateId);
}
