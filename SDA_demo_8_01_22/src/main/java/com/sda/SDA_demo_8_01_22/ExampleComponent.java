package com.sda.SDA_demo_8_01_22;

import org.springframework.stereotype.Component;

@Component
public class ExampleComponent {

    private final DependencyComponent dependencyComponent;

    public ExampleComponent(DependencyComponent dependencyComponent) {
        this.dependencyComponent = dependencyComponent;
        System.out.println("Hey from " + this.getClass().getName());
    }

    public int addByDependencyComponent(int a, int b) {
        return dependencyComponent.add(a, b);
    }
}
