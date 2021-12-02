public class Rectangle implements Figure {
    private double a;
    private double b;
    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getCircumfence() {
        return 2*this.a + 2*this.b;
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
    public String toString()
    {
        return String.format("Dieses Rechteck beinhaltet folgende Werte: \n"
                + "Höhe: %.2f \nBreite: %.2f\nUmfang: %.2f\nFlächeninhalt: %.2f",
                this.getHeight(), this.getWidth(), this.getCircumfence(), this.getArea());
    }
}
