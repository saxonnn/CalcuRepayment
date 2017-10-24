package com.saxon.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saxon.util.model.Detail;
import com.saxon.util.model.RepaymentScheduleData;

@RestController
public class JsonController {

	@RequestMapping("/download")
	public RepaymentScheduleData<List<Detail>> repayment(HttpSession session) {

		return (RepaymentScheduleData<List<Detail>>)session.getAttribute("result");
	}
}
