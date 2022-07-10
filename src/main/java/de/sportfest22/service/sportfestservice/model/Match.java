package de.sportfest22.service.sportfestservice.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "`match`")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "klasse1_id", nullable = false)
    private Klasse klasse1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "klasse2_id", nullable = false)
    private Klasse klasse2;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "public", nullable = false)
    private Boolean _public = false;

    @Column(name = "start", nullable = false)
    private Instant start;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klasse getKlasse1() {
        return klasse1;
    }

    public void setKlasse1(Klasse klasse1) {
        this.klasse1 = klasse1;
    }

    public Klasse getKlasse2() {
        return klasse2;
    }

    public void setKlasse2(Klasse klasse2) {
        this.klasse2 = klasse2;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean get_public() {
        return _public;
    }

    public void set_public(Boolean _public) {
        this._public = _public;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

}