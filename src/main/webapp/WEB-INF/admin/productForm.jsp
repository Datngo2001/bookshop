<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="ISO-8859-1">
  	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <!-- Custom fonts for this template-->
    <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet">
	<link rel="stylesheet" href="./css/admin/ProductForm.css">
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
				<c:import url="shareCode/adminHeader.jsp"></c:import>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Product</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <!-- Content Row -->

					
                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-lg-4 mb-4">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Upload file</h6>
                                </div>
                                <div class="card-body">
                                    <div class="text-center">
									  <div class="wrapper">
									    <form action="#">
									      <input class="file-input" type="file" name="file" hidden>
									      <i class="fas fa-cloud-upload-alt"></i>
									      <p>Browse File to Upload</p>
									    </form>
									    <section class="progress-area"></section>
									    <section class="uploaded-area"></section>
									  </div>
						            </div>
                                    <p>Add some quality, svg illustrations to your project courtesy of <a
                                            target="_blank" rel="nofollow" href="https://undraw.co/">unDraw</a>, a
                                        constantly updated collection of beautiful svg images that you can use
                                        completely free and without attribution!</p>
                                    <a target="_blank" rel="nofollow" href="https://undraw.co/">Browse Illustrations on
                                        unDraw &rarr;</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8 mb-4">
							<form class="user" action="product" method="get">
							<input type="hidden" name="command" value="Update">
					              <div class="p-2 card shadow">                                    	                     
                                    	 <div class="form-group row">
		                                    <div class="col-sm-6 mb-3 mb-sm-0">
		                                    <label class="">Author</label>
		                                        <input type="text" class="form-control"
		                                            id="exampleInputPassword" placeholder="Name of author..." name="author"
		                                            value="${item.nameAuthor}"
		                                            >
		                                    </div>
		                                    <div class="col-sm-6">
		                                    <label class="">NXB</label>
		                                        <input type="text" class="form-control"
		                                            id="exampleRepeatPassword" placeholder="Organzation..." name="nxb"
		                                            value="${item.NXB}"
		                                            >
		                                    </div>
		                                </div>
                                        <div class="form-group">
                                          <label class="">Product name</label>
                                            <input type="text" class="form-control"
                                                id="exampleInputEmail" aria-describedby="emailHelp"
                                                placeholder="Title of e-book..." name="nameItem"
                                                value ="${item.getProductName()}"
                                                >
                                        </div>
                                          <div class="form-group">
                                          <label class="">Describe</label>
                                           <textarea name="description" rows="8" cols="40" class="form-control" 
                                           placeholder="Detail product">${item.description}</textarea>
                                        </div>
                                         <div class="form-group row">
		                                    <div class="col-sm-6 mb-3 mb-sm-0">
		                                    <label class="">Price</label>
		                                        <input type="text" class="form-control"
		                                            id="exampleInputPassword" placeholder="VND" name="price"
		                                            value="${item.price}"
		                                            >
		                                    </div>
		                                    <input type="hidden" name="id" value=${item.id}>
		                                    <div class="col-sm-6">
		                                    <label class="">Supplier</label>
		                                        <input type="text" class="form-control"
		                                            id="exampleRepeatPassword" placeholder="Organzation..." name="supplier"
		                                            value="${item.supplier}"
		                                            >
		                                    </div>
		                                </div>
									<hr>
                                    <input name="action" type="submit" value="Submit" class="btn btn-primary btn-user btn-block"/>                                                                          
                                </div>
							</form>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; E-book 2021</span>
                    </div>
                </div>
            </footer>
        </div>
    </div>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.jsp">Logout</a>
                </div>
            </div>
        </div>
    </div>
	<script src="https://ngocthien2306.github.io/Admin-Site/js/file.js"></script>
    <!-- Bootstrap core JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery/jquery.min.js"></script>
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="https://ngocthien2306.github.io/Admin-Site/js/sb-admin-2.min.js"></script>


</body>

</html>
