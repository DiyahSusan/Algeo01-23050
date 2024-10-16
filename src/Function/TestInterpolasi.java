package Function;

import java.util.Arrays;

public class TestInterpolasi {
    public static void main(String[] args) {
        testFindFunction();
        testFindY();
    }

    private static void testFindFunction() {
        // Sample data points for interpolation
        double[][] titik = {
            {1, 2},   // Point (1, 2)
            {2, 3},   // Point (2, 3)
            {3, 5}    // Point (3, 5)
        };

        int banyakTitik = titik.length;

        // Expected output from the interpolation (this should be computed or known)
        String[] expectedFunction = {"1.0", "1.0", "0.0"}; // Example coefficients for the polynomial

        // Invoke the method
        String[] actualFunction = Interpolasi.findFunction(titik, banyakTitik);

        // Check if the actual output matches the expected output
        if (Arrays.equals(expectedFunction, actualFunction)) {
            System.out.println("testFindFunction: Passed");
        } else {
            System.out.println("testFindFunction: Failed");
            System.out.println("Expected: " + Arrays.toString(expectedFunction));
            System.out.println("Actual: " + Arrays.toString(actualFunction));
        }
    }

    private static void testFindY() {
        // Sample coefficients from the interpolated function
        String[] function = {"1.0", "1.0", "0.0"}; // Example polynomial coefficients
        double x = 2; // Point to evaluate

        // Expected y value for x = 2
        double expectedY = 3.0;

        // Invoke the method
        double actualY = Interpolasi.findY(function, x);

        // Check if the actual output matches the expected output
        if (Math.abs(expectedY - actualY) < 1e-6) { // Allow a small margin of error
            System.out.println("testFindY: Passed");
        } else {
            System.out.println("testFindY: Failed");
            System.out.println("Expected: " + expectedY);
            System.out.println("Actual: " + actualY);
        }
    }
}
