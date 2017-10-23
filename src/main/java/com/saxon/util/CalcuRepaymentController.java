package com.saxon.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.validation.Valid;

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
	public String showList(String name, String date,String totalAmount, String interest, String repaymentMonthly, Model model) {

		RepaymentScheduleData<List<Detail>> repaymentScheduleData = null;
		if (StringUtils.isNoneEmpty(name, totalAmount, interest, repaymentMonthly)) {
			repaymentScheduleData = new CalcuRepayment().of(name, date,Double.valueOf(totalAmount), Double.valueOf(interest)/100,
					Double.valueOf(repaymentMonthly));
			model.addAttribute("repaymentScheduleData", repaymentScheduleData);
		} 
		return "calcuRepayment";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String queryList(@Valid FormData formData, BindingResult bindingResult, Model model) throws UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
	    	 	System.out.println("has errors");
	    	 	System.out.println(bindingResult);
	            return "calcuRepayment";
	        }
//	    String name=URLEncoder.encode(formData.getName(),"UTF-8"); 
//		String str = String.format("redirect:/calcuRepayment?name=%s&date=%s&totalAmount=%s&interest=%s&repaymentMonthly=%s",
//				name,formData.getDate(),formData.getTotalAmount(), formData.getInterestRate(),
//				formData.getRepaymentMonthly());
		
		RepaymentScheduleData<List<Detail>> repaymentScheduleData = new CalcuRepayment().of(
				formData.getName(), formData.getDate(),Double.valueOf(formData.getTotalAmount()), Double.valueOf(formData.getInterestRate())/100,
					Double.valueOf(formData.getRepaymentMonthly()));
			model.addAttribute("repaymentScheduleData", repaymentScheduleData);
	     return "calcuRepayment";
	}

}
