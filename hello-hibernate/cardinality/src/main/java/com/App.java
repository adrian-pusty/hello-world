package com;

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
    // Without name = "a_b"                                    -> A_BS with A_ID and B_ID
    // Without joinColumns = @JoinColumn(name = "a_id")        -> A_B with AS_ID and B_ID
    // Without inverseJoinColumns = @JoinColumn(name = "b_id") -> A_B with A_ID and BS_ID
    @JoinTable(name = "a_b"//,
//            joinColumns = @JoinColumn(name = "a_id"),
//            inverseJoinColumns = @JoinColumn(name = "b_id")
    )
    private Set<B> bs;
}

@Entity
class B
{
    @Id
    @GeneratedValue
    Long id;
    // Without (mappedBy = "bs")            -> B_AS table created with B_ID and AS_ID (A_B created as well)
    // Without @ManyToMany(mappedBy = "bs") -> Caused by: org.hibernate.MappingException: Could not determine type for: java.util.Set, at table: b, for columns: [org.hibernate.mapping.Column(as)]
    @ManyToMany(mappedBy = "bs")
    private Set<A> as;
}

