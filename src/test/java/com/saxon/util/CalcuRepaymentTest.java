package com.saxon.util;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;

import com.saxon.util.model.Detail;
import com.saxon.util.model.RepaymentScheduleData;

import static org.junit.Assert.*;

import java.util.List;

public class CalcuRepaymentTest {

	@Test
	public void testOf1() {

		CalcuRepayment instance = new CalcuRepayment();
		RepaymentScheduleData<List<Detail>> result = instance.of("saxon", 400_000, 0.13, 11_000);
		assertNotNull(result);
		assertEquals(result.data.size(), 47);
	}

	@Test
	public void testOf2() {

		CalcuRepayment instance = new CalcuRepayment();
		RepaymentScheduleData<List<Detail>> result = instance.of("jackluo", 10000, 0.12, 1000);
		assertNotNull(result);
		assertEquals(result.data.size(), 11);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOf3() {

		new CalcuRepayment().of("jackluo", 10000, 0.12, 100);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOf4() {

		new CalcuRepayment().of("jackluo", 10000, 0.12, 99);
	}
}