package com.saxon.util;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saxon.util.model.Detail;
import com.saxon.util.model.RepaymentScheduleData;

@RestController
public class HelloController {

	@RequestMapping("/getDemo")
	public RepaymentScheduleData<List<Detail>> getDemo() {

		return new CalcuRepayment().of("saxon", 400_000, 0.13, 11_000);
	}
}
