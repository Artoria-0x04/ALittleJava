package io.Artoria.Chapter9;

import io.Artoria.MyToString;

abstract class PointD extends MyToString {
    int x;
    int y;

    public PointD(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract int distanceToO();

    boolean closerToO(PointD p) {
        return distanceToO() <= p.distanceToO();
    }

    public PointD minus(PointD q) {
        return new CartesianPt(x - q.x, y - q.y);
    }
}

class CartesianPt extends PointD {
    public CartesianPt(int x, int y) {
        super(x, y);
    }

    @Override
    int distanceToO() {
        return (int) Math.floor(Math.sqrt(x * x + y * y));
    }
}

class ManhattanPt extends PointD {
    public ManhattanPt(int x, int y) {
        super(x, y);
    }

    @Override
    int distanceToO() {
        return x + y;
    }
}

class ShadowedManhattanPt extends ManhattanPt {
    int dx;
    int dy;

    public ShadowedManhattanPt(int x, int y, int dx, int dy) {
        super(x, y);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    int distanceToO() {
        return super.distanceToO() + dx + dy;
    }
}

class ShadowedCartesianPt extends CartesianPt {
    int dx;
    int dy;

    public ShadowedCartesianPt(int x, int y, int dx, int dy) {
        super(x, y);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    int distanceToO() {
        //        return (int) (super.distanceToO() + Math.floor(Math.sqrt(dx * dx + dy * dy)));
        return (int) (new CartesianPt(x + dx, y + dy).distanceToO());
    }
}

abstract class ShapeD extends MyToString {
    abstract boolean accept(ShapeVisitorI ask);
}

class Circle extends ShapeD {
    int r;

    public Circle(int r) {
        this.r = r;
    }

    @Override
    boolean accept(ShapeVisitorI ask) {
        return ask.forCircle(r);
    }
}

class Square extends ShapeD {
    int s;

    public Square(int s) {
        this.s = s;
    }

    @Override
    boolean accept(ShapeVisitorI ask) {
        return ask.forSquare(s);
    }
}

class Translation extends ShapeD {
    PointD q;
    ShapeD s;

    public Translation(PointD q, ShapeD s) {
        this.q = q;
        this.s = s;
    }

    @Override
    boolean accept(ShapeVisitorI ask) {
        return ask.forTranslation(q, s);
    }
}

interface ShapeVisitorI {
    boolean forCircle(int r);

    boolean forSquare(int s);

    boolean forTranslation(PointD q, ShapeD s);
}

class HasPtV implements ShapeVisitorI {
    PointD pt;

    public HasPtV(PointD pt) {
        this.pt = pt;
    }

    ShapeVisitorI newHasPt(PointD p) {
        return new HasPtV(p);
    }

    @Override
    public boolean forCircle(int r) {
        return pt.distanceToO() <= r;
    }

    @Override
    public boolean forSquare(int s) {
        return pt.x <= s && pt.y <= s;
    }

    @Override
    public boolean forTranslation(PointD q, ShapeD s) {
        return s.accept(newHasPt(pt.minus(q)));
    }
}

class Union extends ShapeD {
    ShapeD s;
    ShapeD t;

    public Union(ShapeD s, ShapeD t) {
        this.s = s;
        this.t = t;
    }

    @Override
    boolean accept(ShapeVisitorI ask) {
        return ((UnionVisitorI) ask).forUnion(s, t);
    }
}

interface UnionVisitorI extends ShapeVisitorI {
    boolean forUnion(ShapeD s, ShapeD t);
}

class UnionHasPtV extends HasPtV implements UnionVisitorI {
    public UnionHasPtV(PointD pt) {
        super(pt);
    }

    @Override
    ShapeVisitorI newHasPt(PointD p) {
        return new UnionHasPtV(p);
    }

    @Override
    public boolean forUnion(ShapeD s, ShapeD t) {
        return s.accept(this) || t.accept(this);
    }
}

public class Main {
    public static void main(String[] args) {
        ShadowedCartesianPt p = new ShadowedCartesianPt(12, 5, 3, 4);

        boolean b = new Translation(new CartesianPt(5, 4),
                                    new Translation(new CartesianPt(5, 6),
                                                    new Circle(10)))
                .accept(new HasPtV(new CartesianPt(10, 10)));

        boolean b1 = new Translation(new CartesianPt(3, 7),
                                     new Union(new Square(10),
                                               new Circle(10))).accept(new UnionHasPtV(new CartesianPt(13, 17)));

        System.out.println(p + "\n" +
                           p.distanceToO() + "\n" +
                           b + "\n" +
                           b1 + "\n");
    }
}
