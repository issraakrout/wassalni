// com/wassalni/model/Clients.java
package com.wassalni.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIENTS")
public class Clients {

    @Id
    @Column(name = "NOCLT")
    private Long noclt;

    @Column(name = "NOMCLT")
    private String nomclt;

    @Column(name = "PRENOMCLT")
    private String prenomclt;

    @Column(name = "ADRCLT")
    private String adrclt;

    @Column(name = "VILLECLT")
    private String villeclt;

    @Column(name = "TELCLT")
    private String telclt;

    public Long getNoclt() { return noclt; }
    public void setNoclt(Long noclt) { this.noclt = noclt; }

    public String getNomclt() { return nomclt; }
    public void setNomclt(String nomclt) { this.nomclt = nomclt; }

    public String getPrenomclt() { return prenomclt; }
    public void setPrenomclt(String prenomclt) { this.prenomclt = prenomclt; }

    public String getAdrclt() { return adrclt; }
    public void setAdrclt(String adrclt) { this.adrclt = adrclt; }

    public String getVilleclt() { return villeclt; }
    public void setVilleclt(String villeclt) { this.villeclt = villeclt; }

    public String getTelclt() { return telclt; }
    public void setTelclt(String telclt) { this.telclt = telclt; }
}