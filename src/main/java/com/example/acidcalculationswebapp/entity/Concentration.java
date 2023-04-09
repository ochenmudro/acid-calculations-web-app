package com.example.acidcalculationswebapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "concentrations")
public class Concentration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Double value;

    @ManyToOne
    @JoinColumn(name = "chromatogram_id")
    @ToString.Exclude
    private Chromatogram chromatogram;
}
