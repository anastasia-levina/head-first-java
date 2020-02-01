package ru.anastasia.test;

public class Triangle {
    double area;
    int height;
    int lenght;

    public static void main(String[] args) {
        Triangle[]ta = new Triangle[4];
        int x = 0; //int x = 1;
        while (x < 4) { //x < 5 {
            ta[x] = new Triangle();
            ta[x].height = (x + 1) * 2;
            ta[x].lenght = x + 4;
            ta[x].setArea();
            System.out.print("треугольник "+x+", зона");
            System.out.println(" = " + ta[x].area);
            x = x + 1;
        }
       int y = x;
        x = 27;
        Triangle t5 = ta[2];
        ta[2].area = 343;
        System.out.print("y = " + y);
        System.out.println(", зона t5 = "+ t5.area);
    }
    void setArea() {
       area = (height * lenght) / 2;
    }
}
