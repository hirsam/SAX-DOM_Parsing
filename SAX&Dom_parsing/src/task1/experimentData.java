package task1;

public class experimentData {
    private double x;
    private double y;
    private String observingDate;

    public experimentData(double x, double y, String observingDate) {
        this.x = x;
        this.y = y;
        this.observingDate = observingDate;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getObservingDate() {
        return observingDate;
    }

    public void setObservingDate(String observingDate) {
        this.observingDate = observingDate;
    }
}
