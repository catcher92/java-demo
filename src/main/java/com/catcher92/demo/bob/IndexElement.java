package com.catcher92.demo.bob;

public class IndexElement {

    private final String name;

    public IndexElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean needBalance() {
        return !name.endsWith("_");
    }

    public boolean canBalance(IndexElement e) {
        String name1 = name.endsWith("'") ? name.substring(0, name.length() - 1) : name;
        String name2 = e.name.endsWith("'") ? e.name.substring(0, e.name.length() - 1) : e.name;
        return !name.equals(e.name) && name1.equals(name2);
    }

    @Override
    public String toString() {
        return name;
    }

}
