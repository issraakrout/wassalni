// com/wassalni/model/Articles.java
package com.wassalni.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ARTICLES")
public class Articles {

    @Id
    @Column(name = "REFART")
    private String refart;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "PRIXV")
    private Double prixV;

    @Column(name = "QTESTK")
    private Integer qtestk;

    public String getRefart() { return refart; }
    public void setRefart(String refart) { this.refart = refart; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public Double getPrixV() { return prixV; }
    public void setPrixV(Double prixV) { this.prixV = prixV; }

    public Integer getQtestk() { return qtestk; }
    public void setQtestk(Integer qtestk) { this.qtestk = qtestk; }
}