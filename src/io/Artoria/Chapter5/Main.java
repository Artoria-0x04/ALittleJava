package io.Artoria.Chapter5;

import io.Artoria.MyToString;

abstract class PointD extends MyToString {
    int x;
    int y;
    PointD(int _x, int _y) {
        x = _x;
        y = _y;
    }
    abstract int distanceToO();
    boolean closerToO(PointD p) {
        return distanceToO() <= p.distanceToO();
    }
}
class CartesianPt extends PointD {
    CartesianPt(int _x, int _y) {
        super(_x, _y);
    }
    // -----------------------------------
    public int distanceToO() {
        return (int) Math.sqrt(x*x + y*y);
    }
}
class ManhattanPt extends PointD {
    ManhattanPt(int _x, int _y) {
        super(_x, _y);
    }
    // -----------------------------------
    public int distanceToO() {
        return x + y;
    }
}

abstract class NumD extends MyToString {}

class Zero extends NumD {
    public boolean equals(Object o) {
        return o instanceof Zero;
    }
}
class OneMoreThan extends NumD {
    NumD predecessor;
    OneMoreThan(NumD p) {
        predecessor = p;
    }
    // -------------------------------------
    public boolean equals(Object o) {
        if (o instanceof OneMoreThan) {
            return predecessor.equals((OneMoreThan) ((OneMoreThan) o).predecessor);
        } else {
            return false;
        }
    }
}

abstract class LayerD extends MyToString {}

class Base extends LayerD {
    Object o;
    Base(Object _o) {
        o = _o;
    }
    // ----------------------------------
}
class Slice extends LayerD {
    LayerD l;
    Slice(LayerD _l) {
        l = _l;
    }
    // ---------------------------------
}

abstract class ShishD extends MyToString {
    OnlyOnionsV ooFn = new OnlyOnionsV();
    IsVegetarianV ivFn = new IsVegetarianV();
    abstract boolean onlyOnions();
    abstract boolean isVegetarian();
}

class OnlyOnionsV extends MyToString {
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

class IsVegetarianV extends MyToString {
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
    boolean isVegetarian() {
        return ivFn.forSkewer();
    }
}
class Onion extends ShishD {
    ShishD s;
    Onion(ShishD _s) {
        s = _s;
    }
    //----------------------------------
    @Override
    boolean onlyOnions() {
        return ooFn.forOnion(s);
    }
    boolean isVegetarian() {
        return ivFn.forOnion(s);
    }
}
class Lamb extends ShishD {
    ShishD s;
    Lamb(ShishD _s) {
        s = _s;
    }
    //----------------------------------
    @Override
    boolean onlyOnions() {
        return ooFn.forLamb(s);
    }
    boolean isVegetarian() {
        return ivFn.forLamb(s);
    }
}
class Tomato extends ShishD {
    ShishD s;
    Tomato(ShishD _s) {
        s = _s;
    }
    //----------------------------------
    @Override
    boolean onlyOnions() {
        return ooFn.forTomato(s);
    }
    boolean isVegetarian() {
        return ivFn.forTomato(s);
    }
}

abstract class KebabD extends MyToString {
    abstract boolean isVeggie();
    abstract Object whatHolder();
}

class Holder extends KebabD {
    Object o;
    Holder(Object _o) {
        o = _o;
    }
    //-------------------------------
    @Override
    boolean isVeggie() {
        return true;
    }
    @Override
    Object whatHolder() {
        return o;
    }
}
class Shallot extends KebabD {
    KebabD k;
    Shallot(KebabD _k) {
        k = _k;
    }
    //-------------------------------
    @Override
    boolean isVeggie() {
        return k.isVeggie();
    }
    @Override
    Object whatHolder() {
        return k.whatHolder();
    }
}
class Shrimp extends KebabD {
    KebabD k;
    Shrimp(KebabD _k) {
        k = _k;
    }
    //-------------------------------

    @Override
    boolean isVeggie() {
        return false;
    }
    @Override
    Object whatHolder() {
        return k.whatHolder();
    }
}
class Radish extends KebabD {
    KebabD k;
    Radish(KebabD _k) {
        k = _k;
    }
    //-------------------------------

    @Override
    boolean isVeggie() {
        return k.isVeggie();
    }

    @Override
    Object whatHolder() {
        return k.whatHolder();
    }
}
class Zucchini extends KebabD {
    KebabD k;
    Zucchini(KebabD _k) {
        k = _k;
    }
    //-------------------------------

    @Override
    boolean isVeggie() {
        return k.isVeggie();
    }

    @Override
    Object whatHolder() {
        return k.whatHolder();
    }
}

abstract class RodD extends MyToString {}

class Dagger extends RodD {}
class Sabre extends RodD {}
class Sword extends RodD {}

abstract class PizzaD extends MyToString {
    RemoveAnchovyV remFn = new RemoveAnchovyV();
    TopAnchovyWithCheese topFn = new TopAnchovyWithCheese();
    SubstituteAnchovyByCheese subFn = new SubstituteAnchovyByCheese();

    abstract PizzaD removeAnchovy();

    abstract PizzaD topAnchovyWithCheese();

    abstract PizzaD substituteAnchovyByCheese();
}

class RemoveAnchovyV extends MyToString {
    PizzaD forCrust() {
        return new Crust();
    }

    PizzaD forCheese(PizzaD p) {
        return new Cheese(p.removeAnchovy());
    }

    PizzaD forOlive(PizzaD p) {
        return new Olive(p.removeAnchovy());
    }

    PizzaD forAnchovy(PizzaD p) {
        return p.removeAnchovy();
    }

    PizzaD forSausage(PizzaD p) {
        return new Sausage(p.removeAnchovy());
    }

    PizzaD forSpinach(PizzaD p) {
        return new Spinach(p.removeAnchovy());
    }
}

class TopAnchovyWithCheese extends MyToString {
    PizzaD forCrust() {
        return new Crust();
    }

    PizzaD forCheese(PizzaD p) {
        return new Cheese(p.topAnchovyWithCheese());
    }

    PizzaD forOlive(PizzaD p) {
        return new Olive(p.topAnchovyWithCheese());
    }

    PizzaD forAnchovy(PizzaD p) {
        return new Cheese(new Anchovy(p.topAnchovyWithCheese()));
    }

    PizzaD forSausage(PizzaD p) {
        return new Sausage(p.topAnchovyWithCheese());
    }

    PizzaD forSpinach(PizzaD p) {
        return new Spinach(p.topAnchovyWithCheese());
    }
}

class SubstituteAnchovyByCheese extends MyToString {
    PizzaD forCrust() {
        return new Crust();
    }

    PizzaD forCheese(PizzaD p) {
        return new Cheese(p.substituteAnchovyByCheese());
    }

    PizzaD forOlive(PizzaD p) {
        return new Olive(p.substituteAnchovyByCheese());
    }

    PizzaD forAnchovy(PizzaD p) {
        return new Cheese(p.substituteAnchovyByCheese());
    }

    PizzaD forSausage(PizzaD p) {
        return new Sausage(p.substituteAnchovyByCheese());
    }

    PizzaD forSpinach(PizzaD p) {
        return new Spinach(p.substituteAnchovyByCheese());
    }
}

class Crust extends PizzaD {
    @Override
    PizzaD removeAnchovy() {
        return remFn.forCrust();
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return topFn.forCrust();
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return subFn.forCrust();
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
        return remFn.forCheese(p);
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return topFn.forCheese(p);
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return subFn.forCheese(p);
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
        return remFn.forOlive(p);
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return topFn.forOlive(p);
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return subFn.forOlive(p);
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
        return remFn.forAnchovy(p);
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return topFn.forAnchovy(p);
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return subFn.forAnchovy(p);
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
        return remFn.forSausage(p);
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return topFn.forSausage(p);
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return subFn.forSausage(p);
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
        return remFn.forSpinach(p);
    }

    @Override
    PizzaD topAnchovyWithCheese() {
        return topFn.forSpinach(p);
    }

    @Override
    PizzaD substituteAnchovyByCheese() {
        return subFn.forSpinach(p);
    }
}

abstract class PieD extends MyToString {
    RemAV raFn = new RemAV();
    abstract PieD remA();
    RemFishV rfFn = new RemFishV();
    abstract PieD remFish(FishD f);
    RemIntV riFn = new RemIntV();
    abstract PieD remInt(Integer i);
    RemV rFn = new RemV();
    abstract PieD rem(Object o);
    SubstV subFn = new SubstV();
    abstract PieD subst(Object src, Object target);
}

class RemAV extends MyToString {
    PieD forTopping(Object t, PieD p) {
        if (t instanceof Anchovyy) {
            return p.remA();
        } else {
            return new Topping(t, p.remA());
        }
    }
    PieD forBottom() {
        return new Bottom();
    }
}

class RemFishV extends MyToString {
    PieD forTopping(Object t, PieD p, FishD f) {
        if (f.equals(t)) {
            return p.remFish(f);
        } else {
            return new Topping(t, p.remFish(f));
        }
    }
    PieD forBottom(FishD f) {
        return new Bottom();
    }
}

class RemIntV extends MyToString {
    PieD forTopping(Object t, PieD p, Integer i) {
        if (i.equals(t)) {
            return p.remInt(i);
        } else {
            return new Topping(t, p.remInt(i));
        }
    }
    PieD forBottom(Integer i) {
        return new Bottom();
    }
}

class RemV extends MyToString {
    PieD forTopping(Object t, PieD p, Object o) {
        if (o.equals(t)) {
            return p.rem(o);
        } else {
            return new Topping(t, p.rem(o));
        }
    }
    PieD forBottom(Object o) {
        return new Bottom();
    }
}

class SubstV extends MyToString {
    PieD forTopping(Object t, PieD p, Object src, Object target) {
        if (target.equals(t)) {
            return new Topping(src, p.subst(src, target));
        } else {
            return new Topping(t, p.subst(src, target));
        }
    }
    PieD forBottom(Object src, Object target) {
        return new Bottom();
    }
}
class Bottom extends PieD {
    @Override
    PieD remA() {
        return raFn.forBottom();
    }

    @Override
    PieD remFish(FishD f) {
        return rfFn.forBottom(f);
    }

    @Override
    PieD remInt(Integer i) {
        return riFn.forBottom(i);
    }

    @Override
    PieD rem(Object o) {
        return rFn.forBottom(o);
    }

    @Override
    PieD subst(Object src, Object target) {
        return subFn.forBottom(src, target);
    }
}
class Topping extends PieD {
    Object t;
    PieD r;
    Topping(Object _t, PieD _r) {
        t = _t;
        r = _r;
    }
    //---------------------------------

    @Override
    PieD remA() {
        return raFn.forTopping(t, r);
    }

    @Override
    PieD remFish(FishD f) {
        return rfFn.forTopping(t, r, f);
    }

    @Override
    PieD remInt(Integer i) {
        return riFn.forTopping(t, r, i);
    }

    @Override
    PieD rem(Object o) {
        return rFn.forTopping(t, r, o);
    }

    @Override
    PieD subst(Object src, Object target) {
        return subFn.forTopping(t, r, src, target);
    }
}


abstract class FishD extends MyToString {
}

class Anchovyy extends FishD {
    public boolean equals(Object o) {
        return (o instanceof Anchovyy);
    }
}

class Salmon extends FishD {
    public boolean equals(Object o) {
        return (o instanceof Salmon);
    }
}

class Tuna extends FishD {
    public boolean equals(Object o) {
        return (o instanceof Tuna);
    }
}


public class Main {

    public static void main(String[] args) {
        // TODO: 2017/1/22 create instance
        CartesianPt pt = new CartesianPt(3, 4);
        NumD n = new OneMoreThan(new Zero());
        ShishD shish = new Onion(
                new Onion(
                new Lamb(
                new Skewer())));
        System.out.println(
                pt.toString() + "\n" +
                n.toString()+"\n"+
                shish.toString() +"\n"+
                shish.onlyOnions()
        );
        KebabD kebab = new Shallot(
                new Radish(
                new Shrimp(
                new Holder(
                new Dagger()))));
        System.out.println(kebab.isVeggie());
        System.out.println(kebab.whatHolder());
        System.out.println(pt.closerToO(new ManhattanPt(1,4)));

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
        System.out.println(p.removeAnchovy());
        System.out.println(pi.topAnchovyWithCheese());
        System.out.println(pi.substituteAnchovyByCheese());

        PieD pieD = new Topping(new Salmon(),
                                new Topping(new Tuna(),
                                            new Topping(new Anchovyy(),
                                                        new Bottom())));

        System.out.println(pieD.toString());
        System.out.println(pieD.remA());
        System.out.println(pieD.remFish(new Tuna()));
        System.out.println(pieD.rem(new Tuna()));

        PieD pieI = new Topping(new Salmon(),
                                new Topping(new Integer(5),
                                            new Topping(new Integer(3),
                                                        new Bottom())));
        System.out.println(pieI.toString());
        System.out.println(pieI.remInt(3));
        System.out.println(pieI.rem(3));

        PieD pieA = new Topping(new Anchovyy(),
                                new Topping(new Zero(),
                                            new Bottom()));
        System.out.println(pieA.rem(new Zero()));
        System.out.println(pieA.subst(new Tuna(), new Zero()));
    }
}
