package com.example.xmen;

import androidx.annotation.DrawableRes;

public class XMen {

    private String nom;
    private String alias;
    private String description;
    private String confrerie;
    private String pouvoirs;
    private @DrawableRes int idImage;

    // Constructeurs

    // Constructeur sans paramètre
    // Constructor in XMen class
    public XMen() {
        nom = "inconnu";
        this.idImage = R.drawable.undef;
    }

    // Constructeur avec paramètres
    public XMen(String nom, String alias, String description, String confrerie, String pouvoirs, @DrawableRes int idImage) {
        this.nom = nom;
        this.alias = alias;
        this.description = description;
        this.confrerie = confrerie;
        this.pouvoirs = pouvoirs;
        this.idImage = idImage;
    }

    // Getters et Setters (vous pouvez générer ces méthodes automatiquement dans Android Studio)

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfrerie() {
        return confrerie;
    }

    public void setConfrerie(String confrerie) {
        this.confrerie = confrerie;
    }

    public String getPouvoirs() {
        return pouvoirs;
    }

    public void setPouvoirs(String pouvoirs) {
        this.pouvoirs = pouvoirs;
    }

    @DrawableRes
    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(@DrawableRes int idImage) {
        this.idImage = idImage;
    }
}
