<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>VNPAY</title>

</head>

<body>

	<div class="container">
		<div class="header clearfix">

			<h3 class="text-muted">VNPAY DEMO</h3>
		</div>
		<h3>Tạo mới đơn hàng</h3>
		<div class="table-responsive">
			<form action="./payment" id="frmCreateOrder" method="post">
				<div class="form-group">
					<label for="amount">Số tiền</label> <input class="form-control"
						data-val="true"
						data-val-number="The field Amount must be a number."
						data-val-required="The Amount field is required." id="amount"
						max="100000000" min="1" name="amount" type="number" value="10000" />
				</div>
				<h4>Chọn ngân hàng</h4>
				<div class="form-group">
					<div class="form-group">
						<label for="bankcode">Ngân hàng</label> <select name="bankCode"
							id="bankCode" class="form-control">
							<option value="">Không chọn</option>
							<option value="MBAPP">Ung dung MobileBanking</option>
							<option value="VNPAYQR">VNPAYQR</option>
							<option value="VNBANK">LOCAL BANK</option>
							<option value="IB">INTERNET BANKING</option>
							<option value="ATM">ATM CARD</option>
							<option value="INTCARD">INTERNATIONAL CARD</option>
							<option value="VISA">VISA</option>
							<option value="MASTERCARD">MASTERCARD</option>
							<option value="JCB">JCB</option>
							<option value="UPI">UPI</option>
							<option value="VIB">VIB</option>
							<option value="VIETCAPITALBANK">VIETCAPITALBANK</option>
							<option value="SCB">Ngan hang SCB</option>
							<option value="NCB">Ngan hang NCB</option>
							<option value="SACOMBANK">Ngan hang SacomBank</option>
							<option value="EXIMBANK">Ngan hang EximBank</option>
							<option value="MSBANK">Ngan hang MSBANK</option>
							<option value="NAMABANK">Ngan hang NamABank</option>
							<option value="VNMART">Vi dien tu VnMart</option>
							<option value="VIETINBANK">Ngan hang Vietinbank</option>
							<option value="VIETCOMBANK">Ngan hang VCB</option>
							<option value="HDBANK">Ngan hang HDBank</option>
							<option value="DONGABANK">Ngan hang Dong A</option>
							<option value="TPBANK">Ngân hàng TPBank</option>
							<option value="OJB">Ngân hàng OceanBank</option>
							<option value="BIDV">Ngân hàng BIDV</option>
							<option value="TECHCOMBANK">Ngân hàng Techcombank</option>
							<option value="VPBANK">Ngan hang VPBank</option>
							<option value="AGRIBANK">Ngan hang Agribank</option>
							<option value="MBBANK">Ngan hang MBBank</option>
							<option value="ACB">Ngan hang ACB</option>
							<option value="OCB">Ngan hang OCB</option>
							<option value="IVB">Ngan hang IVB</option>
							<option value="SHB">Ngan hang SHB</option>
							<option value="APPLEPAY">Apple Pay</option>
						</select>
					</div>

				</div>
				<div class="form-group">
					<h5>Chọn ngôn ngữ giao diện thanh toán:</h5>
					<input type="radio" id="language" Checked="True" name="language"
						value="vn"> <label for="language">Tiếng việt</label><br>
					<input type="radio" id="language" name="language" value="en">
					<label for="language">Tiếng anh</label><br>

				</div>
				<button type="submit" class="btn btn-default" href>Thanh
					toán</button>
			</form>
		</div>
		<p>&nbsp;</p>
		<footer class="footer">
			<p>&copy; VNPAY 2020</p>
		</footer>
	</div>

	<link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
	<script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
	<script type="text/javascript">
		$("#frmCreateOrder").submit(function() {
			var postData = $("#frmCreateOrder").serialize();
			var submitUrl = $("#frmCreateOrder").attr("action");
			$.ajax({
				type : "POST",
				url : submitUrl,
				data : postData,
				dataType : 'JSON',
				success : function(x) {
					if (x.code === '00') {
						if (window.vnpay) {
							vnpay.open({
								width : 768,
								height : 600,
								url : x.data
							});
						} else {
							location.href = x.data;
						}
						return false;
					} else {
						alert(x.Message);
					}
				}
			});
			return false;
		});
	</script>

</body>

</html>