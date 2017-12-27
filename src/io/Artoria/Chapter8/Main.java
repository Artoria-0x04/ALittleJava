package io.Artoria.Chapter8;

import io.Artoria.MyToString;

abstract class Expr extends MyToString {
    abstract Object accept(ExprVisitorI ask);
}

class Const extends Expr {
    Object c;

    public Const(Object c) {
        this.c = c;
    }

    @Override
    Object accept(ExprVisitorI ask) {
        return ask.forConst(c);
    }
}

class Plus extends Expr {
    Expr l;
    Expr r;

    public Plus(Expr l, Expr r) {
        this.l = l;
        this.r = r;
    }

    @Override
    Object accept(ExprVisitorI ask) {
        return ask.forPlus(l, r);
    }
}

class Diff extends Expr {
    Expr l;
    Expr r;

    public Diff(Expr l, Expr r) {
        this.l = l;
        this.r = r;
    }

    @Override
    Object accept(ExprVisitorI ask) {
        return ask.forDiff(l, r);
    }
}

class Prod extends Expr {
    Expr l;
    Expr r;

    public Prod(Expr l, Expr r) {
        this.l = l;
        this.r = r;
    }

    @Override
    Object accept(ExprVisitorI ask) {
        return ask.forProd(l, r);
    }
}

abstract class SetD extends MyToString {
    SetD add(Integer i) {
        if (mem(i)) {
            return this;
        } else {
            return new Add(i, this);
        }
    }

    abstract boolean mem(Integer i);

    abstract SetD plus(SetD s);

    abstract SetD diff(SetD s);

    abstract SetD prod(SetD s);
}

class Empty extends SetD {
    @Override
    boolean mem(Integer i) {
        return false;
    }

    @Override
    SetD plus(SetD s) {
        return s;
    }

    @Override
    SetD diff(SetD s) {
        return new Empty();
    }

    @Override
    SetD prod(SetD s) {
        return new Empty();
    }
}

class Add extends SetD {
    Integer i;
    SetD set;

    public Add(Integer i, SetD set) {
        this.i = i;
        this.set = set;
    }

    @Override
    boolean mem(Integer n) {
        if (this.i.equals(n)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    SetD plus(SetD s) {
        return new Add(i, s);
    }

    @Override
    SetD diff(SetD s) {
        if (s.mem(i)) { // has same int, go deeper
            return set.diff(s);
        } else { // different, go deeper with i
            return set.diff(s).add(i);
        }
    }

    @Override
    SetD prod(SetD s) {
        if (s.mem(i)) {
            return set.prod(s).add(i);
        } else {
            return set.prod(s);
        }
    }
}

interface ExprVisitorI {
    Object forConst(Object c);

    Object forPlus(Expr l, Expr r);

    Object forDiff(Expr l, Expr r);

    Object forProd(Expr l, Expr r);
}

class IntEvalVold implements ExprVisitorI {
    @Override
    public Object forConst(Object c) {
        return c;
    }

    @Override
    public Object forPlus(Expr l, Expr r) {
        return plus(l.accept(this), r.accept(this));
    }

    @Override
    public Object forDiff(Expr l, Expr r) {
        return diff(l.accept(this), r.accept(this));
    }

    @Override
    public Object forProd(Expr l, Expr r) {
        return prod(l.accept(this), r.accept(this));
    }

    Object plus(Object l, Object r) {
        return new Integer(((Integer) l).intValue() + ((Integer) r).intValue());
    }

    Object diff(Object l, Object r) {
        return new Integer(((Integer) l).intValue() - ((Integer) r).intValue());
    }

    Object prod(Object l, Object r) {
        return new Integer(((Integer) l).intValue() * ((Integer) r).intValue());
    }
}

class SetEvalVold extends IntEvalVold {
    Object plus(Object l, Object r) {
        return ((SetD) l).plus((SetD) r);
    }

    Object diff(Object l, Object r) {
        return ((SetD) l).diff((SetD) r);
    }

    Object prod(Object l, Object r) {
        return ((SetD) l).prod((SetD) r);
    }
}

abstract class EvalD extends MyToString implements ExprVisitorI {
    @Override
    public Object forConst(Object c) {
        return c;
    }

    @Override
    public Object forPlus(Expr l, Expr r) {
        return plus(l.accept(this), r.accept(this));
    }

    @Override
    public Object forDiff(Expr l, Expr r) {
        return diff(l.accept(this), r.accept(this));
    }

    @Override
    public Object forProd(Expr l, Expr r) {
        return prod(l.accept(this), r.accept(this));
    }

    abstract Object plus(Object l, Object r);

    abstract Object diff(Object l, Object r);

    abstract Object prod(Object l, Object r);
}

class IntEvalV extends EvalD {
    Object plus(Object l, Object r) {
        return new Integer(((Integer) l).intValue() + ((Integer) r).intValue());
    }

    Object diff(Object l, Object r) {
        return new Integer(((Integer) l).intValue() - ((Integer) r).intValue());
    }

    Object prod(Object l, Object r) {
        return new Integer(((Integer) l).intValue() * ((Integer) r).intValue());
    }
}

class SetEvalV extends EvalD {
    Object plus(Object l, Object r) {
        return ((SetD) l).plus((SetD) r);
    }

    Object diff(Object l, Object r) {
        return ((SetD) l).diff((SetD) r);
    }

    Object prod(Object l, Object r) {
        return ((SetD) l).prod((SetD) r);
    }
}

public class Main {
    public static void main(String[] args) {
        Expr e1 = new Prod(new Plus(new Const(3),
                                    new Diff(new Const(5), new Const(6))),
                           new Const(7));
        Expr e2 = new Prod(new Plus(new Const(new Empty().add(3)),
                                    new Diff(new Const(new Empty().add(5).add(6)), new Const(new Empty().add(6).add(7)))),
                           new Const(new Empty().add(3)));
        System.out.println("\n" +
                           e1 + "\n" +
                           e1.accept(new IntEvalVold()) + "\n" +
                           e2 + "\n" +
                           e2.accept(new SetEvalVold()) + "\n" +
                           ""
        );
    }
}


abstract class PieD extends MyToString {
    abstract PieD accept(PieVisitorI ask);
}

class Bottom extends PieD {
    @Override
    PieD accept(PieVisitorI ask) {
        return ask.forBottom();
    }
}

class Topping extends PieD {
    Object top;
    PieD rest;

    Topping(Object _t, PieD _r) {
        top = _t;
        rest = _r;
    }

    //---------------------------------
    @Override
    PieD accept(PieVisitorI ask) {
        return ask.forTopping(top, rest);
    }
}

interface PieVisitorI {
    PieD forTopping(Object top, PieD rest);

    PieD forBottom();
}

abstract class SubstD implements PieVisitorI {
    Object neue;
    Object old;

    public SubstD(Object neue, Object old) {
        this.neue = neue;
        this.old = old;
    }

    abstract public PieD forTopping(Object top, PieD rest);

    @Override
    public PieD forBottom() {
        return new Bottom();
    }
}

class SubstV extends SubstD {
    public SubstV(Object neue, Object old) {
        super(neue, old);
    }

    @Override
    public PieD forTopping(Object top, PieD rest) {
        if (old.equals(top)) {
            return new Topping(neue, rest.accept(this));
        } else {
            return new Topping(top, rest.accept(this));
        }
    }
}

class LimitedSubstV extends SubstD {
    private int count;

    public LimitedSubstV(int count, Object neue, Object old) {
        super(neue, old);
        this.count = count;
    }

    @Override
    public PieD forTopping(Object top, PieD rest) {
        if (count == 0) {
            return new Topping(top, rest);
        } else {
            if (top.equals(old)) {
                return new Topping(neue, rest.accept(new LimitedSubstV(count - 1, neue, old)));
            } else {
                return new Topping(top, rest.accept(this));
            }
        }
    }
}

class SubstVOld implements PieVisitorI {
    Object neue;
    Object old;

    public SubstVOld(Object neue, Object old) {
        this.neue = neue;
        this.old = old;
    }

    @Override
    public PieD forTopping(Object top, PieD rest) {
        if (old.equals(top)) {
            return new Topping(neue, rest.accept(this));
        } else {
            return new Topping(top, rest.accept(this));
        }
    }

    @Override
    public PieD forBottom() {
        return new Bottom();
    }
}

class LimitedSubstVext extends SubstVOld {
    int c;

    public LimitedSubstVext(int c, Object neue, Object old) {
        super(neue, old);
        this.c = c;
    }

    @Override
    public PieD forTopping(Object top, PieD rest) {
        if (c == 0) {
            return new Topping(top, rest);
        } else {
            if (top.equals(old)) {
                return new Topping(neue, rest.accept(new LimitedSubstV(c - 1, neue, old)));
            } else {
                return new Topping(top, rest.accept(this));
            }
        }
    }

    @Override
    public PieD forBottom() {
        return super.forBottom();
    }
}
