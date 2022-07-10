package de.sportfest22.service.sportfestservice.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor
@Table(name = "match_typ1ergebnis")
public class MatchTyp1ergebni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gewinner_id", nullable = false)
    private Klasse gewinner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "betreuer_id", nullable = false)
    private Betreuer betreuer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(name = "entrytime", nullable = false)
    private Instant entrytime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klasse getGewinner() {
        return gewinner;
    }

    public void setGewinner(Klasse gewinner) {
        this.gewinner = gewinner;
    }

    public Betreuer getBetreuer() {
        return betreuer;
    }

    public void setBetreuer(Betreuer betreuer) {
        this.betreuer = betreuer;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Instant getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Instant entrytime) {
        this.entrytime = entrytime;
    }

}