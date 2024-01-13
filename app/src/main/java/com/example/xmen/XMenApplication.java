package com.example.xmen;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class XMenApplication extends Application
{
    public List<XMen> getListe() {
        return liste;
    }

    // variable globale de l'application : la liste des XMen
    private List<XMen> liste;
    // initialisation du contexte
    @Override public void onCreate()
    {
        super.onCreate();
        initListe();
    }
    public void initListe(){
        Resources res = getResources();
        final String[] noms = res.getStringArray(R.array.noms);
        final String[] alias = res.getStringArray(R.array.alias);
        final String[] confrerie = res.getStringArray(R.array.confrerie);
        final String[] description = res.getStringArray(R.array.descriptions);
        final String[] pouvoirs = res.getStringArray(R.array.pouvoirs);
        TypedArray images = res.obtainTypedArray(R.array.idimages);
        if(liste==null){
            liste = new ArrayList<>(noms.length);
        }else{
            liste.clear();
        }

        for (int i=0; i<noms.length; ++i) {
            liste.add(new XMen(noms[i], alias[i],description[i],confrerie[i],pouvoirs[i], images.getResourceId(i, 0)));

        }
// TODO initialiser la liste à partir des ressources
        images.recycle();
    }

    public void addtoListe(XMen xmen){
        this.liste.add(xmen);
    }
    public void modifyListe(int position, XMen xmen){
        liste.get(position).setNom(xmen.getNom());
        liste.get(position).setAlias(xmen.getAlias());
        liste.get(position).setDescription(xmen.getDescription());
        liste.get(position).setConfrerie(xmen.getConfrerie());
        liste.get(position).setPouvoirs(xmen.getPouvoirs());

    }
    public void resetList(){
        if(liste!=null){
            initListe();
        }
    }

}