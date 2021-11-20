<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" errorPage="error.jsp" isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Global -->
    <c:import url="sharedView/global.html" />
    <!-- <link rel="stylesheet" href="./css/bootstrap.css" /> -->

    <!-- Local -->
    <link rel="stylesheet" href="./css/productItem.css" />
    <title>${product.getProductName()}</title>
  </head>
  <body>
    <c:import url="sharedView/header.jsp"></c:import>
    <main class="product-container">
      <section class="product">
        <div class="product-view">
          <figure class="product-image-wrapper">
            <img
              class="product-image"
              src="${product.pictureUrl}"
              alt="${product.getProductName()}"
            />
          </figure>
          <div class="button-wrapper">
            <div class="add-to-cart">
              <span>Thêm vào giỏ hàng</span>
            </div>
            <div class="buy-now">
              <span>Mua ngay</span>
            </div>
          </div>
        </div>
        <div class="product-info">
          <h1 class="product-name">${product.getProductName()}</h1>
          <div class="product-supplier-author">
            <div class="product-author-row">
              <div class="product-row-content">
                Nhà cung cấp:
                <span style="color: #2489f4">${product.supplier}</span>
              </div>
              <div class="product-row-content">
                Tác giả: <span>${product.nameAuthor}</span>
              </div>
            </div>
            <div class="product-author-row">
              <div class="product-row-content">
                Nhà xuất bản: <span>${product.NXB}</span>
              </div>
            </div>
          </div>
          <div class="product-rate">
            <div class="product-stars">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <span>5.0</span>
            </div>
            <span>(3 <span>đánh giá</span>)</span>
            <div class="product-addon">
              <div>
                <i class="fal fa-heart fa-2x"></i>
              </div>
              <div>
                <i class="fal fa-share-alt fa-2x"></i>
              </div>
            </div>
          </div>
          <div class="product-price">${product.price} d</div>
          <div class="product-quantity-wrapper">
            <label>Số lượng:</label>
            <div class="product-quantity">
              <a href="">
                <i class="far fa-minus"></i>
              </a>
              1
              <a href="">
                <i class="far fa-plus"></i>
              </a>
            </div>
          </div>
        </div>
      </section>
      <section class="product-details">
        <div class="product-title">Thông tin sản phẩm</div>
        <div class="product-details-contents">
          <div class="product-details-content">
            <div class="product-content-label">Mã hàng</div>
            <div class="product-content-detail">${product.getSku()}</div>
          </div>
          <div class="product-details-content">
            <div class="product-content-label">Tên Nhà Cung Cấp</div>
            <div class="product-content-detail">${product.supplier}</div>
          </div>
          <div class="product-details-content">
            <div class="product-content-label">Tác giả</div>
            <div class="product-content-detail">${product.nameAuthor}</div>
          </div>
          <div class="product-details-content">
            <div class="product-content-label">NXB</div>
            <div class="product-content-detail">${product.NXB}</div>
          </div>
          <div class="product-details-content">
            <div class="product-content-label">Genres</div>
            <div class="product-content-detail">${product.typeBook}</div>
          </div>
          <div class="product-details-description">${product.description}</div>
        </div>
      </section>
      <section class="product-rating-container">
        <div class="product-title">Đánh giá sản phẩm</div>
        <section class="product-rating">
          <div class="rating-view">
            <div class="user-rating">5<span class="rating">/5</span></div>
            <div class="rating-star">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
            </div>
            <span class="number-rating">(3 <span>đánh giá</span>)</span>
          </div>
          <div class="review-rating-container">
            <div class="review-rating">
              <div class="rating-star">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
              <span>100%</span>
            </div>
            <div class="review-rating">
              <div class="rating-star">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
              <span>100%</span>
            </div>
            <div class="review-rating">
              <div class="rating-star">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
              <span>100%</span>
            </div>
            <div class="review-rating">
              <div class="rating-star">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
              <span>100%</span>
            </div>
            <div class="review-rating">
              <div class="rating-star">
                <i class="fas fa-star"></i>
              </div>
              <span>100%</span>
            </div>
          </div>
          <div class="noti-non-user">
            Chỉ có thành viên mới có thể viết nhận xét.Vui lòng đăng nhập hoặc
            đăng ký.
          </div>
        </section>
        <section class="user-comments">
          <div class="user-comment">
            <section class="user">
              <div class="username">Duong Le</div>
              <span class="comment-date">12/11/2021</span>
            </section>
            <section class="comment">
              <div class="rating-star">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
              <div class="comment-content">
                Truyện bìa xin, nét vẽ là gu của mình nói chung art đẹp lắm.
                Truyện kiểu đáng yêu nhẹ nhàng hài hước cũng có ý nghĩa cũng có.
                Chưa kể fahasa bọc truyện xinh lắm đóng gói đẹp mà giao còn
                nhanh ghê ý. Bộc này rất đáng để mua mà mình đu muộn nên không
                có full quà tặng từ tập 1 buồn ghê ý
              </div>
            </section>
          </div>
          <div class="user-comment">
            <section class="user">
              <div class="username">Nguyen Minh Khang</div>
              <span class="comment-date">12/11/2021</span>
            </section>
            <section class="comment">
              <div class="rating-star">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
              <div class="comment-content">
                Dành cho các bạn lười đọc nhiều chữ thì hãy mua truyện tranh.
                Truyện tranh bao gồm cả hình và chữ, tùy đợt mà bìa rời, kèm quà
                như card pvc, postcard
              </div>
            </section>
          </div>
          <div class="user-comment">
            <section class="user">
              <div class="username">Le Ho Hai Duong</div>
              <span class="comment-date">12/11/2021</span>
            </section>
            <section class="comment">
              <div class="rating-star">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
              <div class="comment-content">
                Truyện rất hay và ok , có nhiều tình tiết hài hước. Xịn
                hết......................................................
              </div>
            </section>
          </div>
        </section>
      </section>
    </main>
    <c:import url="sharedView/footer.jsp"></c:import>
    <script src="./js/productItem.js"></script>
  </body>
</html>
