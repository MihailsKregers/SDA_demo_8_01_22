package anotherPackage;

import org.springframework.stereotype.Component;

@Component
public class AnotherComponent {

    public AnotherComponent() {
        System.out.println("Will not be printed!");
    }
}
