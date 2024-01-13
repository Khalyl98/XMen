package com.example.xmen;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class XMenAdapter extends RecyclerView.Adapter<XMenViewHolder>{
    private List<XMen> liste;
    private List<XMen> filteredData;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private XMenAdapter.OnItemClickListener onItemClickListener;
    public XMenAdapter(List<XMen> liste) {
        this.liste = liste;
        this.filteredData = new ArrayList<>(liste);
    }
    public int getOriginalPosition(int filetredPosition){
        if(filetredPosition>=0 && filetredPosition < filteredData.size()){
            XMen filtredXMen = filteredData.get(filetredPosition);
            return liste.indexOf(filtredXMen);
        }
        else{
            return -1;
        }
    }
    @NonNull
    @Override
    public XMenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        XMenBinding ui = XMenBinding.inflate(

                LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new XMenViewHolder(ui,this);
    }

    @Override
    public void onBindViewHolder(@NonNull XMenViewHolder holder, int position) {
        holder.setXMen(filteredData.get(position));
        holder.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {

        return this.filteredData.size();
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void filter(String query, String selectedFilter) {
        filteredData.clear();

        if (TextUtils.isEmpty(query)) {
            filteredData.addAll(liste);
        } else {
            query = query.toLowerCase().trim();
            for (XMen item : liste) {
                // Add your filtering logic based on the selected filter
                String filterValue = getFilterValue(item, selectedFilter);
                if (filterValue != null && filterValue.toLowerCase().contains(query)) {
                    filteredData.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }

    private String getFilterValue(XMen xMen, String selectedFilter) {
        switch (selectedFilter) {
            case "Nom":
                return xMen.getNom();
            case "Pouvoirs":
                return xMen.getPouvoirs();
            case "Description":
                return xMen.getDescription();
            case "Alias":
                return xMen.getAlias();
            default:
                return null;
        }
    }
}
