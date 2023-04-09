package com.example.acidcalculationswebapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "acids")
public class Acid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "text_name")
    private String textName;

    @OneToMany(mappedBy = "acid")
    @ToString.Exclude
    private List<Calculation> calculationList;

    @OneToMany(mappedBy = "acid")
    @ToString.Exclude
    private List<Chromatogram> chromatogramList;
}


