package com.socialprotection.vnpay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class VnpayController {

	@RequestMapping(value = "/vnpay/ipn", method = RequestMethod.GET)
	public void getVnPayResult(HttpServletRequest request) {
		System.out.println("nvnpay: " + request.toString());
//		return "home";
	}

	@RequestMapping(value = "/vnp", method = RequestMethod.GET)
	public String returnPayView(HttpServletRequest request) {
		
		return "vnpay";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public void doPay(HttpServletRequest req, HttpServletResponse response) throws UnsupportedEncodingException {
		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String orderType = "other";
		String vnp_TxnRef = VnpayConfig.getRandomNumber(8);
		String vnp_IpAddr = "127.0.0.1";
		String vnp_TmnCode = VnpayConfig.vnp_TmnCode;

		int amount = Integer.parseInt(req.getParameter("amount")) * 100;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");

		String bankCode = req.getParameter("bankCode");
		if (bankCode != null && !bankCode.isEmpty()) {
			vnp_Params.put("vnp_BankCode", bankCode);
		}
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_OrderType", orderType);

		String locate = req.getParameter("language");
		if (locate != null && !locate.isEmpty()) {
			vnp_Params.put("vnp_Locale", locate);
		} else {
			vnp_Params.put("vnp_Locale", "vn");
		}
		vnp_Params.put("vnp_ReturnUrl", VnpayConfig.vnp_Returnurl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
//		vnp_Params.put("vnp_IpnURL", VnpayConfig.vnp_Ipnurl);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();

		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = VnpayConfig.hmacSHA512(VnpayConfig.vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = VnpayConfig.vnp_PayUrl + "?" + queryUrl;

		System.out.println(queryUrl);
		response.setHeader("Location", paymentUrl);
		response.setStatus(302);

//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        response.setHeader("Location", paymentUrl);
//        response.setStatus(302);

//        Gson
//        Gson gson = new Gson();
////        resp.getWriter().write(gson.toJson(job));
//        System.out.println(gson.toJson(job));
//		return "vnpay";
	}
}
