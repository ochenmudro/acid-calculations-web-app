package com.example.acidcalculationswebapp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "chromatograms")
public class Chromatogram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acid_id")
    private Acid acid;

    @Column(name = "date")
    private OffsetDateTime date;

    @Column(name = "t")
    private Double t;

    @Column(name = "name")
    private String name;

    @PrePersist
    public void prePersist() {
        this.date = OffsetDateTime.now();
    }
}
