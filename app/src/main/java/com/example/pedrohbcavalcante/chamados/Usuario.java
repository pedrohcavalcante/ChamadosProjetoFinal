package com.example.pedrohbcavalcante.chamados;

import java.util.Objects;

public class Usuario {
    private long ID;
    private String name;
    private String username;
    private String password;

    public Usuario(long ID, String name, String username, String password) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return ID == usuario.ID &&
                Objects.equals(name, usuario.name) &&
                Objects.equals(username, usuario.username) &&
                Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, name, username, password);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

