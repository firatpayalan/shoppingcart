package com.firat.shoppingcart.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private String title;
    private Category child;

    public Category(String title) {
        this.title = title;
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

    /**
     * @param childrens each member has been collected in this structure
     * @param child next child
     * @return all child elements of the category....feels like müge anlı
     */
    public List<String> allChildren(List<String> childrens,Category child){
        if (child!=null){
            childrens.add(child.getTitle());
            return allChildren(childrens,child.getChild());
        }
        return childrens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
