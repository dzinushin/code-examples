package org.example;

import org.example.beans.Canary;
import org.example.beans.Dog;
import org.example.beans.Parrot;
import org.example.configuration.ProjectConfig;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {

    private static void addCanaryToAppContext(AnnotationConfigApplicationContext context) {

        Supplier<Canary> canarySupplier = () -> {
            Canary canary = new Canary();
            canary.setName("Canary");
            return canary;
        };
        BeanDefinitionCustomizer bdc = bc -> bc.setPrimary(true);
        context.registerBean("canary", Canary.class, canarySupplier, bdc);
    }

    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(ProjectConfig.class);
        addCanaryToAppContext(applicationContext);

        Parrot mainParrot = applicationContext.getBean(Parrot.class);
        System.out.println("Main parrot name: " + mainParrot.getName());

        Parrot rikiParrot = applicationContext.getBean("riki", Parrot.class);
        System.out.println("Riki parrot name: "+ rikiParrot.getName());


        Dog dog = applicationContext.getBean(Dog.class);
        System.out.println("Dog: "+ dog);
        System.out.println("Dog name: "+ dog.getName());

        Canary canary = applicationContext.getBean(Canary.class);
        System.out.println("Canary: "+ canary);
        System.out.println("Canary name: "+ canary.getName());

    }
}
