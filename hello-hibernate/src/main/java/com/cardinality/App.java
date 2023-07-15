package com.cardinality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.Set;

@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}

@Entity
class A
{
    @Id
    @GeneratedValue
    Long id;

    @ManyToMany
    @JoinTable(name = "a_b",
            joinColumns = @JoinColumn(name = "a_id"),
            inverseJoinColumns = @JoinColumn(name = "b_id")
    )
    private Set<B> bs;
}

@Entity
class B
{
    @Id
    @GeneratedValue
    Long id;
    @ManyToMany(mappedBy = "bs")
    private Set<A> as;
}

