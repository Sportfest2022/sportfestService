package de.sportfest22.service.sportfestservice.model;

import javax.persistence.*;

@Entity
@Table(name = "klasse")
public class Klasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 3)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "klassenstufe_id", nullable = false)
    private Klassenstufe klassenstufe;

    public Klassenstufe getKlassenstufe() {
        return klassenstufe;
    }

    public void setKlassenstufe(Klassenstufe klassenstufe) {
        this.klassenstufe = klassenstufe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}