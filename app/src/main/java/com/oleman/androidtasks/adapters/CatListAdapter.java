package com.oleman.androidtasks.adapters;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oleman.androidtasks.R;

import java.util.List;

import static com.oleman.androidtasks.MainActivity.LOG_TAG;

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CatViewHolder>{

    private List<Integer> idList;
    private List<String> nameList;
    private List<String> ageList;
    private List<String> colorList;
    private List<String> careerList;
    private Context context;



    public CatListAdapter() {
    }

    public CatListAdapter(Context context, List<Integer> idList, List<String> nameList, List<String> ageList, List<String> colorList, List<String> careerList) {
        this.idList = idList;
        this.nameList = nameList;
        this.ageList = ageList;
        this.colorList = colorList;
        this.careerList = careerList;
        this.context = context;

    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task7_cat_item, parent, false);

        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {

        holder.context = context;
        holder.id = ""+idList.get(position);
        holder.name.setText(nameList.get(position));
        holder.age.setText(ageList.get(position));
        holder.color.setText(colorList.get(position));
        holder.career.setText(careerList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {

        CardView cardView;
        TextView name;
        TextView age;
        TextView color;
        TextView career;
        String id = "";
        Context context;

        public CatViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cat_card);

            name = itemView.findViewById(R.id.nameCard_t7);
            age = itemView.findViewById(R.id.ageCard_t7);
            color = itemView.findViewById(R.id.colorCard_t7);
            career = itemView.findViewById(R.id.careerCard_t7);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(view.getContext(), view);
                    popup.inflate(R.menu.task7_popup_menu);
                    popup.setOnMenuItemClickListener(CatViewHolder.this);
                    popup.show();
                }
            });

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            switch (item.getItemId()){
                case R.id.delete_popup_t7:
                    if (id.equalsIgnoreCase("")) break;
                    int delCount = db.delete(DBHelper.TABLE_CATS, DBHelper.KEY_ID+"= "+ id, null);
                    //для удаления поля по имени
                    //int delCount = db.delete(DBHelper.TABLE_CATS, DBHelper.KEY_NAME+" = ?", new String[]{name});

                    Log.d(LOG_TAG, "deleted rows count = "+delCount);
                    Toast.makeText(context, "Cat has been deleted.", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

}
