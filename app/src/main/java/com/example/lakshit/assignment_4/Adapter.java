package com.example.lakshit.assignment_4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {


    private List<ListItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
        void onCheckClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public void setListData(ArrayList<ListItem> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.note.setText(item.getNote());
        if (item.isCheck()){
            holder.check_icon.setImageResource(R.drawable.ic_check_box_black_24dp);
        } else {
            holder.check_icon.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }
        holder.icon.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public Adapter(List<ListItem> listData, Context c){
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView note;
        private ImageView icon;
        private View container;
        private ImageView check_icon;

        public Holder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.item_text);
            icon=(ImageView)itemView.findViewById(R.id.item_icon);
            note = (TextView)itemView.findViewById(R.id.item_note);
            check_icon = (ImageView)itemView.findViewById(R.id.item_check);
            check_icon.setOnClickListener(this);
            container=(View)itemView.findViewById(R.id.cont_item);
            container.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cont_item){
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.onCheckClick(getAdapterPosition());
            }
        }
    }
}
