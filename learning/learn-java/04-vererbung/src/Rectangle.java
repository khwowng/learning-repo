public class Rectangle extends Figure {
    private double a, b;
    public double getA() { return this.a; }
    public double getB() { return this.b; }
    public void setA(double a) { this.a = (a>0)? a:0; }
    public void setB(double b) { this.b = (b>0)? b:0; }

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getCircumfence() { return 2*this.a + 2*this.b; }

    @Override
    public double getArea() { return this.a*this.b; }

    @Override
    public double getHeight() {
        if (this.a>this.b) return this.a;
        else return this.b;
    }

    @Override
    public double getWidth() {
        if (this.a<this.b) return this.a;
        else return this.b;
    }

    @Override
    public String toString()
    { return String.format ("Dieses Rechteck beinhaltet folgende Werte:\n" + "Hoehe: %.2f\n Breite: %.2f\n Umfang: %.2f\n Flaecheninhalt: %.2f",
        this.getHeight(), this.getWidth(), this.getCircumfence(), this.getArea());
    }

}
