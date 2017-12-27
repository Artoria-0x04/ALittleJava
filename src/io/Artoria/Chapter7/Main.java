package io.Artoria.Chapter7;

import io.Artoria.MyToString;

abstract class FruitD extends MyToString {
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

abstract class TreeD extends MyToString {
    abstract boolean accept(bTreeVisitorI ask);

    abstract int accept(iTreeVisitorI ask);

    abstract TreeD accept(tTreeVisitorI ask);

    abstract Object accept(TreeVisitorI ask);
}

class Bud extends TreeD {
    @Override
    boolean accept(bTreeVisitorI ask) {
        return ask.forBud();
    }

    @Override
    int accept(iTreeVisitorI ask) {
        return ask.forBud();
    }

    @Override
    TreeD accept(tTreeVisitorI ask) {
        return ask.forBud();
    }

    @Override
    Object accept(TreeVisitorI ask) {
        return ask.forBud();
    }
}

class Split extends TreeD {
    TreeD left;
    TreeD right;

    public Split(TreeD left, TreeD right) {
        this.left = left;
        this.right = right;
    }

    @Override
    boolean accept(bTreeVisitorI ask) {
        return ask.forSplit(left, right);
    }

    @Override
    int accept(iTreeVisitorI ask) {
        return ask.forSplit(left, right);
    }

    @Override
    TreeD accept(tTreeVisitorI ask) {
        return ask.forSplit(left, right);
    }

    @Override
    Object accept(TreeVisitorI ask) {
        return ask.forSplit(left, right);
    }
}

class Flat extends TreeD {
    FruitD fruit;
    TreeD tree;

    public Flat(FruitD fruit, TreeD tree) {
        this.fruit = fruit;
        this.tree = tree;
    }

    @Override
    boolean accept(bTreeVisitorI ask) {
        return ask.forFlat(fruit, tree);
    }

    @Override
    int accept(iTreeVisitorI ask) {
        return ask.forFlat(fruit, tree);
    }

    @Override
    TreeD accept(tTreeVisitorI ask) {
        return ask.forFlat(fruit, tree);
    }

    @Override
    Object accept(TreeVisitorI ask) {
        return ask.forFlat(fruit, tree);
    }
}

interface bTreeVisitorI {
    boolean forBud();

    boolean forFlat(FruitD f, TreeD t);

    boolean forSplit(TreeD l, TreeD r);
}

class bIsFlatV implements bTreeVisitorI {
    @Override
    public boolean forBud() {
        return true;
    }

    @Override
    public boolean forFlat(FruitD f, TreeD t) {
        return t.accept(this);
    }

    @Override
    public boolean forSplit(TreeD l, TreeD r) {
        return false;
    }
}

class bIsSplitV implements bTreeVisitorI {
    @Override
    public boolean forBud() {
        return true;
    }

    @Override
    public boolean forFlat(FruitD f, TreeD t) {
        return false;
    }

    @Override
    public boolean forSplit(TreeD l, TreeD r) {
        if (l.accept(this)) {
            return r.accept(this);
        } else {
            return false;
        }
    }
}

class bHasFruitV implements bTreeVisitorI {
    @Override
    public boolean forBud() {
        return false;
    }

    @Override
    public boolean forFlat(FruitD f, TreeD t) {
        return true;
    }

    @Override
    public boolean forSplit(TreeD l, TreeD r) {
        return l.accept(this) || r.accept(this);
    }
}

interface iTreeVisitorI {
    int forBud();

    int forFlat(FruitD f, TreeD t);

    int forSplit(TreeD l, TreeD r);
}

class iHeightV implements iTreeVisitorI {
    @Override
    public int forBud() {
        return 0;
    }

    @Override
    public int forFlat(FruitD f, TreeD t) {
        return t.accept(this) + 1;
    }

    @Override
    public int forSplit(TreeD l, TreeD r) {
        return Math.max(l.accept(this), r.accept(this)) + 1;
    }
}

class iOccursV implements iTreeVisitorI {
    FruitD a;

    public iOccursV(FruitD a) {
        this.a = a;
    }

    @Override
    public int forBud() {
        return 0;
    }

    @Override
    public int forFlat(FruitD f, TreeD t) {
        if (a.equals(f)) {
            return 1 + t.accept(this);
        } else {
            return t.accept(this);
        }
    }

    @Override
    public int forSplit(TreeD l, TreeD r) {
        return l.accept(this) + r.accept(this);
    }
}

interface tTreeVisitorI {
    TreeD forBud();

    TreeD forSplit(TreeD l, TreeD r);

    TreeD forFlat(FruitD f, TreeD t);
}

class tSubstV implements tTreeVisitorI {
    FruitD neue;
    FruitD old;

    public tSubstV(FruitD neue, FruitD old) {
        this.neue = neue;
        this.old = old;
    }

    @Override
    public TreeD forBud() {
        return new Bud();
    }

    @Override
    public TreeD forSplit(TreeD l, TreeD r) {
        return new Split(l.accept(this), r.accept(this));
    }

    @Override
    public TreeD forFlat(FruitD f, TreeD t) {
        if (f.equals(old)) {
            return new Flat(neue, t.accept(this));
        } else {
            return new Flat(f, t.accept(this));
        }
    }
}

interface TreeVisitorI {
    Object forBud();

    Object forSplit(TreeD l, TreeD r);

    Object forFlat(FruitD f, TreeD t);
}

class IsFlatV implements TreeVisitorI {
    @Override
    public Object forBud() {
        return true;
    }

    @Override
    public Object forSplit(TreeD l, TreeD r) {
        return false;
    }

    @Override
    public Object forFlat(FruitD f, TreeD t) {
        return t.accept(this);
    }
}

class IsSplitV implements TreeVisitorI {
    @Override
    public Object forBud() {
        return true;
    }

    @Override
    public Object forSplit(TreeD l, TreeD r) {
        return ((Boolean) l.accept(this)).booleanValue() &&
               ((Boolean) r.accept(this)).booleanValue();
    }

    @Override
    public Object forFlat(FruitD f, TreeD t) {
        return false;
    }
}

class OccursV implements TreeVisitorI {
    FruitD a;

    public OccursV(FruitD a) {
        this.a = a;
    }

    @Override
    public Object forBud() {
        return 0;
    }

    @Override
    public Object forSplit(TreeD l, TreeD r) {
        return (Integer) l.accept(this) + (Integer) r.accept(this);
    }

    @Override
    public Object forFlat(FruitD f, TreeD t) {
        if (f.equals(a)) {
            return 1 + (Integer) t.accept(this);
        } else {
            return t.accept(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TreeD tree = new Split(new Split(new Split(new Bud(),
                                                   new Split(new Bud(),
                                                             new Bud())),
                                         new Bud()),
                               new Split(new Bud(),
                                         new Bud())
        );
        TreeD treeF = new Split(new Split(new Split(new Bud(),
                                                    new Bud()),
                                          new Flat(new Apple(),
                                                   new Bud())),
                                new Flat(new Apple(),
                                         new Bud()));
        TreeD tree2 = new Flat(new Pear(),
                               new Flat(new Apple(),
                                        new Bud()));
        System.out.println("\n" +
                           tree + "\n" +
                           tree2 + "\n" +
                           tree2.accept(new bIsFlatV()) + "\n" +
                           tree.accept(new IsSplitV()) + "\n" +
                           treeF.accept(new bHasFruitV()) + "\n" +
                           tree.accept(new iHeightV()) + "\n" +
                           treeF.accept(new tSubstV(new Peach(), new Apple())) + "\n" +
                           treeF.accept(new OccursV(new Apple())) + "\n"
        );
    }
}
