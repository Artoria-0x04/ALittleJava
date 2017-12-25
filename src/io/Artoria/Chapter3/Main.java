package io.Artoria.Chapter3;

import io.Artoria.MyToString;


abstract class PizzaD extends MyToString {
    abstract PizzaD removeAnchovy();
    abstract PizzaD topAnchovyWithCheese();
    abstract PizzaD substituteAnchovyByCheese();
}

class Crust extends PizzaD {
    @Override
    PizzaD removeAnchovy() {
        return new Crust();
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return new Crust();
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return new Crust();
    }
}

class Cheese extends PizzaD {
    PizzaD p;
    Cheese(PizzaD _p) {
        p = _p;
    }
    //----------------------------

    @Override
    PizzaD removeAnchovy() {
        return new Cheese(p.removeAnchovy());
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return new Cheese(p.topAnchovyWithCheese());
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return new Cheese(p.substituteAnchovyByCheese());
    }
}

class Olive extends PizzaD {
    PizzaD p;
    Olive(PizzaD _p) {
        p = _p;
    }
    //----------------------------

    @Override
    PizzaD removeAnchovy() {
        return new Olive(p.removeAnchovy());
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return new Olive(p.topAnchovyWithCheese());
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return new Olive(p.substituteAnchovyByCheese());
    }
}

class Anchovy extends PizzaD {
    PizzaD p;
    Anchovy(PizzaD _p) {
        p = _p;
    }
    //----------------------------

    @Override
    PizzaD removeAnchovy() {
        return p.removeAnchovy();
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return new Cheese(new Anchovy(p.topAnchovyWithCheese()));
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return new Cheese(p.substituteAnchovyByCheese());
    }
}

class Sausage extends PizzaD {
    PizzaD p;
    Sausage(PizzaD _p) {
        p = _p;
    }
    //----------------------------

    @Override
    PizzaD removeAnchovy() {
        return new Sausage(p.removeAnchovy());
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return new Sausage(p.topAnchovyWithCheese());
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return new Sausage(p.substituteAnchovyByCheese());
    }
}

class Spinach extends PizzaD {
    PizzaD p;
    Spinach(PizzaD _p) {
        p = _p;
    }
    //----------------------------

    @Override
    PizzaD removeAnchovy() {
        return new Spinach(p.removeAnchovy());
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return new Spinach(p.topAnchovyWithCheese());
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return new Spinach(p.substituteAnchovyByCheese());
    }
}

public class Main {
    public static void main(String[] args) {
        PizzaD p = new Sausage(
                new Olive(
                new Anchovy(
                new Anchovy(
                new Cheese(
                new Crust())))));
        PizzaD pi = new Sausage(
                new Olive(
                new Cheese(
                new Anchovy(
                new Crust()))));
        System.out.println(p.removeAnchovy().topAnchovyWithCheese());
        System.out.println(pi.topAnchovyWithCheese());
        System.out.println(pi.substituteAnchovyByCheese());
    }
}