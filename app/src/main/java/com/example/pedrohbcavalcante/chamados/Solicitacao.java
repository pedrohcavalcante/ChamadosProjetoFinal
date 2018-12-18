package com.example.pedrohbcavalcante.chamados;

import java.util.Objects;

public class Solicitacao {
   /* private long ID;*/

    private long userID;
    private long ticket;
    private String titulo;
    private String tipo;
    private String descricao;
    private String servico;
    private String assunto;
    private String texto;
    private String URL_anexo;
    private String sala;
    private String ramal;
    private String status;

    public Solicitacao(/*long ID,*/ long userID, long ticket, String titulo, String status){
       /* this.ID = ID;*/
        this.userID = userID;
        this.ticket = ticket;
        this.titulo = titulo;
        this.status = status;
    }

    public Solicitacao(long userID, long ticket, String titulo, String tipo, String descricao, String servico, String assunto, String texto, String URL_anexo, String sala, String ramal, String status) {
        /*this.ID = ID;*/
        this.userID = userID;
        this.ticket = ticket;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.servico = servico;
        this.assunto = assunto;
        this.texto = texto;
        this.URL_anexo = URL_anexo;
        this.sala = sala;
        this.ramal = ramal;
        this.status = status;
    }

   /* public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }*/

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getTicket() {
        return ticket;
    }

    public void setTicket(long ticket) {
        this.ticket = ticket;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getURL_anexo() {
        return URL_anexo;
    }

    public void setURL_anexo(String URL_anexo) {
        this.URL_anexo = URL_anexo;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitacao that = (Solicitacao) o;
        return /*ID == that.ID &&*/
                userID == that.userID &&
                ticket == that.ticket &&
                Objects.equals(titulo, that.titulo) &&
                Objects.equals(tipo, that.tipo) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(servico, that.servico) &&
                Objects.equals(assunto, that.assunto) &&
                Objects.equals(texto, that.texto) &&
                Objects.equals(URL_anexo, that.URL_anexo) &&
                Objects.equals(sala, that.sala) &&
                Objects.equals(ramal, that.ramal) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(/*ID,*/ userID, ticket, titulo, tipo, descricao, servico, assunto, texto, URL_anexo, sala, ramal, status);
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
               /* "ID=" + ID +*/
                ", userID=" + userID +
                ", ticket=" + ticket +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", servico='" + servico + '\'' +
                ", assunto='" + assunto + '\'' +
                ", texto='" + texto + '\'' +
                ", URL_anexo='" + URL_anexo + '\'' +
                ", sala='" + sala + '\'' +
                ", ramal='" + ramal + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
