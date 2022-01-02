package com.challenge.aoc2021.day3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MostCommonBitDiagnosticReportFilterTest {
    @Test
    public void test1Pass() {
        var report = DiagnosticReportTestBuilder.of(
                "11001",
                "10011",
                "00100"
        );

        System.out.println(report);

        //var filter = DiagnosticReportFilterFactory.getInstance().createFilterForMostCommonBit(report);

        //Assert.assertEquals(filter.filter(), 2);
    }
}