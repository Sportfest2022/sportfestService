package de.sportfest22.service.sportfestservice.model;

import javax.persistence.*;

@Entity
@Table(name = "klassenstufe")
public class Klassenstufe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "stufe", nullable = false)
    private Integer stufe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStufe() {
        return stufe;
    }

    public void setStufe(Integer stufe) {
        this.stufe = stufe;
    }

}