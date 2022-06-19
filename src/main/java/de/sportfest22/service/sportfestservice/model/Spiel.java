package de.sportfest22.service.sportfestservice.model;

import javax.persistence.*;

@Entity
@Table(name = "spiel")
public class Spiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "gametype", nullable = false)
    private Integer gametype;

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

    public Integer getGametype() {
        return gametype;
    }

    public void setGametype(Integer gametype) {
        this.gametype = gametype;
    }

}