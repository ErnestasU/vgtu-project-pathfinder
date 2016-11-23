package bl.test.calories;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import bl.test.BaseTest;
import math.CaloriesCalculator;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CaloriesManagerTest extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(CaloriesManagerTest.class);

    @Test
    public void testShortestPathCalories() {
        CaloriesCalculator calc = new CaloriesCalculator(fullPathEdges);
        int calories = calc.calculate();
        Assert.assertNotEquals(0, calories);
        LOGGER.info(String.format("[testShortestPathCalories] Shortest path took \"%d\" calories!", calories));
    }
}
