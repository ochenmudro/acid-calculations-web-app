package com.example.acidcalculationswebapp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "experiments")
public class Experiment {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "date")
    private OffsetDateTime date;

    @Column(name = "name")
    private String name;

    @Column(name = "m_st")
    private double mSt;

    @Column(name = "v")
    private double v;

    @OneToMany(mappedBy = "experiment", cascade = CascadeType.DETACH)
    @ToString.Exclude
    private List<Calculation> calculations;

    @PrePersist
    public void prePersist() {
        this.date = OffsetDateTime.now();
    }

}
