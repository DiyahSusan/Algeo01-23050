package Function;

public class BicubicTest {
    public static void main(String[] args) {
        // Define the test 4x4 matrix as input
        double[][] f = {
            {21, 98, 125, 153},
            {51, 101, 161, 59},
            {0, 42, 72, 210},
            {16, 12,81, 96},
            {0.5,0.5}
        };

        // Define the points to interpolate
        double x1 = 0.5; // x position to interpolate
        double y1 = 0.5; // y position to interpolate

        // Call the interpolation function
        double result = Bicubic.BicubicSplineInterpolation(f);

        // Output the result
        System.out.println("Interpolated value at (" + x1 + ", " + y1 + ") is: " + result);
    }
}
