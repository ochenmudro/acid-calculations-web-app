package com.example.acidcalculationswebapp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "calculations")
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "concentration")
    private double concentration;

    @Column(name = "abs_k")
    private double absK;

    @Column(name = "s")
    private double s;

    @Column(name = "s_st")
    private double sSt;

    @ManyToOne
    @JoinColumn(name = "acid_id")
    private Acid acid;

    @ManyToOne
    @JoinColumn(name = "experiment_id")
    private Experiment experiment;

}
