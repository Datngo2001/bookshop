<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="ISO-8859-1" errorPage="error.jsp"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Global -->
    <c:import url="sharedView/global.html" />
    <link rel="stylesheet" href="./css/home.css" />
    <!-- Custom fonts for this template-->
    <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
        type="text/css" />
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet" />

    <!-- Custom styles for this template-->
    <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet" />
    <!-- Local -->
    <link rel="stylesheet" href="./css/profile.css" />
    <style type="text/css"></style>
    <title>My Profile</title>
</head>

<body>
    <c:import url="sharedView/header.jsp"></c:import>
    <br>
    <div class="text-center">
      <h1 class="text-gray-900">Your Profile</h1>
    </div>
	<div class="grid">
		<div class="grid_row">
			<div class="grid_column-10" style="margin: 0 auto;">
			<form action="profile" method="post">
            <input type="hidden" name="action" value="Update"/>
            <div class="form-group">
                <label for="inputFirstName">Username</label>
                <input readonly type="text" class="form-control" id="inputFirstName" placeholder="First name" name="username" value="${user.username}">
            </div>
            <div class="form-group">
                <label for="inputFirstName">First name</label>
                <input type="text" class="form-control" id="inputFirstName" placeholder="First name" name="fname" value="${user.fname}">
            </div>
            <div class="form-group">
                <label for="inputLastName">Last name</label>
                <input type="text" class="form-control" id="inputLastName" placeholder="Last name" name="lname" value="${user.lname}">
            </div>
            <label>Gender</label>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="M" ${user.gender eq 'M' ? 'checked' : ''}>
                <label class="form-check-label" for="inlineRadio1">Male</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="F" ${user.gender eq 'F' ? 'checked' : ''}>
                <label class="form-check-label" for="inlineRadio2">Female</label>
            </div>
            <div class="form-group">
                <label for="inputBirthDay">Birth Date</label>
                <input type="text" class="form-control" id="inputBirthDay" placeholder="yyyy-mm-dd" name="bdate" value="${user.bdate}">
            </div>
            <div class="form-group">
                <label for="inputEmail">Email address</label>
                <input readonly type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                    placeholder="Email" name="email" value="${user.email}">
            </div>
            <div class="text-center">
              <a class="small" href="forgot-password"
                >Change password</a
              >
            </div>
            <button type="submit" class="btn btn-primary">Save changes</button>
        </form>
			</div>
		</div>
	</div>
	<div class="grid">
		<div class="grid_row">
			<div class="grid_column-10" style="margin: 0 auto;">
			         <div class="trending_book-header">
                <span class="trending_book-title">
                    My e-book<br />
                    <p class="trending_book-title-after">
                        Stay ahead of the curve and get the most anticipated eBooks of the
                        year the moment they come out.
                    </p>
                </span>
            </div>
            <div style="display: flex; flex-wrap: wrap;">
            <c:forEach var="item" items="${list_item}">
            <c:url var="link" value="home">
              <c:param name="command" value="LOAD" />
              <c:param name="productID" value="${item.getProduct().id}" />
            </c:url>
                <div class="grid_column-2-5" >
                    <div class="card-trending">
                        <div class="product-item border-card">
                        	<a href="${link}">
                            <div class="product-item-img" style="background-image: url(${item.getProduct().getPictureUrl()});"></div>
                            </a>
                            <p class="trending-item-name">
                                ${item.getProduct().getProductName()}
                            </p>
                            <p class="trending-item-author">
                                ${item.getProduct().getNameAuthor()}
                            </p>
                            <div class="product-action">  
						<span class="product-action-heart product-action-liked">
		                    <i class="like-icon far fa-heart"></i>
		                    <i class="liked-icon fas fa-heart"></i>
		                  </span> 
		                  <div class="product-action-star">
		                    <c:forEach var="i" begin="1" end="${item.getProduct().getStar()}">
		                      <i class="star-gold fas fa-star"></i>
		                    </c:forEach>
		                    <c:forEach var="i" begin="1" end="${5 - item.getProduct().getStar()}">
		                      <i class="star-gold far fa-star"></i>
		                    </c:forEach>
		                  </div>
		                  </div>

                            <form action="read">
                                <button class="btn btn-primary" style="width: 100%; border-radius: 1px">
                                    Read book
                                </button>
                            </form>

                            <div class="product-item-favourite">
                                <i class="fas fa-check"></i> Interesting
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
			</div>
		</div>
	</div>
	<br>
    <c:import url="sharedView/footer.jsp"></c:import>
</body>

</html>
