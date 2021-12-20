public class Rhombus extends Figure {
    private double a, d1, d2; // Seitenlänge, Diagonal
    public Rhombus (double a, double d1, double d2) {
        this.a = a;
        this.d1 = d1;
        this.d2 = d2;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = (a>=0)? a:0;
    }

    public double getD1() {
        return d1;
    }

    public void setD1(double d1) {
        this.d1 = (d1>=0)? d1:0;
    }

    public double getD2() {
        return d2;
    }

    public void setD2(double d2) {
        this.d2 = (d2>=0)? d2:0;
    }

    @Override
    public double getCircumfence() {
        return 4*this.a;
    }

    @Override
    public double getArea() {
        return this.d1*this.d2;
    }

    @Override
    public double getHeight() {
        if (this.d1>this.d2) return this.d1;
        else return this.d2;
    }

    @Override
    public double getWidth() {
        if (this.d1<this.d2) return this.d1;
        else return this.d2;
    }

    @Override
    public String toString()
    {
        return String.format("Dieses Rhombus beinhaltet folgende Werte: \n"
                + "Seitenlänge: %.2f \nHöhe: %.2f \nBreite: %.2f \nUmfang: %.2f \nFlächeninhalt: %.2f",
                this.getA(), this.getHeight(), this.getWidth(), this.getCircumfence(), this.getArea());
    }
}
