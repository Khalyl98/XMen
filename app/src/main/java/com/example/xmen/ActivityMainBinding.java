package com.example.xmen;

// généré par GenViewBindings.py
// Pierre Nerzic, pierre.nerzic@univ-rennes1.fr

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// FIXME imports manquants pour les vues
import android.widget.*;

@SuppressWarnings("unused")
public class ActivityMainBinding
{
    // identifiant du layout associé à ce View Binding
    private static final int ID_LAYOUT = R.layout.activity_main;

    // vue racine du layout, voir getRoot()
    private @NonNull final androidx.recyclerview.widget.RecyclerView root;

    // objets Java associés aux vues avec identifiants
    public @NonNull final androidx.recyclerview.widget.RecyclerView recycler;

    /**
     * constructeur privé : utiliser inflate et bind
     * @param root vue racine à associer à this
     */
    private ActivityMainBinding(@NonNull androidx.recyclerview.widget.RecyclerView root)
    {
        this.root = root;
        // récupérer les vues du layout
        recycler = root.findViewById(R.id.recycler);
    }

    /**
     * instancie un nouveau layout et retourne un nouveau ActivityMainBinding
     * @param layoutInflater expanseur de layout
     * @return nouvelle instance de ActivityMainBinding
     */
    @SuppressLint("InflateParams")
    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater layoutInflater)
    {
        return new ActivityMainBinding((androidx.recyclerview.widget.RecyclerView) layoutInflater.inflate(ID_LAYOUT, null, false));
    }

    /**
     * instancie un nouveau layout et retourne un nouveau ActivityMainBinding
     * @param layoutInflater expanseur de layout
     * @param parent vue parente à laquelle rattacher celle-ci, peut être null
     * @param attachToParent true s'il faut faire un attachement au parent
     * @return nouvelle instance de ActivityMainBinding
     */
    @NonNull
    public static ActivityMainBinding inflate(
            @NonNull LayoutInflater layoutInflater, @Nullable ViewGroup parent, boolean attachToParent)
    {
        return new ActivityMainBinding((androidx.recyclerview.widget.RecyclerView) layoutInflater.inflate(ID_LAYOUT, parent, attachToParent));
    }

    /**
     * associe un nouveau ActivityMainBinding à une vue existante
     * @param root vue racine à associer
     * @return nouvelle instance de ActivityMainBinding
     */
    @NonNull
    public static ActivityMainBinding bind(@NonNull androidx.recyclerview.widget.RecyclerView root)
    {
        return new ActivityMainBinding(root);
    }

    /**
     * retourne la vue racine du layout attaché à ce ViewBinding
     * @return View racine du layout
     */
    @NonNull public androidx.recyclerview.widget.RecyclerView getRoot()
    {
        return root;
    }
}
