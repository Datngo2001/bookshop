  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">

  <!-- Custom fonts for this template-->
  <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet">
        <title>Tạo mới đơn hàng</title>
        <!-- Bootstrap core CSS -->

        <!-- Custom styles for this template -->
      
        <script src="./js/jquery-1.11.3.min.js"></script>
		<c:import url="../sharedView/global.html"></c:import>
    </head>

    <body>
	
		<c:import url="../sharedView/header.jsp"></c:import>
         <div class="container">
			<div class="grid">
				<div class="grid_row">
					<div class="grid_column-8" style="margin: 0 auto;">
					           <div class="header clearfix">
            <h3 class="text-muted">VNPAY</h3>
            </div>
            <h5>Tạo mới đơn hàng</h5>
            

            <div class="table-responsive">
            
           	<form action="confirm" method="post">
            	 <input type="hidden" name="action" value="CHECK">
            	 <input type="hidden" name="price" value="${priceTotal}">
            	 <input type="hidden" name="amount" value="${priceTotal}">
            	 <c:choose>
            	<c:when test="${sessionScope.check != null}">
            	  <div class="form-group">
                        <label class="label-control" for="language">Mã khuyến mãi</label>
						<input type="text" class="form-control" name="promoCode" >
						<input type="submit" class=" btn btn-primary" 
						style="margin: 8px 0; width: 100px;" 
						name="" value="Kiểm tra" 
						placeholder="Sử dụng mã giảm giá để nhận được ưu đãi" readonly="readonly">
                    </div>
                    </c:when>
                    <c:otherwise>
                         <div class="form-group">
                        <label class="label-control" for="language">Mã khuyến mãi</label>
						<input type="text" class="form-control" name="promoCode" >
						<input type="submit" class=" btn btn-primary" 
						style="margin: 8px 0; width: 100px;" 
						name="" value="Kiểm tra" 
						placeholder="Sử dụng mã giảm giá để nhận được ưu đãi" >
                    </div>
                    </c:otherwise>
                     </c:choose>
            </form>
                <form action="ajaxServlet" id="frmCreateOrder" method="post">        
                    <div class="form-group">
                        <label class="label-control" for="language">Loại hàng hóa </label>
                        <select name="ordertype" id="ordertype" class="form-control">
                            <option value="topup">Rental Book</option>
    
                            <option value="fashion">E-Book</option>
                            <option value="240004">Audio Book</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="amount">Số tiền (VND)</label>
                        <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." data-val-required="The Amount field is required." 
                        id="amount" max="100000000" min="1" name="amount" type="number" value="${priceTotal}" readonly="readonly"/>
                    </div>

                    <div class="form-group">
                        <label class="label-control" for="OrderDescription">Nội dung thanh toán</label>
                        <textarea class="form-control" cols="20" id="vnp_OrderInfo" name="vnp_OrderInfo" rows="2">Thanh toan don hang </textarea>
                    </div>
                    <div class="form-group">
                        <label class="label-control" for="bankcode">Ngân hàng</label>
                        <select name="bankcode" id="bankcode" class="form-control">
                            <option value="">Không chọn </option>
                            <option value="VNPAYQR">VNPAYQR</option>
                            <option value="MBAPP">MBAPP</option>
                            <option value= "INTCARD">INTCARD</option>
                            <option value= "VNBANK">VNBANK</option>
                            <option value= "VNPAYEWALLET">VNPAYEWALLET</option> 
                            <option value="NCB">  	Ngan hang NCB</option>
                            <option value="SACOMBANK">Ngan hang SacomBank  </option>
                            <option value="EXIMBANK">  Ngan hang EximBank </option>
                            <option value="MSBANK"> 	Ngan hang MSBANK </option>
                            <option value="NAMABANK"> 	Ngan hang NamABank </option>
                            <option value="VISA">  	Thanh toan qua VISA/MASTER</option>
                            <option value="VNMART">  	Vi dien tu VnMart</option>
                            <option value="VIETINBANK">Ngan hang Vietinbank  </option>
                            <option value="VIETCOMBANK"> 	Ngan hang VCB </option>
                            <option value="HDBANK">Ngan hang HDBank</option>
                            <option value="DONGABANK">  	Ngan hang Dong A</option>
                            <option value="TPBANK"> 	Ngân hàng TPBank </option>
                            <option value="OJB">  	Ngân hàng OceanBank</option>
                            <option value="BIDV"> Ngân hàng BIDV </option>
                            <option value="TECHCOMBANK"> 	Ngân hàng Techcombank </option>
                            <option value="VPBANK"> 	Ngan hang VPBank </option>
                            <option value="AGRIBANK"> 	Ngan hang Agribank </option>
                            <option value="MBBANK"> 	Ngan hang MBBank </option>
                            <option value="ACB"> Ngan hang ACB </option>
                            <option value="OCB"> Ngan hang OCB </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="label-control" for="language">Ngôn ngữ</label>
                        <select name="language" id="language" class="form-control">
                            <option value="vn">Tiếng Việt</option>
                            <option value="en">English</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Thanh toán</button>
                </form>
            </div>
					</div>
				</div>
			</div>
	
            <p>
                &nbsp;
            </p>

        </div>
		<c:import url="../sharedView/footer.jsp"></c:import>
        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
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