//public class MainV1 {
//
//    public static void main(String[] args) {
//        // TODO: 2017/1/22 create instance
//        CartesianPt pt = new CartesianPt(3, 4);
//        NumD n = new OneMoreThan(new Zero());
//        ShishD shish = new Onion(
//                new Onion(
//                        new Skewer()));
//        KebabD kebab = new Shallot(
//                new Radish(
//                        new Shrimp(
//                                new Holder(
//                                        new Dagger()))));
//        System.out.println(
//                pt.toString() + "\n" +
//                        n.toString()+"\n"+
//                        shish.toString() +"\n"+
//                        shish.onlyOnions()
//        );
//        System.out.println(kebab.isVeggie());
//        System.out.println(kebab.whatHolder());
//        System.out.println(pt.closerToO(new ManhattanPt(1,4)));
//
//        PizzaD p = new Sausage(
//                new Olive(
//                        new Anchovy(
//                                new Anchovy(
//                                        new Cheese(
//                                                new Crust())))));
//        PizzaD pi = new Sausage(
//                new Olive(
//                        new Cheese(
//                                new Anchovy(
//                                        new Crust()))));
//        System.out.println(p.removeAnchovy());
//        System.out.println(pi.topAnchovyWithCheese());
//    }
//}
//
//abstract class SeasoningD extends Foo{}
//
//class Salt extends SeasoningD {}
//class Pepper extends SeasoningD {}
//class Thyme extends SeasoningD {}
//class Sage extends SeasoningD {}
//
//abstract class PointD extends Foo{
//    int x;
//    int y;
//    PointD(int _x, int _y) {
//        x = _x;
//        y = _y;
//    }
//    abstract int distanceToO();
//    boolean closerToO(PointD p) {
//        return distanceToO() <= p.distanceToO();
//    }
//}
//class CartesianPt extends PointD {
//    CartesianPt(int _x, int _y) {
//        super(_x, _y);
//    }
//    // -----------------------------------
//    public int distanceToO() {
//        return (int) Math.sqrt(x*x + y*y);
//    }
//}
//class ManhattanPt extends PointD {
//    ManhattanPt(int _x, int _y) {
//        super(_x, _y);
//    }
//    // -----------------------------------
//    public int distanceToO() {
//        return x + y;
//    }
//}
//
//abstract class NumD extends Foo {}
//
//class Zero extends NumD {}
//class OneMoreThan extends NumD {
//    NumD predecessor;
//    OneMoreThan(NumD p) {
//        predecessor = p;
//    }
//    // -------------------------------------
//}
//
//abstract class LayerD extends Foo {}
//
//class Base extends LayerD {
//    Object o;
//    Base(Object _o) {
//        o = _o;
//    }
//    // ----------------------------------
//}
//class Slice extends LayerD {
//    LayerD l;
//    Slice(LayerD _l) {
//        l = _l;
//    }
//    // ---------------------------------
//}
//
//abstract class ShishD extends Foo {
//    abstract boolean onlyOnions();
//    abstract boolean isVegetarian();
//}
//
//class Skewer extends ShishD {
//    @Override
//    boolean onlyOnions() {
//        return true;
//    }
//    boolean isVegetarian() {
//        return true;
//    }
//}
//class Onion extends ShishD {
//    ShishD s;
//    Onion(ShishD _s) {
//        s = _s;
//    }
//    //----------------------------------
//    @Override
//    boolean onlyOnions() {
//        return s.onlyOnions();
//    }
//    boolean isVegetarian() {
//        return s.isVegetarian();
//    }
//}
//class Lamb extends ShishD {
//    ShishD s;
//    Lamb(ShishD _s) {
//        s = _s;
//    }
//    //----------------------------------
//    @Override
//    boolean onlyOnions() {
//        return false;
//    }
//    boolean isVegetarian() {
//        return false;
//    }
//}
//class Tomato extends ShishD {
//    ShishD s;
//    Tomato(ShishD _s) {
//        s = _s;
//    }
//    //----------------------------------
//    @Override
//    boolean onlyOnions() {
//        return false;
//    }
//    boolean isVegetarian() {
//        return s.isVegetarian();
//    }
//}
//
//abstract class KebabD extends Foo {
//    abstract boolean isVeggie();
//    abstract Object whatHolder();
//}
//
//class Holder extends KebabD {
//    Object o;
//    Holder(Object _o) {
//        o = _o;
//    }
//    //-------------------------------
//    @Override
//    boolean isVeggie() {
//        return true;
//    }
//    @Override
//    Object whatHolder() {
//        return o;
//    }
//}
//class Shallot extends KebabD {
//    KebabD k;
//    Shallot(KebabD _k) {
//        k = _k;
//    }
//    //-------------------------------
//    @Override
//    boolean isVeggie() {
//        return k.isVeggie();
//    }
//    @Override
//    Object whatHolder() {
//        return k.whatHolder();
//    }
//}
//class Shrimp extends KebabD {
//    KebabD k;
//    Shrimp(KebabD _k) {
//        k = _k;
//    }
//    //-------------------------------
//
//    @Override
//    boolean isVeggie() {
//        return false;
//    }
//    @Override
//    Object whatHolder() {
//        return k.whatHolder();
//    }
//}
//class Radish extends KebabD {
//    KebabD k;
//    Radish(KebabD _k) {
//        k = _k;
//    }
//    //-------------------------------
//
//    @Override
//    boolean isVeggie() {
//        return k.isVeggie();
//    }
//
//    @Override
//    Object whatHolder() {
//        return k.whatHolder();
//    }
//}
//class Zucchini extends KebabD {
//    KebabD k;
//    Zucchini(KebabD _k) {
//        k = _k;
//    }
//    //-------------------------------
//
//    @Override
//    boolean isVeggie() {
//        return k.isVeggie();
//    }
//
//    @Override
//    Object whatHolder() {
//        return k.whatHolder();
//    }
//}
//
//abstract class RodD extends Foo {}
//
//class Dagger extends RodD {}
//class Sabre extends RodD {}
//class Sword extends RodD {}
//
//abstract class PizzaD extends Foo {
//    abstract PizzaD removeAnchovy();
//    abstract PizzaD topAnchovyWithCheese();
//    abstract PizzaD substituteAnchovyByCheese();
//}
//
//class Crust extends PizzaD {
//    @Override
//    PizzaD removeAnchovy() {
//        return new Crust();
//    }
//
//    @Override
//    PizzaD topAnchovyWithCheese() {
//        return new Crust();
//    }
//
//    @Override
//    PizzaD substituteAnchovyByCheese() {
//        return new Crust();
//    }
//}
//class Cheese extends PizzaD {
//    PizzaD p;
//    Cheese(PizzaD _p) {
//        p = _p;
//    }
//    //----------------------------
//
//    @Override
//    PizzaD removeAnchovy() {
//        return new Cheese(p.removeAnchovy());
//    }
//
//    @Override
//    PizzaD topAnchovyWithCheese() {
//        return new Cheese(p.topAnchovyWithCheese());
//    }
//
//    @Override
//    PizzaD substituteAnchovyByCheese() {
//        return new Cheese(p.substituteAnchovyByCheese());
//    }
//}
//class Olive extends PizzaD {
//    PizzaD p;
//    Olive(PizzaD _p) {
//        p = _p;
//    }
//    //----------------------------
//
//    @Override
//    PizzaD removeAnchovy() {
//        return new Olive(p.removeAnchovy());
//    }
//
//    @Override
//    PizzaD topAnchovyWithCheese() {
//        return new Olive(p.topAnchovyWithCheese());
//    }
//
//    @Override
//    PizzaD substituteAnchovyByCheese() {
//        return new Olive(p.substituteAnchovyByCheese());
//    }
//}
//class Anchovy extends PizzaD {
//    PizzaD p;
//    Anchovy(PizzaD _p) {
//        p = _p;
//    }
//    //----------------------------
//
//    @Override
//    PizzaD removeAnchovy() {
//        return p.removeAnchovy();
//    }
//
//    @Override
//    PizzaD topAnchovyWithCheese() {
//        return new Cheese(new Anchovy(p.topAnchovyWithCheese()));
//    }
//
//    @Override
//    PizzaD substituteAnchovyByCheese() {
//        return new Cheese(p.substituteAnchovyByCheese());
//    }
//}
//class Sausage extends PizzaD {
//    PizzaD p;
//    Sausage(PizzaD _p) {
//        p = _p;
//    }
//    //----------------------------
//
//    @Override
//    PizzaD removeAnchovy() {
//        return new Sausage(p.removeAnchovy());
//    }
//
//    @Override
//    PizzaD topAnchovyWithCheese() {
//        return new Sausage(p.topAnchovyWithCheese());
//    }
//
//    @Override
//    PizzaD substituteAnchovyByCheese() {
//        return new Sausage(p.substituteAnchovyByCheese());
//    }
//}
//class Spinach extends PizzaD {
//    PizzaD p;
//    Spinach(PizzaD _p) {
//        p = _p;
//    }
//    //----------------------------
//
//    @Override
//    PizzaD removeAnchovy() {
//        return new Spinach(p.removeAnchovy());
//    }
//
//    @Override
//    PizzaD topAnchovyWithCheese() {
//        return new Spinach(p.topAnchovyWithCheese());
//    }
//
//    @Override
//    PizzaD substituteAnchovyByCheese() {
//        return new Spinach(p.substituteAnchovyByCheese());
//    }
//}