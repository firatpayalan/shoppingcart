package com.firat.shoppingcart.cart;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String title;
    private Category child;

    public Category(String title, Category child) {
        this.title = title;
        this.child = child;
    }

    public Category(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", parent=" + child +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setChild(Category child) {
        this.child = child;
    }

    public Category getChild() {
        return child;
    }

    public List<String> allChildren(List<String> childrens,Category child){
        if (child!=null){
            childrens.add(child.getTitle());
            return allChildren(childrens,child.getChild());
        }
        return childrens;
    }
}
