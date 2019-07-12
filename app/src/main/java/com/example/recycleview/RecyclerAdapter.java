package com.example.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    public CheckBox checkBox;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkBox);
        textView = itemView.findViewById(R.id.txtDescription);
    }


    public Boolean getCheckBoxStatus() {
        return checkBox.isChecked();
    }

    public void setTextView(String text) {
        this.textView.setText(text);
    }
}


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>  {
    private List<Item> item_name;
    private Context context;
    private int is_checked;
    private ViewGroup viewGroup;


    public RecyclerAdapter(List<Item> item_name, Context context) {
        this.item_name = item_name;
        this.context = context;
        this.is_checked = -1;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_item_recycler_view,viewGroup,false);
        this.viewGroup = viewGroup;
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder recyclerViewHolder, final int i) {
        recyclerViewHolder.setTextView(item_name.get(i).getName());

        if(item_name.get(i).isChecked == false)recyclerViewHolder.checkBox.setChecked(false);
        if(item_name.get(i).isChecked == true)recyclerViewHolder.checkBox.setChecked(true);


        recyclerViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(is_checked < 0)is_checked = i;
                else {
                    if(is_checked != i && isChecked ==true){
                        final int temp = is_checked;
                        item_name.get(is_checked).setChecked(false);
                        item_name.get(i).setChecked(true);
                        is_checked=i;
                        buttonView.post(new Runnable() {
                            @Override
                            public void run() {
                                notifyItemChanged(temp);
                            }
                        });//item decoration
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_name.size();
    }

}
