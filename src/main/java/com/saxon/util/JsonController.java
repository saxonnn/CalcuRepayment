package com.saxon.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saxon.util.model.Detail;
import com.saxon.util.model.RepaymentScheduleData;

@RestController
public class JsonController {

	@RequestMapping("/repayment")
	public RepaymentScheduleData<List<Detail>> repayment(String name, String date,String totalAmount, String interest,
			String repaymentMonthly, Model model) {

		RepaymentScheduleData<List<Detail>> repaymentScheduleData = null;
		if (StringUtils.isNoneEmpty(name, totalAmount, interest, repaymentMonthly)) {
			repaymentScheduleData = new CalcuRepayment().of(name,date, Double.valueOf(totalAmount), Double.valueOf(interest),
					Double.valueOf(repaymentMonthly));
		}else {
			repaymentScheduleData = new RepaymentScheduleData<List<Detail>>();
//			repaymentScheduleData.name="";
//			repaymentScheduleData.data=new ArrayList<Detail>();			
		}
		
		return repaymentScheduleData;
	}
}
