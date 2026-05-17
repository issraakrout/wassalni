// com/wassalni/model/LigCdesId.java  (clé composite)
package com.wassalni.model;

import java.io.Serializable;
import java.util.Objects;

public class LigCdesId implements Serializable {
    private Long nocde;
    private String refart;

    public LigCdesId() {}
    public LigCdesId(Long nocde, String refart) {
        this.nocde  = nocde;
        this.refart = refart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LigCdesId)) return false;
        LigCdesId that = (LigCdesId) o;
        return Objects.equals(nocde, that.nocde) &&
               Objects.equals(refart, that.refart);
    }

    @Override
    public int hashCode() { return Objects.hash(nocde, refart); }
}