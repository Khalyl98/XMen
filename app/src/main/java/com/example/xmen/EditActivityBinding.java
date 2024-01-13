package com.example.xmen;;

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
public class EditActivityBinding
{
    private static final int ID_LAYOUT = R.layout.edit_activity;

    // vue racine du layout, voir getRoot()
    private @NonNull final RelativeLayout root;

    // objets Java associés aux vues avec identifiants
    public @NonNull final EditText editTextNom;
    public @NonNull final EditText editTextAlias;
    public @NonNull final EditText editTextDescription;
    public @NonNull final EditText editTextConfrerie;
    public @NonNull final EditText editTextPouvoirs;

    /**
     * constructeur privé : utiliser inflate et bind
     * @param root vue racine à associer à this
     */
    private EditActivityBinding(@NonNull RelativeLayout root)
    {
        this.root = root;
        // récupérer les vues du layout
        editTextNom = root.findViewById(R.id.editTextNom);
        editTextPouvoirs = root.findViewById(R.id.editTextAlias);
        editTextConfrerie = root.findViewById(R.id.editTextDescription);
        editTextDescription = root.findViewById(R.id.editTextConfrerie);
        editTextAlias = root.findViewById(R.id.editTextPouvoirs);
    }

    /**
     * instancie un nouveau layout et retourne un nouveau ActivityEditBinding
     * @param layoutInflater expanseur de layout
     * @return nouvelle instance de ActivityEditBinding
     */
    @SuppressLint("InflateParams")
    @NonNull
    public static EditActivityBinding inflate(@NonNull LayoutInflater layoutInflater)
    {
        return new EditActivityBinding((RelativeLayout) layoutInflater.inflate(ID_LAYOUT, null, false));
    }

    /**
     * instancie un nouveau layout et retourne un nouveau ActivityEditBinding
     * @param layoutInflater expanseur de layout
     * @param parent vue parente à laquelle rattacher celle-ci, peut être null
     * @param attachToParent true s'il faut faire un attachement au parent
     * @return nouvelle instance de ActivityEditBinding
     */
    @NonNull
    public static EditActivityBinding inflate(
            @NonNull LayoutInflater layoutInflater, @Nullable ViewGroup parent, boolean attachToParent)
    {
        return new EditActivityBinding((RelativeLayout) layoutInflater.inflate(ID_LAYOUT, parent, attachToParent));
    }

    /**
     * associe un nouveau ActivityEditBinding à une vue existante
     * @param root vue racine à associer
     * @return nouvelle instance de ActivityEditBinding
     */
    @NonNull
    public static EditActivityBinding bind(@NonNull RelativeLayout root)
    {
        return new EditActivityBinding(root);
    }

    /**
     * retourne la vue racine du layout attaché à ce ViewBinding
     * @return View racine du layout
     */
    @NonNull public RelativeLayout getRoot()
    {
        return root;
    }}