package Function;

public class BicubicTest {
    public static void main(String[] args) {
        // Define the test 4x4 matrix as input
        double[][] f = {
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        };

        // Define the points to interpolate
        double x1 = 0.5; // x position to interpolate
        double y1 = 0.5; // y position to interpolate

        // Call the interpolation function
        double result = Bicubic.BicubicSplineInterpolation(f, x1, y1);

        // Output the result
        System.out.println("Interpolated value at (" + x1 + ", " + y1 + ") is: " + result);
    }
}
