// com/wassalni/model/Personnel.java
package com.wassalni.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSONNEL")
public class Personnel {

    @Id
    @Column(name = "IDPERS")
    private Long idpers;

    @Column(name = "NOMPERS")
    private String nompers;

    @Column(name = "PRENOMPERS")
    private String prenompers;

    @Column(name = "TELPERS")
    private String telpers;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "MOTP")
    private String motP;

    @Column(name = "CODEPOSTE")
    private String codeposte;

    @Column(name = "IDGOUV")
    private Long idgouv; // gouvernorat du livreur (null pour contrôleurs)

    public Long getIdpers() { return idpers; }
    public void setIdpers(Long idpers) { this.idpers = idpers; }
    public String getNompers() { return nompers; }
    public void setNompers(String nompers) { this.nompers = nompers; }
    public String getPrenompers() { return prenompers; }
    public void setPrenompers(String prenompers) { this.prenompers = prenompers; }
    public String getTelpers() { return telpers; }
    public void setTelpers(String telpers) { this.telpers = telpers; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getMotP() { return motP; }
    public void setMotP(String motP) { this.motP = motP; }
    public String getCodeposte() { return codeposte; }
    public void setCodeposte(String codeposte) { this.codeposte = codeposte; }
    public Long getIdgouv() { return idgouv; }
    public void setIdgouv(Long idgouv) { this.idgouv = idgouv; }
}