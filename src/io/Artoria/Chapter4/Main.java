package io.Artoria.Chapter4;


import io.Artoria.MyToString;

abstract class ShishD extends MyToString {
    OnlyOnionsV ooFn = new OnlyOnionsV();
    IsVegetarianV ivFn = new IsVegetarianV();

    abstract boolean onlyOnions();

    abstract boolean isVegetarian();
}

class OnlyOnionsV {
    boolean forSkewer() {
        return true;
    }

    boolean forOnion(ShishD s) {
        return s.onlyOnions();
    }

    boolean forLamb(ShishD s) {
        return false;
    }

    boolean forTomato(ShishD s) {
        return false;
    }
}

class IsVegetarianV {
    boolean forSkewer() {
        return true;
    }

    boolean forOnion(ShishD s) {
        return s.isVegetarian();
    }

    boolean forLamb(ShishD s) {
        return false;
    }

    boolean forTomato(ShishD s) {
        return s.isVegetarian();
    }
}

class Skewer extends ShishD {
    @Override
    boolean onlyOnions() {
        return ooFn.forSkewer();
    }

    @Override
    boolean isVegetarian() {
        return ivFn.forSkewer();
    }
}

class Onion extends ShishD {
    ShishD s;

    public Onion(ShishD s) {
        this.s = s;
    }

    @Override
    boolean onlyOnions() {
        return ooFn.forOnion(s);
    }

    @Override
    boolean isVegetarian() {
        return ivFn.forOnion(s);
    }
}

class Lamb extends ShishD {
    ShishD s;

    public Lamb(ShishD s) {
        this.s = s;
    }

    @Override
    boolean onlyOnions() {
        return ooFn.forLamb(s);
    }

    @Override
    boolean isVegetarian() {
        return ivFn.forLamb(s);
    }
}

class Tomato extends ShishD {
    ShishD s;

    public Tomato(ShishD s) {
        this.s = s;
    }

    @Override
    boolean onlyOnions() {
        return ooFn.forTomato(s);
    }

    @Override
    boolean isVegetarian() {
        return ivFn.forTomato(s);
    }
}

public class Main {
    public static void main(String[] args) {
        ShishD s = new Tomato(
                new Tomato(
                        new Lamb(
                                new Onion(
                                        new Skewer()))));
        ShishD s2 = new Onion(new Onion(new Skewer()));
        System.out.println(s + "\n" +
                           s.onlyOnions() + "\n" +
                           s2.onlyOnions() + "\n" +
                           s2.isVegetarian() + "\n");
    }
}