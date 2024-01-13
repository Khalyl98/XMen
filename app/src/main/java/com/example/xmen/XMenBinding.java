package com.example.xmen;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;

public class XMenBinding {

    // Identifiant du layout associé à ce View Binding
    private static final int ID_LAYOUT = R.layout.x_men;

    // Vue racine du layout, voir getRoot()
    @NonNull
    private final LinearLayout root;

    // Objets Java associés aux vues avec identifiants
    @NonNull
    public final ImageView imageViewXMen;

    @NonNull
    public final TextView textViewNom;

    @NonNull
    public final TextView textViewAlias;

    @NonNull
    public final TextView textViewDescription;
    @NonNull
    public final TextView textViewConfrerie;
    @NonNull
    public final TextView textViewPouvoirs;

    /**
     * Constructeur privé : utiliser inflate et bind
     *
     * @param root Vue racine à associer à this
     */
    private XMenBinding(@NonNull LinearLayout root) {
        this.root = root;
        // Récupérer les vues du layout
        imageViewXMen = root.findViewById(R.id.imageViewXMen);
        textViewNom = root.findViewById(R.id.textViewNom);
        textViewAlias = root.findViewById(R.id.textViewAlias);
        textViewDescription = root.findViewById(R.id.textViewDescription);
        textViewConfrerie= root.findViewById(R.id.textViewConfrerie);
        textViewPouvoirs = root.findViewById(R.id.textViewPouvoirs);
    }

    /**
     * Instancie un nouveau layout et retourne un nouveau XMenBinding
     *
     * @param layoutInflater Expanseur de layout
     * @return Nouvelle instance de XMenBinding
     */
    @NonNull
    public static XMenBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return new XMenBinding((LinearLayout) layoutInflater.inflate(ID_LAYOUT, null, false));
    }

    /**
     * Instancie un nouveau layout et retourne un nouveau XMenBinding
     *
     * @param layoutInflater Expanseur de layout
     * @param parent         Vue parente à laquelle rattacher celle-ci, peut être null
     * @param attachToParent True s'il faut faire un attachement au parent
     * @return Nouvelle instance de XMenBinding
     */
    @NonNull
    public static XMenBinding inflate(
            @NonNull LayoutInflater layoutInflater, @NonNull ViewGroup parent, boolean attachToParent) {
        return new XMenBinding((LinearLayout) layoutInflater.inflate(ID_LAYOUT, parent, attachToParent));
    }

    /**
     * Associe un nouveau XMenBinding à une vue existante
     *
     * @param root Vue racine à associer
     * @return Nouvelle instance de XMenBinding
     */
    @NonNull
    public static XMenBinding bind(@NonNull LinearLayout root) {
        return new XMenBinding(root);
    }

    /**
     * Retourne la vue racine du layout attaché à ce ViewBinding
     *
     * @return View racine du layout
     */
    @NonNull
    public LinearLayout getRoot() {
        return root;
    }
}
