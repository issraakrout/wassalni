// com/wassalni/model/Commandes.java
package com.wassalni.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMMANDES")
public class Commandes {

    @Id
    @Column(name = "NOCDE")
    private Long nocde;

    @Column(name = "NOCLT")
    private Long noclt;

    @Column(name = "DATECDE")
    private LocalDate datecde;

    @Column(name = "ETATCDE")
    private String etatcde;

    public Long getNocde() { return nocde; }
    public void setNocde(Long nocde) { this.nocde = nocde; }

    public Long getNoclt() { return noclt; }
    public void setNoclt(Long noclt) { this.noclt = noclt; }

    public LocalDate getDatecde() { return datecde; }
    public void setDatecde(LocalDate datecde) { this.datecde = datecde; }

    public String getEtatcde() { return etatcde; }
    public void setEtatcde(String etatcde) { this.etatcde = etatcde; }
}