public class Figure {
    public double getCircumfence() {return 0;}
    public double getArea() {return 0;}
    public double getHeight() {return 0;}
    public double getWidth() {return 0;}
    public String toString() {return "no shape at all";}

    public static void main(String[] args) throws Exception{
        Square shape1 = new Square(2.0);
        Rectangle shape2 = new Rectangle (2.0, 3.0);
        Circle shape3 = new Circle(3.0);
        RightTriangle shape4 = new RightTriangle(3.0, 4.0);
        Rhombus shape5 = new Rhombus(5,6,8);
        System.out.println("----------");
        Figure figs[] = {shape1, shape2, shape3, shape4, shape5};
        for(Figure x: figs) {
            System.out.println(x);
            System.out.println();
        }
    }
}
