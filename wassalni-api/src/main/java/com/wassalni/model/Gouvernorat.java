// com/wassalni/model/Gouvernorat.java
package com.wassalni.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GOUVERNORATS")
public class Gouvernorat {

    @Id
    @Column(name = "IDGOUV")
    private Long idgouv;

    @Column(name = "NOMGOUV")
    private String nomgouv;

    public Long getIdgouv() { return idgouv; }
    public void setIdgouv(Long idgouv) { this.idgouv = idgouv; }
    public String getNomgouv() { return nomgouv; }
    public void setNomgouv(String nomgouv) { this.nomgouv = nomgouv; }
}