package com.indo.checkbox.listcheckboxdynamic.model;

import androidx.annotation.NonNull;

public class ChecboxModel<T> {
    private boolean isCheck;
    private  T data;

    public ChecboxModel(boolean isCheck, T data) {
        this.isCheck = isCheck;
        this.data = data;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return data.toString();
    }
}
