package com.sda.SDA_demo_8_01_22;

import org.springframework.stereotype.Component;

@Component
public class SecondDependency {

    private boolean injected = false;

    public SecondDependency() {
        System.out.println("Hey from " + this.getClass().getName());
    }

    public void setInjected(boolean injected) {
        this.injected = injected;
        System.out.println("Hello from setter");
    }

    public boolean isInjected() {
        return injected;
    }
}
