package com.sda.SDA_demo_8_01_22;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DependencyComponent {

    private SecondDependency secondDependency;

    public DependencyComponent() {
        System.out.println("Hey from " + this.getClass().getName());
    }

    @Autowired
    public void setSecondDependency(SecondDependency secondDependency) {
        this.secondDependency = secondDependency;
        this.secondDependency.setInjected(true);
    }

    public int add(int a, int b) {
        return a+b;
    }
}
