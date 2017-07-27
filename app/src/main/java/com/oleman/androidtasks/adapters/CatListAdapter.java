package com.oleman.androidtasks.adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oleman.androidtasks.R;

import java.util.List;

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CatViewHolder>{

    private List<String> nameList;
    private List<String> ageList;
    private List<String> colorList;
    private List<String> careerList;

    public CatListAdapter(List<String> nameList, List<String> ageList, List<String> colorList, List<String> careerList) {
        this.nameList = nameList;
        this.ageList = ageList;
        this.colorList = colorList;
        this.careerList = careerList;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item, parent, false);

        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        holder.name.setText(nameList.get(position));
        holder.age.setText(ageList.get(position));
        holder.color.setText(colorList.get(position));
        holder.career.setText(careerList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView name;
        TextView age;
        TextView color;
        TextView career;

        public CatViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cat_card);

            name = (TextView) itemView.findViewById(R.id.nameCard_t7);
            age = (TextView) itemView.findViewById(R.id.ageCard_t7);
            color = (TextView) itemView.findViewById(R.id.colorCard_t7);
            career = (TextView) itemView.findViewById(R.id.careerCard_t7);

        }
    }

}
