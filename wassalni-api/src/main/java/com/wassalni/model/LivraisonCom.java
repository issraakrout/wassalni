// com/wassalni/model/LivraisonCom.java
package com.wassalni.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LIVRAISONCOM")
public class LivraisonCom {

    @Id
    @Column(name = "NOCDE")
    private Long nocde;

    @Column(name = "DATELIV")
    private LocalDate dateliv;

    @Column(name = "LIVREUR")
    private Long livreur;

    @Column(name = "MODEPAY")
    private String modepay;

    @Column(name = "ETATLIV")
    private String etatliv;

    @Column(name = "CONTROLEUR")
    private Long controleur;

    public Long getNocde() { return nocde; }
    public void setNocde(Long nocde) { this.nocde = nocde; }
    public LocalDate getDateliv() { return dateliv; }
    public void setDateliv(LocalDate dateliv) { this.dateliv = dateliv; }
    public Long getLivreur() { return livreur; }
    public void setLivreur(Long livreur) { this.livreur = livreur; }
    public String getModepay() { return modepay; }
    public void setModepay(String modepay) { this.modepay = modepay; }
    public String getEtatliv() { return etatliv; }
    public void setEtatliv(String etatliv) { this.etatliv = etatliv; }
    public Long getControleur() { return controleur; }
    public void setControleur(Long controleur) { this.controleur = controleur; }
}