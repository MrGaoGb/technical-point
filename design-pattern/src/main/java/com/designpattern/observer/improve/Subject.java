package com.designpattern.observer.improve;

import java.util.Observer;

/**
 * @author: Mr.Gao
 * @date: 2021/6/5 16:49
 * @description:
 */
public interface Subject {

    public void registerObserve(Observer observer);
    public void removeObserve(Observer observer);
    public void notifyObserves();
}
