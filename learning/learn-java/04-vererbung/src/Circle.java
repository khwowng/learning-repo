public class Circle extends Figure{
    private double r;
    public Circle(double r) {
        this.r = r;
    }
    @Override
    public double getCircumfence() {
        return 3.14*2*this.r;
    }
    @Override
    public double getArea() {
        return 3.14*this.r*this.r;
    }
    @Override
    public double getHeight() {
        return 2*this.r;
    }
    @Override
    public double getWidth() {
        return 2*this.r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = (r>0)? r:0;
    }

    @Override
    public String toString()
    {
        return String.format("Dieses Kreis beinhaltet folgende Werte:"
                + "\nRadius: %.2f \nDurchmesser: %.2f \nUmfang: %.2f\nFlächeninhalt: %.2f",
                this.getR(), this.getHeight(), this.getCircumfence(), this.getArea());
    }
}
