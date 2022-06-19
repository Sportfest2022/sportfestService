package de.sportfest22.service.sportfestservice.model;

import javax.persistence.*;

@Entity
@Table(name = "betreuer")
public class Betreuer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 16)
    private String name;

    @Column(name = "nachname", nullable = false, length = 16)
    private String nachname;

    @Column(name = "nutzername", nullable = false, length = 8)
    private String nutzername;

    @Column(name = "passwort", nullable = false, length = 6)
    private String passwort;

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

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getNutzername() {
        return nutzername;
    }

    public void setNutzername(String nutzername) {
        this.nutzername = nutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

}