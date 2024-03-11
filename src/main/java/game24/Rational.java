package game24;

public class Rational {
    private int numerator;
    private int denominator;

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Rational add(Rational other){
        int newNumerator = other.getNumerator() * denominator + numerator * other.getDenominator();
        int newDenominator = other.getDenominator() * denominator;
        int ggt = getGGT(newNumerator, newDenominator);
        return new Rational(newNumerator/ggt, newDenominator/ggt);
    }
    public Rational sub(Rational other){
        int newNumerator = numerator * other.getDenominator() - other.getNumerator() * denominator;
        int newDenominator = other.getDenominator() * denominator;
        int ggt = getGGT(newNumerator, newDenominator);
        return new Rational(newNumerator/ggt, newDenominator/ggt);
    }

    public Rational mul(Rational other){
        int newNumerator = other.getNumerator()*numerator;
        int newDenominator = other.getDenominator()* denominator;
        int ggt = getGGT(newNumerator, newDenominator);
        return new Rational(newNumerator/ggt, newDenominator/ggt);
    }

    public Rational div(Rational other){
        if(other.equalsInt(0))
            throw new RuntimeException("Can't divide by this Rational because it's zero");
        return mul(other.getReciprocal());
    }

    public Rational getReciprocal(){
        if(numerator == 0)
            throw new RuntimeException("Can't get Reciprocal for this Rational because it's zero");
        return new Rational(denominator, numerator);
    }

    public boolean equalsInt(int value){
        return numerator % denominator == 0 && numerator / denominator == value;
    }

    private int getGGT(int a, int b){
        if(b == 0) return a;
        return getGGT(b, a % b);
    }

    public int toInt(){
        if(numerator % denominator == 0)
            return numerator / denominator;
        else
            throw new RuntimeException("This rational can't be converted to int");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Rational r){
            return r.getDenominator() == denominator && r.getNumerator() == numerator;
        }
        return false;
    }
}
