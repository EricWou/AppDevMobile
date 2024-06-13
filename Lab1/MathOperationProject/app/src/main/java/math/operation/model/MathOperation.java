package math.operation.model;

public class MathOperation {

    private double x_var;
    private double y_var;

    public MathOperation()
    {
        x_var = 0.0;
        y_var = 0.0;
    }

    public MathOperation(double x, double y)
    {
        x_var = x;
        y_var = y;
    }

    public double getX_var() {
        return x_var;
    }

    public void setX_var(double x_var) {
        this.x_var = x_var;
    }

    public double getY_var() {
        return y_var;
    }

    public void setY_var(double y_var) {
        this.y_var = y_var;
    }

    public double calcSum()
    {
        return x_var + y_var;
    }

    public double calcDifference()
    {
        return x_var - y_var;
    }

    public double calcProduct()
    {
        return x_var * y_var;
    }

    public double calcDivision()
    {
        return x_var / y_var;
    }

    @Override
    public String toString() {
        return "MathOperation{" +
                "x_var=" + x_var +
                ", y_var=" + y_var +
                '}';
    }
}
