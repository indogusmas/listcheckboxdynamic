package com.indo.checkbox.listcheckboxdynamic;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indo.checkbox.databinding.ItemCheckboxBinding;
import com.indo.checkbox.listcheckboxdynamic.model.ChecboxModel;


public class AdapterCheckbox extends RecyclerView.Adapter<AdapterCheckbox.Holder> {

    public static AdapterCheckbox.onClickListerner onClickListerner;
    private List<ChecboxModel<Object>> items = new ArrayList<>();

    private List<AdapterCheckbox.Holder> holders;

    public AdapterCheckbox(List<ChecboxModel<Object>> items,onClickListerner onClickListerner) {
        this.items = items;
        holders = new ArrayList<>(items.size());
        for (int i = 0; i < holders.size(); i++) {
            holders.add(null);
        }
        this.onClickListerner = onClickListerner;

    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(ItemCheckboxBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.setIsRecyclable(false);
        holders.set(i,holder);
        if (items.get(i).isCheck() == true) {
            holder.mbinding.tvCheckBox.setChecked(true);
        }else {
            holder.mbinding.tvCheckBox.setChecked(false);
        }
        holder.bind(items.get(i).toString());
    }


    public void setItems(List<ChecboxModel<Object>> items) {
        this.items = items;
        holders = new ArrayList<>(items.size());
        for (int i = 0; i < items.size(); i++) {
            holders.add(null);
        }
        notifyDataSetChanged();
    }

    public List<AdapterCheckbox.Holder> getHolders(){
        return  holders;
    }

    public List<ChecboxModel<Object>> getList(){
        return items;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public ItemCheckboxBinding mbinding;

        public Holder(ItemCheckboxBinding binding) {
            super(binding.getRoot());
            this.mbinding = binding;
        }

        public void bind(String item) {
            mbinding.tvCheckBox.setText(item);
            mbinding.tvCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListerner.OnClickListener(getAdapterPosition(),item);
                }
            });
        }
    }

    interface onClickListerner{
        void  OnClickListener(int position,Object object);
    }
}