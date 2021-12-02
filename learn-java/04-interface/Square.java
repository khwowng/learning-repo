public class Square implements Figure{
    private double a;
    public Square (double a) {
        this.a = a;
    }
    @Override
    public double getCircumfence() {
        return this.a*4;
    }
    @Override
    public double getArea() {
        return this.a*this.a;
    }
    @Override
    public double getHeight() {
        return this.a;
    }
    @Override
    public double getWidth() {
        return this.a;
    }

    public double getA(){
        return a;
    }
    public void setA(double a) {
        this.a = (a>0)? a:0;
    }

    @Override
    public String toString()
    {
        return String.format("Dieses Quadrat beinhaltet folgende Werte:\n"
                + "Seitenlänge: %.2f \nUmfang: %.2f \nFlächeninhalt: %.2f",
                this.getA(), this.getCircumfence(), this.getArea());
    }
}
