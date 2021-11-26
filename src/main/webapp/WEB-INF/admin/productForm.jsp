<%@ page pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <!-- Custom fonts for this template-->
    <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
        type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/admin/ProductForm.css">
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <c:import url="shareCode/leftHeader.html"></c:import>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <c:import url="shareCode/headerUser.html"></c:import>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">${FormCommand} Product</h1>
                    </div>

                    <!-- Content Row -->


                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-lg-8 mb-4">
                            <form class="user" action="product" method="post">
                                <input id="command" type="hidden" name="command" value="${FormCommand}">
                                <div class="p-2 card shadow">
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label class="">Author</label>
                                            <input type="text" class="form-control" id="exampleInputPassword"
                                                placeholder="Name of author..." name="author"
                                                value="${item.nameAuthor}">
                                        </div>
                                        <div class="col-sm-6">
                                            <label class="">NXB</label>
                                            <input type="text" class="form-control" id="exampleRepeatPassword"
                                                placeholder="Organzation..." name="nxb" value="${item.NXB}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="">Product name</label>
                                        <input type="text" class="form-control" id="exampleInputEmail"
                                            aria-describedby="emailHelp" placeholder="Title of e-book..."
                                            name="nameItem" value="${item.getProductName()}">
                                    </div>
                                    <div class="form-group">
                                        <label class="">Describe</label>
                                        <textarea name="description" rows="8" cols="40" class="form-control"
                                            placeholder="Detail product">${item.description}</textarea>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label class="">Price</label>
                                            <input type="text" class="form-control" id="exampleInputPassword"
                                                placeholder="VND" name="price" value="${item.price}">
                                        </div>
                                        <input id="productId" type="hidden" name="id" value=${item.id}>
                                        <div class="col-sm-6">
                                            <label class="">Supplier</label>
                                            <input type="text" class="form-control" id="exampleRepeatPassword"
                                                placeholder="Organzation..." name="supplier" value="${item.supplier}">
                                        </div>
                                    </div>
                                    <hr>
                                    <input name="action" type="submit" value="Save Change"
                                        class="btn btn-primary btn-user btn-block" />
                                </div>
                            </form>
                        </div>
                    </div>
                    <c:if test="${FormCommand == 'Update'}">
                        <div class="row">
                            <h1 class="h3 mb-0 text-gray-800">Manage Photo</h1>
                            <div class="p-2 card shadow">
                                <button class="btn btn-primary" id="uploadPhoto">Upload Photo</button>
                                <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <c:forEach var="photo" items="${item.photos}">
                                        <div class="carousel-item active">
                                            <img src="${photo.url}" class="d-block w-100" alt="...">
                                            <div class="carousel-caption d-none d-md-block">
                                                <c:choose>
                                                <c:when test="${photo.Main}">
                                                <h5>Main Photo</h5>
                                                </c:when>
                                                <c:when test="${photo.Main}">
                                                <form action="photo" method="post">
                                                    <input type="hidden" name="command" value="SetMain">
                                                    <input type="hidden" name="productId" value=${item.id}>
                                                    <input type="hidden" name="photoId" value=${photo.id}>
                                                    <input name="action" type="submit" value="Set to main"
                                                        class="btn btn-primary btn-block" />
                                                </form>
                                                <form action="photo" method="post">
                                                    <input type="hidden" name="command" value="Delete">
                                                    <input type="hidden" name="productId" value=${item.id}>
                                                    <input type="hidden" name="photoId" value=${photo.id}>
                                                    <input name="action" type="submit" value="Delete photo"
                                                        class="btn btn-danger btn-block" />
                                                </form>
                                                </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        </c:forEach>
                                    </div>
                                    <button class="carousel-control-prev" type="button"
                                        data-target="#carouselExampleCaptions" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button"
                                        data-target="#carouselExampleCaptions" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <c:import url="shareCode/logoutModal.html"></c:import>

    <script src="https://ngocthien2306.github.io/Admin-Site/js/file.js"></script>
    <!-- Bootstrap core JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery/jquery.min.js"></script>
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="https://ngocthien2306.github.io/Admin-Site/js/sb-admin-2.min.js"></script>

    <!-- cloudinary upload widget -->
    <script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>

    <!-- local upload instantiation -->
    <script src="../js/uploadPhoto.js" type="text/javascript"></script>

</body>

</html>