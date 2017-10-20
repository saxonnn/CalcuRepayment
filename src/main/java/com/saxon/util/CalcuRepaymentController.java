package com.saxon.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saxon.util.model.Detail;
import com.saxon.util.model.FormData;
import com.saxon.util.model.RepaymentScheduleData;

@Controller
@RequestMapping("/calcuRepayment")
public class CalcuRepaymentController {

	@Autowired
	public CalcuRepaymentController() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public String showList(String name, String totalAmount, String interest, String repaymentMonthly, Model model) {

		RepaymentScheduleData<List<Detail>> repaymentScheduleData = null;
		if (StringUtils.isNoneEmpty(name, totalAmount, interest, repaymentMonthly)) {
			repaymentScheduleData = new CalcuRepayment().of(name, Double.valueOf(totalAmount), Double.valueOf(interest),
					Double.valueOf(repaymentMonthly));
			model.addAttribute("repaymentScheduleData", repaymentScheduleData);
		} 
		return "calcuRepayment";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String queryList(@ModelAttribute("formData") FormData formData,BindingResult bindingResult, Model model) {
		System.out.println(formData.toString());

	     if (bindingResult.hasErrors()) {
	    	 	System.out.println("has errors");
	            return "calcuRepayment";
	        }
	     
		String str = String.format("redirect:/calcuRepayment?name=%s&totalAmount=%s&interest=%s&repaymentMonthly=%s",
				formData.getName(), formData.getTotalAmount(), formData.getInterestRate(),
				formData.getRepaymentMonthly());
		System.out.println(str);
		return str;
	}

}
