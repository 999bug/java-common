package com.ncst.design;

/**
 * @Author: Lisy
 * @Date: 2022/09/19/15:02
 * @Description:
 */
public class CopyRule implements Platform{
    @Override
    public void start(String uuid) {

    }

    @Override
    public void stop(String uuid) {

    }

    @Override
    public void operate(String operateId) {
        // db get
        // switch status
        this.start("");
    }
}
