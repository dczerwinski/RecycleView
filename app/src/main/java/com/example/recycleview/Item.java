package com.example.recycleview;

public class Item {
    String name;
    Boolean isChecked;

    public Item(String name, Boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public String getName() {
        return name;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
