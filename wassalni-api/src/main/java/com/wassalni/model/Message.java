// com/wassalni/model/Message.java
package com.wassalni.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    @Column(name = "IDMSG")
    private Long idmsg;

    @Column(name = "EXPEDITEUR")
    private Long expediteur;

    @Column(name = "DESTINATAIRE")
    private Long destinataire;

    @Column(name = "CONTENU")
    private String contenu;

    @Column(name = "HEUREENVOI")
    private LocalDateTime heureenvoi;

    @Column(name = "TYPEMSG")
    private String typeMsg;

    @Column(name = "NOCDE")
    private Long nocde;

    @Column(name = "LU")
    private Integer lu;

    public Long getIdmsg() { return idmsg; }
    public void setIdmsg(Long idmsg) { this.idmsg = idmsg; }
    public Long getExpediteur() { return expediteur; }
    public void setExpediteur(Long expediteur) { this.expediteur = expediteur; }
    public Long getDestinataire() { return destinataire; }
    public void setDestinataire(Long destinataire) { this.destinataire = destinataire; }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public LocalDateTime getHeureenvoi() { return heureenvoi; }
    public void setHeureenvoi(LocalDateTime h) { this.heureenvoi = h; }
    public String getTypeMsg() { return typeMsg; }
    public void setTypeMsg(String typeMsg) { this.typeMsg = typeMsg; }
    public Long getNocde() { return nocde; }
    public void setNocde(Long nocde) { this.nocde = nocde; }
    public Integer getLu() { return lu; }
    public void setLu(Integer lu) { this.lu = lu; }
}