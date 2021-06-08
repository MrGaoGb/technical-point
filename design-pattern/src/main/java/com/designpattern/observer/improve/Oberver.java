package com.designpattern.observer.improve;

/**
 * @author: Mr.Gao
 * @date: 2021/6/5 16:51
 * @description:
 */
public interface Oberver {
    /**
     * 更新
     *
     * @param temperature
     * @param pressure
     * @param humidity
     */
    public void update(float temperature, float pressure, float humidity);
}
