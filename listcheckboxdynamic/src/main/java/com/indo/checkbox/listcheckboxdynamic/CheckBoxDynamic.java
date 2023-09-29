package com.indo.checkbox.listcheckboxdynamic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.indo.checkbox.listcheckboxdynamic.model.ChecboxModel;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxDynamic<T> extends LinearLayout {

    private final AttributeSet _attrs;
    private List<ChecboxModel<Object>> items = new ArrayList<>();
    private AdapterCheckbox adapterCheckbox;
    private Context _context;

    public CheckBoxDynamic(Context context) {
        this(context, null);
    }

    private CheckBoxDynamic.CheckBoxDynamicInterface checkBoxDynamicInterface;

    public CheckBoxDynamic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this._context = context;
        this._attrs = attrs;

        // Set Layout
        initView();
    }

    private void initView() {
        LinearLayout linearLayout = new LinearLayout(_context);
        linearLayout.setOrientation(VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setLayoutParams(params);
        RecyclerView recyclerView = new RecyclerView(_context);
        RecyclerView.LayoutParams paramsRV = new
                RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        );
        recyclerView.setVisibility(VISIBLE);
        recyclerView.setLayoutParams(paramsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(_context));
        recyclerView.setHasFixedSize(false);

        adapterCheckbox = new AdapterCheckbox(items, new AdapterCheckbox.onClickListerner() {
            @Override
            public void OnClickListener(int position, Object object) {
                checkBoxDynamicInterface.onClickLister(position,object);
            }
        });
        recyclerView.setAdapter(adapterCheckbox);

        linearLayout.addView(recyclerView);
        addView(linearLayout);
    }

    public CheckBoxDynamic setItems(List<Object> items1){
        items.clear();
        for (int i = 0; i < items1.size(); i++) {
            items.add(new ChecboxModel<Object>(false,items1.get(i)));
        }
        adapterCheckbox.setItems(items);
        return this;
    }


    public List<AdapterCheckbox.Holder> getHolder() {
        return  adapterCheckbox.getHolders();
    }

    public void setChecked(boolean isChecked, int index){
        adapterCheckbox.getList().get(index).setCheck(isChecked);
        adapterCheckbox.notifyDataSetChanged();
    }


    public interface CheckBoxDynamicInterface{
        void onClickLister(int position, Object object);
    }

    public void setCheckBoxDynamicListener(CheckBoxDynamicInterface dynamicInterface){
        this.checkBoxDynamicInterface = dynamicInterface;
    }
}
