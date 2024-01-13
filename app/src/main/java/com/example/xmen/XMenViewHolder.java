package com.example.xmen;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class XMenViewHolder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener
{
    private final XMenBinding ui;
    private XMenAdapter adapter;
    public static final int MENU_EDIT = 1;
    public static final int MENU_DELETE = 2;
    public void setOnItemClickListener(XMenAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    private void onClick(View v) {
        int filtredPosition =adapter.getOriginalPosition(getAdapterPosition());
        if (onItemClickListener != null) // test par précaution
            onItemClickListener.onItemClick(filtredPosition);
    }
    private XMenAdapter.OnItemClickListener onItemClickListener;
    public XMenViewHolder(@NonNull XMenBinding ui ,XMenAdapter adapter){
        super(ui.getRoot());
        this.ui = ui;
        this.adapter=adapter;
        itemView.setOnClickListener(this::onClick);
        itemView.setOnCreateContextMenuListener(this);

    }
    public void setXMen(XMen xmen) {
        ui.textViewNom.setText(xmen.getNom());
        ui.textViewAlias.setText(xmen.getAlias());
        ui.textViewDescription.setText(xmen.getDescription());
        ui.textViewConfrerie.setText(xmen.getConfrerie());
        ui.textViewPouvoirs.setText(xmen.getPouvoirs());

        // Update the image
        ui.imageViewXMen.setImageResource(xmen.getIdImage());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // position de l'élément concerné

        int position = getAdapterPosition();
// titre du menu = nom du X-Men
        menu.setHeaderTitle(ui.textViewNom.getText());
// utiliser l'ordre pour stocker la position destinée à l'activité
        menu.add(0, MENU_EDIT, position, "Edit");
        menu.add(0, MENU_DELETE, position, "Delete");
    }
}

