package com.firat.shoppingcart;

public class Category {
    private String title;
    private Category parent;

    public Category(String title, Category parent) {
        this.title = title;
        this.parent = parent;
    }

    public Category(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", parent=" + parent +
                '}';
    }

    public String getTitle() {
        return title;
    }
}
