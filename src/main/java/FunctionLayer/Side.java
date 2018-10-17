package FunctionLayer;

public class Side {

    private int brick2x4;
    private int brick2x2;
    private int brick2x1;

    public int getBrick2x4() {
        return brick2x4;
    }

    public void setBrick2x4(int brick2x4) {
        this.brick2x4 = brick2x4;
    }

    public int getBrick2x2() {
        return brick2x2;
    }

    public void setBrick2x2(int brick2x2) {
        this.brick2x2 = brick2x2;
    }

    public int getBrick2x1() {
        return brick2x1;
    }

    public void setBrick2x1(int brick2x1) {
        this.brick2x1 = brick2x1;
    }

    public void calcSide(int count) {
        brick2x4 = calcBrick2x4(count);
        count = count - (brick2x4 * 4);

        if (count > 0) {
            calcBrickOther(count);
        }
    }

    public int calcBrick2x4(int count) {
        if (count < 4) {
            return 0;
        }
        if ((count % 4) == 0) {
            return count / 4;
        }
        return (count - (count % 4)) / 4;
    }

    public void calcBrickOther(int count) {
        if (count == 1) {
            brick2x1 = 1;
        }

        if (count == 2) {
            brick2x2 = 1;
        }

        if (count > 2) {
            brick2x1 = 1;
            brick2x2 = 1;
        }
    }
}
