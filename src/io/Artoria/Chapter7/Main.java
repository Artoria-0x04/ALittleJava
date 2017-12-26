package io.Artoria.Chapter7;

import sun.reflect.generics.tree.Tree;

abstract class TreeD {
}

class Bud extends TreeD {
}

class Split extends TreeD {
    TreeD left;
    TreeD right;

    public Split(TreeD left, TreeD right) {
        this.left = left;
        this.right = right;
    }
}

class Flat extends TreeD {
    FruitD fruit;
    TreeD tree;

    public Flat(FruitD fruit, TreeD tree) {
        this.fruit = fruit;
        this.tree = tree;
    }
}

abstract class FruitD {
}

class Peach extends FruitD {
    public boolean equals(Object fruit) {
        return fruit instanceof Peach;
    }
}

class Apple extends FruitD {
    public boolean equals(Object fruit) {
        return fruit instanceof Apple;
    }
}

class Pear extends FruitD {
    public boolean equals(Object fruit) {
        return fruit instanceof Pear;
    }
}

class Lemon extends FruitD {
    public boolean equals(Object fruit) {
        return fruit instanceof Lemon;
    }
}

class Fig extends FruitD {
    public boolean equals(Object fruit) {
        return fruit instanceof Fig;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
