package e3;

import java.util.Arrays;

public record Triangle(float angle0, float angle1, float angle2) {
    public Triangle {
        if (angle0+angle1+angle2!=180) throw new IllegalArgumentException();
        // Throws an IllegalArgumentException if the angles don't add up to exactly 180.
    }

    public Triangle(Triangle tr) { // It builds a triangle using the data of another triangle.
        this(tr.angle0, tr.angle1, tr.angle2);
    }
    public boolean isRight() { // Returns the boolean result of any angle equalising 90.
        return (this.angle0 == 90 || this.angle1 == 90 || this.angle2 == 90);
    }

    public boolean isAcute() { // Returns the boolean result of every angle being lower than 90.
        return (this.angle0 < 90 && this.angle1 < 90 && this.angle2 < 90);
    }

    public boolean isObtuse() { // Returns the boolean result of any angle being larger than 90.
        return (this.angle0 > 90 || this.angle1 > 90 || this.angle2 > 90);
    }

    public boolean isEquilateral() { // Returns the boolean result of angle0 equalising angle1 and angle2.
        return (this.angle0 == this.angle1 && this.angle0 == this.angle2);
    }
    public boolean isIsosceles() {
        if (this.isEquilateral()) {return false;} // If the triangle is equilateral, it's false.
        else{ // Else, returns the boolean result of any angle equalising another.
        return (this.angle0 == this.angle1 || this.angle0 == this.angle2 || this.angle1 == this.angle2);}
    }

    public boolean isScalene() { // Returns the boolean result of every angle not equalising the other ones.
        return (this.angle0 != this.angle1 && this.angle0 != this.angle2 && this.angle1 != this.angle2);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) { // If the object received is empty, returns false.
            return false;
        }
        if (this == o) {
            return true; // If "this" and o are equal, returns true.
        }
        if (getClass()!=o.getClass()) { // If the classes are different, returns false.
            return false;
        }
        Triangle t = (Triangle) o; // Creates a triangle using o.
        float[] check = {t.angle0, t.angle1, t.angle2}; // Fills two array with the angles of both triangles.
        float[] check2 = {this.angle0, this.angle1, this.angle2};
        Arrays.sort(check); // Sorts both arrays, from lower to higher.
        Arrays.sort(check2);

        int b = 0; // It compares the sorted angles of both triangles. If the angles are equal, it adds 1 to b
        if(check [0] == check2[0]) b++;
        if(check [1] == check2[1]) b++;
        if(check [2] == check2[2]) b++;
        return (b == 3); // Returns if b is 3 or not.
    }
    @Override
    public int hashCode() {
        float[] pos = {this.angle0, this.angle1, this.angle2};
        Arrays.sort(pos); // It sorts the angles of the triangle, after being put in an array.
        return (int) (5.0*pos[0]+7.0*pos[1]+11.0*pos[2]); // Returns the smallest angle *5, the largest one * 11 and the last one * 7
    }
}


