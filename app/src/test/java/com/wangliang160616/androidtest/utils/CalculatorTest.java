package com.wangliang160616.androidtest.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangliang on 2016/8/15.
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        /*在这里进行数据初始化等操作*/
        mCalculator = new Calculator();
    }

    @Test
    public void testSum() throws Exception {
        /*param1:expected value
        * param2:actual value
        * param3:max delta (期望值和实际值的差)*/

        assertEquals(6d , mCalculator.sum(3d,3d) , 0d);
    }

    @Test
    public void testSubstract() throws Exception {
        assertEquals(3.5d , mCalculator.substract(21d,17.5d) , 0.d);
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(5d , mCalculator.divide(20d, 4d) , 0d);
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(6d , mCalculator.multiply(2d,3d) , 0d);
    }
}