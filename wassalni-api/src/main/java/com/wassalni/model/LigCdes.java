// com/wassalni/model/LigCdes.java
package com.wassalni.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LIGCDES")
@IdClass(LigCdesId.class)
public class LigCdes {

    @Id
    @Column(name = "NOCDE")
    private Long nocde;

    @Id
    @Column(name = "REFART")
    private String refart;

    @Column(name = "QTECDE")
    private Integer qtecde;

    public Long getNocde() { return nocde; }
    public void setNocde(Long nocde) { this.nocde = nocde; }

    public String getRefart() { return refart; }
    public void setRefart(String refart) { this.refart = refart; }

    public Integer getQtecde() { return qtecde; }
    public void setQtecde(Integer qtecde) { this.qtecde = qtecde; }
}