package stackowerflow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RunWith(BlockJUnit4ClassRunner.class)
public class StackOverflow1 {

    @Test
    public void test_1() {
        BigDecimal gcApprovedAmount = new BigDecimal(11499);

        BigDecimal gcRequestedAmt = new BigDecimal(12500);

        gcApprovedAmount = gcRequestedAmt.subtract(gcApprovedAmount).round(new MathContext(3, RoundingMode.HALF_UP));

        System.out.println(gcApprovedAmount);
    }

    @Test
    public void test_2() {

        BigDecimal gcApprovedAmount = new BigDecimal(11499);

        BigDecimal gcRequestedAmt = new BigDecimal(12500);

        gcApprovedAmount = gcRequestedAmt.subtract(gcApprovedAmount).setScale(3, BigDecimal.ROUND_HALF_UP);

        System.out.println(gcApprovedAmount);
    }
}
