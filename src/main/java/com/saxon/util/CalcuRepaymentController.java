package com.saxon.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saxon.util.model.Detail;
import com.saxon.util.model.FormData;
import com.saxon.util.model.RepaymentScheduleData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/calcuRepayment")
public class CalcuRepaymentController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
	public CalcuRepaymentController() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public String showList(@Valid FormData formData, Model model) {
		logger.info("do Get()");
		
		RepaymentScheduleData<List<Detail>> repaymentScheduleData = null;
		if (StringUtils.isNoneEmpty(formData.getDate(), formData.getTotalAmount(), formData.getInterestRate(), formData.getRepaymentMonthly())) {
			repaymentScheduleData = new CalcuRepayment().of(formData.getName(), formData.getDate(),Double.valueOf(formData.getTotalAmount()), Double.valueOf(formData.getInterestRate())/100,
					Double.valueOf(formData.getRepaymentMonthly()));
		} 
		model.addAttribute("repaymentScheduleData", repaymentScheduleData);
		return "calcuRepayment";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String queryList(@Valid FormData formData, BindingResult bindingResult, Model model,HttpSession session) throws UnsupportedEncodingException {
		logger.info("do POST()");
		logger.debug("formData="+formData.toString());
		
		if (bindingResult.hasErrors()) {
	    	 	System.out.println("has errors");
	    	 	System.out.println(bindingResult);
	            return "calcuRepayment";
	        }
		
		RepaymentScheduleData<List<Detail>> repaymentScheduleData = new CalcuRepayment().of(
				formData.getName(), formData.getDate(),Double.valueOf(formData.getTotalAmount()), Double.valueOf(formData.getInterestRate())/100,
					Double.valueOf(formData.getRepaymentMonthly()));
			model.addAttribute("repaymentScheduleData", repaymentScheduleData);
		
		session.setAttribute("result",repaymentScheduleData);
	     return "calcuRepayment";
	}
	
}
