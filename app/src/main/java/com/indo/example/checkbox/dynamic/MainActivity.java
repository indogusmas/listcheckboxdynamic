package com.indo.example.checkbox.dynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.indo.checkbox.listcheckboxdynamic.AdapterCheckbox;
import com.indo.checkbox.listcheckboxdynamic.CheckBoxDynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private CheckBoxDynamic checkBoxDynamic;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxDynamic = (CheckBoxDynamic) findViewById(R.id.checkbox_dyanmic);
        btnSave =  findViewById(R.id.btn_save);
        List<String> listDumyy = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listDumyy.add("Item "+ i);
        }
        List<Object> objectList = Arrays.asList(listDumyy.toArray());
        checkBoxDynamic.setItems(objectList);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<AdapterCheckbox.Holder> list = checkBoxDynamic.getHolder();
                for (int i = 0; i < list.size(); i++) {

                }
            }
        });
        checkBoxDynamic.setCheckBoxDynamicListener(new CheckBoxDynamic.CheckBoxDynamicInterface() {
            @Override
            public void onClickLister(int position, Object object) {
                Log.d(TAG, "onClickLister: "+ position + " "+ "Object "+ object.toString());
                if (position == 0) {
                    List<AdapterCheckbox.Holder> list = checkBoxDynamic.getHolder();
                    boolean ischecked = list.get(0).mbinding.tvCheckBox.isChecked();
                    for (int i = 0; i < list.size(); i++) {
                        checkBoxDynamic.setChecked(ischecked,i);
                    }
                }
            }
        });
    }

}