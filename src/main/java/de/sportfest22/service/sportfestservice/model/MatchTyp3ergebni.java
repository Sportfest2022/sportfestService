package de.sportfest22.service.sportfestservice.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "match_typ3ergebnis")
public class MatchTyp3ergebni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "zeit", nullable = false)
    private Integer zeit;

    @Column(name = "klassen_id", nullable = false)
    private Integer klassenId;

    @Column(name = "betreuer_id", nullable = false)
    private Integer betreuerId;

    @Column(name = "entrytime", nullable = false)
    private Instant entrytime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZeit() {
        return zeit;
    }

    public void setZeit(Integer zeit) {
        this.zeit = zeit;
    }

    public Integer getKlassenId() {
        return klassenId;
    }

    public void setKlassenId(Integer klassenId) {
        this.klassenId = klassenId;
    }

    public Integer getBetreuerId() {
        return betreuerId;
    }

    public void setBetreuerId(Integer betreuerId) {
        this.betreuerId = betreuerId;
    }

    public Instant getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Instant entrytime) {
        this.entrytime = entrytime;
    }

}