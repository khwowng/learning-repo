import java.lang.Math;

public class RightTriangle implements Figure {
    private double a, b;
    public RightTriangle (double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = (a>0)? a:0;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = (b>0)? b:0;
    }

    @Override
    public double getCircumfence() {
        return this.a+this.b+Math.sqrt(this.a*this.a+this.b*this.b);
    }

    @Override
    public double getArea() {
        return this.a*this.b;
    }

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
    {
        return String.format("Dieses recktwinkliges Dreieck beinhaltet folgende Werte:\n"
                + "Höhe: %.2f \nBreite: %.2f\nUmfang: %.2f\nFlächeninhalt: %.2f",
                this.getHeight(), this.getWidth(), this.getCircumfence(), this.getArea());
    }

}
