import flanagan.interpolation.CubicSpline;
import flanagan.interpolation.TriCubicSpline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testFlanagan {
    TriCubicSpline triCubicSpline;

    @BeforeEach
    void setup() {
        double[] excelData = { 50.0, 100.0, 200.0, 500.0 };
        double[] pitchData = { 10.0, 20.0, 30.0 };
        double[] speedData = { 200.0, 220.0, 240.0, 260.0, 280.0, 300.0 };
        double[][][] distances = {
                {
                        { 5575.0, 5625.0, 5700.0, 5750.0, 5825.0, 5875.0 },
                        { 6750.0, 6800.0, 6875.0, 6950.0, 7025.0, 7100.0 },
                        { 7250.0, 7300.0, 7350.0, 7400.0, 7475.0, 7550.0 }
                },
                {
                        { 5675.0, 5725.0, 5800.0, 5850.0, 5925.0, 5975.0 },
                        { 6800.0, 6850.0, 6925.0, 7000.0, 7075.0, 7150.0 },
                        { 7300.0, 7350.0, 7400.0, 7450.0, 7525.0, 7600.0 }
                },
                {
                        { 5925.0, 5975.0, 6050.0, 6100.0, 6175.0, 6225.0 },
                        { 6950.0, 7000.0, 7075.0, 7150.0, 7225.0, 7300.0 },
                        { 7450.0, 7500.0, 7550.0, 7600.0, 7675.0, 7750.0 }
                },
                {
                        { 6425.0, 6475.0, 6550.0, 6600.0, 6675.0, 6725.0 },
                        { 7150.0, 7200.0, 7275.0, 7350.0, 7425.0, 7560.0 },
                        { 7550.0, 7600.0, 7650.0, 7700.0, 7775.0, 7850.0 }
                }
        };

        triCubicSpline = new TriCubicSpline(excelData, pitchData, speedData, distances);
    }

    @Test
    void testInterpolation() {
        double interpolateDistance = triCubicSpline.interpolate(80.0, 20.0, 250.0);

        System.out.println(interpolateDistance);
        assertEquals(interpolateDistance, 6942, 1);

        interpolateDistance = triCubicSpline.interpolate(40.0, 20.0, 250.0);
        System.out.println(interpolateDistance);
    }

    @Test
    void testExtrapolation() {
        double[] excelData = { 50.0, 100.0, 200.0, 500.0 };
        double[] distances = { 5700.0, 7075.0, 7225.0, 7425.0};
        CubicSpline cubicSpline = new CubicSpline(excelData, distances);

        for (int i = 10; i < 700; i += 10)
        {
            double interpolateDistance = cubicSpline.interpolate(i);
            System.out.println(interpolateDistance);
        }
    }
}
