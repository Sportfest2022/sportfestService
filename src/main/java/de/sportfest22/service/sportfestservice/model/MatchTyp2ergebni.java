package de.sportfest22.service.sportfestservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "match_typ2ergebnis")
public class MatchTyp2ergebni {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "gewinner_id", nullable = false)
    private Integer gewinnerId;

    @Column(name = "match_id", nullable = false)
    private Integer matchId;

    @Column(name = "zeit_klasse1", nullable = false)
    private Integer zeitKlasse1;

    @Column(name = "zeit_klasse2", nullable = false)
    private Integer zeitKlasse2;

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

    public Integer getGewinnerId() {
        return gewinnerId;
    }

    public void setGewinnerId(Integer gewinnerId) {
        this.gewinnerId = gewinnerId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getZeitKlasse1() {
        return zeitKlasse1;
    }

    public void setZeitKlasse1(Integer zeitKlasse1) {
        this.zeitKlasse1 = zeitKlasse1;
    }

    public Integer getZeitKlasse2() {
        return zeitKlasse2;
    }

    public void setZeitKlasse2(Integer zeitKlasse2) {
        this.zeitKlasse2 = zeitKlasse2;
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