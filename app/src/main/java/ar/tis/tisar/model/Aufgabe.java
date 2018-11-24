package ar.tis.tisar.model;

import java.util.Date;
import java.util.List;

public class Aufgabe {
    String beschreibung;
    Foto foto;
    List<Mitarbeiter> arbeiter;
    Date deadline;
    Status status;

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public List<Mitarbeiter> getArbeiter() {
        return arbeiter;
    }

    public void setArbeiter(List<Mitarbeiter> arbeiter) {
        this.arbeiter = arbeiter;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
