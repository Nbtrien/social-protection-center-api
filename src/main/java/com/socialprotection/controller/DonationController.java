package com.socialprotection.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.socialprotection.entity.Donation;
import com.socialprotection.entity.Donor;
import com.socialprotection.service.DonationService;
import com.socialprotection.vnpay.DonationRequest;
import com.socialprotection.vnpay.DonationReturnRequest;
import com.socialprotection.vnpay.VnpayService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DonationController {
	@Autowired
	private DonationService donationService;

	@Autowired
	private VnpayService vnpayService;

	@GetMapping(value = "/donations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Donation>> getAllDonations() throws ParseException {
		List<Donation> donations = donationService.fndAll();
		return ResponseEntity.ok(donations);
	}

	@GetMapping(value = "/donations/pagination", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Donation>> getAllDonationsPagin(
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "sortDirec", required = false) String sortDirec) throws ParseException {
		return ResponseEntity.ok(donationService.getDonationsPagin(page, limit, sortBy, sortDirec));
	}

	@GetMapping(value = "name/{name}/donations/pagination", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Donation>> getDonationsByDonorName(@PathVariable("name") String name,
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "sortDirec", required = false) String sortDirec) throws ParseException {
		return ResponseEntity.ok(donationService.getDonationsByDonorName(name, page, limit, sortBy, sortDirec));
	}

	@GetMapping(value = "/donations/amount", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Float> getAllDonationAmount() throws ParseException {
		return ResponseEntity.ok(donationService.getTotalAmount());
	}

	@GetMapping(value = "year/{year}/donations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDonationsByYear(@PathVariable("year") Integer year) {
		return ResponseEntity.ok(donationService.getDonationsByYear(year));

	}

	@GetMapping(value = "/donations/years/amounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getYearsDonationAmounts() {
		return ResponseEntity.ok(donationService.getYearsDonationAmounts());

	}

	@GetMapping(value = "year/{year}/donations/months/amounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMonthDonationAmountsByYear(@PathVariable("year") Integer year) {
		return ResponseEntity.ok(donationService.getMonthDonationAmountsByYear(year));
	}

	@GetMapping(value = "year/{year}/month/{month}/donations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDonationAountByMonthInYear(@PathVariable("year") Integer year,
			@PathVariable("month") Integer month) {
		return ResponseEntity.ok(new BigDecimal(donationService.getDonationAmountByMonthInYear(year, month)));
	}

	@GetMapping(value = "year/{year}/month/{month}/donations/pagination", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDonationByMonthInYear(@PathVariable("year") Integer year,
			@PathVariable("month") Integer month, @RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "sortDirec", required = false) String sortDirec) throws ParseException {
		return ResponseEntity
				.ok(donationService.getDonationsByMonthInYear(year, month, page, limit, sortBy, sortDirec));
	}

	@GetMapping(value = "start/{startDate}/end/{endDate}/donations/pagination", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDonationByTime(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate, @RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "sortDirec", required = false) String sortDirec) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = (Date) formatter.parse(startDate);
		Date date2 = (Date) formatter.parse(endDate);

		Timestamp timestamp1 = new java.sql.Timestamp(date1.getTime());
		Timestamp timestamp2 = new java.sql.Timestamp(date2.getTime());

		return ResponseEntity
				.ok(donationService.getDonationsByTime(timestamp1, timestamp2, page, limit, sortBy, sortDirec));
	}

	@GetMapping(value = "donations/years", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDonationYears() {
		return ResponseEntity.ok(donationService.getAllYearsInDonation());
	}

	@PostMapping(value = "/donations/return", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> receiveDonation(@RequestBody DonationReturnRequest request) throws ParseException {
		String resCode = request.getVnp_ResponseCode();
		if (resCode.equals("00")) {
			long donationId = Integer.valueOf(request.getVnp_TxnRef());
			Donation donation = donationService.getDonationById(donationId);
			donation.setBankCode(request.getVnp_BankCode());
			donation.setBankTranNo(request.getVnp_BankTranNo());
			donation.setCardType(request.getVnp_CardType());
			donation.setVnpayTranNo(request.getVnp_TransactionNo());
			donation.setStatus('0');

			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = (Date) formatter.parse(request.getVnp_PayDate());
			Timestamp timestamp = new java.sql.Timestamp(date.getTime());
			donation.setDonateTime(timestamp);
//			System.out.println(donation.toString());
			donationService.saveDonation(donation);
			return ResponseEntity.ok(donation);
		}
		return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/donations", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> receiveDonor(@RequestBody DonationRequest request) {
		Donor donor = donationService.saveDonor(request.getDonor());
		Donation donation = new Donation(donor, request.getAmount(), request.getMessage(), '1');
		donationService.saveDonation(donation);
		String urlRedirect = vnpayService.generateUrlRedirect(request.getAmount(), donation.getDonationId(),
				request.getUrlReturn(), request.getMessage());

		Map<String, String> returnStatement = new HashMap<>();
		returnStatement.put("urlRedirect", urlRedirect);
		return ResponseEntity.ok(returnStatement);
	}

}
