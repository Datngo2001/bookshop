<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
  <!-- Global -->
  <c:import url="sharedView/global.html" />
  <!-- Carousel -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
  <!-- Local -->
  <link rel="stylesheet" href="./css/home.css">
  <title>E-Book Shop</title>
</head>

<body>
  <c:import url="sharedView/header.jsp"></c:import>
  <br>
  <div class="adver-demo">
    <div class="owl-carousel owl-theme">
      <div class="adver-item">
        <a href=""><img src="https://www.epub.vn/static/assets/bigbanner/webp/the-overweight-book-shelf-colour-pc.webp?v=1" alt=""> </a>
      </div>
      <div class="adver-item">
        <a href=""><img src="https://www.epub.vn/static/assets/bigbanner/webp/who-was-pc.webp?v=1" alt=""> </a>
      </div>
      <div class="adver-item">
        <a href=""><img src="https://www.epub.vn/static/assets/bigbanner/webp/about-us-banner-pc.webp?v=1" alt=""> </a>
      </div>
      <div class="adver-item">
        <a href=""><img src="https://www.epub.vn/static/assets/bigbanner/webp/harry-potter-pc.webp?v=1" alt=""> </a>
      </div>
    </div>
  </div>
  <div class="app_container">
    <div class="grid">
      <div class="grid_row app-content">
        <div class="grid_column-2">
          <nav class="category">
            <h3 class="category-heading"> <i class="category-heading-icon fas fa-list"></i>Content</h3>
            <ul class="category-list">
              <li class="category-item category--active">
                <a href="" class="category-link">Romance</a>
              </li>
              <li class="category-item">
                <a href="" class="category-link">Science Fiction & Fantasy</a>
              </li>
              <li class="category-item">
                <a href="" class="category-link">Comics, Graphic Novels, & Manga</a>
              </li>
              <li class="category-item">
                <a href="" class="category-link">Business & Finance</a>
              </li>
            </ul>
          </nav>
        </div>
        <div class="grid_column-10">

          <div class="home-filter">
          	<div class="sort-by">
            <span class="home-label">Sorting by</span>
            <button class="btn-product home-filter-btn">Popular</button>
            <button class="btn-product btn-primary home-filter-btn">Newest</button>
            <button class="btn-product home-filter-btn">Selling</button>
            </div>
            <div class="select-input">
              <span class="select-price-label">Price</span>
              <i class="fas fa-angle-down select-price-icon"></i>
              <ul class="select-input-list">
                <li class="select-input-item">
                  <a href="" class="select-input-link">Increase</a>
                </li>
                <li class="select-input-item">
                  <a href="" class="select-input-link">Decrease</a>
                </li>
              </ul>
            </div>
            <div class="home-page">

              <div class="filter-control" style="display: flex">
				<div class="info-page" style="display: flex;">
					<label> ${next + 1} </label> / <label> ${max}</label>
				</div>
              <form action="home" method="post">
              	<input type="hidden" name="action" value="PREV">
              	<input type="hidden" name="next" value="${next}">
				<button type="submit" class="filter-page-btn"><i class="fas fa-angle-left"></i></button>
              </form>
              
              <form action="home" method="post">
              <input type="hidden" name="action" value="NEXT">
                <input type="hidden" name="next" value="${next}">
              	<button type="submit" class="filter-page-btn"><i class="fas fa-angle-right"></i></button>
               </form>
              </div>
            </div>
          </div>
          <div class="home-product">
            <div class="grid_row">
              <c:forEach var="product" items="${list_product}">
                <c:url var="link" value="home">
                  <c:param name="command" value="LOAD" />
                  <c:param name="productID" value="${product.id}" />
                </c:url>

                <div class="grid_column-2-5">
                  <div class="card-trending">
                    <div class="product-item border-card">
                      <a class="card-link-product" href="${link}">
                       <div class="product-item-img" style="background-image: url(${product.pictureUrl});"></div>
                      </a>
                      <p class="trending-item-name">${product.getProductName()}</p>
                      <p class="trending-item-author">${product.nameAuthor}</p>
                      <div class="product-action">
                        <span class="product-action-heart product-action-liked">
                          <i class="like-icon far fa-heart"></i>
                          <i class="liked-icon fas fa-heart"></i>
                        </span>
                        <div class="product-action-star">
                          <i class="star-gold fas fa-star"></i>
                          <i class="star-gold fas fa-star"></i>
                          <i class="star-gold fas fa-star"></i>
                          <i class="star-gold fas fa-star"></i>
                          <i class="star-gold far fa-star"></i>
                        </div>
                      </div>
                      <div class="trending-item-price">
                        <span class="price-old mr-up">${product.price}</span>
                        
                        <span class="price-current mr-up">${product.getPriceDiscount()}</span>
                      </div>
                      <form action="${pageContext.request.contextPath}/cart" method="post">
                      		<input type="hidden" name="productId"  value="${product.id}">
                      		<input type="hidden" name="quantity"  value="1">
                      		<input type="hidden" name="action"  value="ADD">
                      	  <input type="submit" class="btn_add-to-cart" value="Add to cart"/>
                      </form>

                      <div class="product-item-favourite">
                        <i class="fas fa-check"></i> Interesting
                      </div>
                      <c:choose>
                      <c:when test="${product.discount == 0 }">
                      
                      </c:when>
                      <c:otherwise>
                      <div class="product-item-sale">
                        <span class="product-item-label">Discount</span>
                        <span class="product-item-percent">${product.getDiscount()}%</span>
                      </div>
                      </c:otherwise>
                      </c:choose>
                    </div>
                  </div>
                </div>
              </c:forEach>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  
<br>
	<div class="grid">
      <div class="grid_row">
        <div class="trending_book">
          <div class="trending_book-header">
            <span class="trending_book-title">Trending Now in eBooks</span>
            <ul class="trending-list">
              <li class="trending_item"><a href="" class="trending_item-link">Discount</a></li>
              <li class="trending_item"><a href="" class="trending_item-link">Newest</a></li>
              <li class="trending_item"><a href="" class="trending_item-link">See All</a></li>
            </ul>
          </div>
          <div class="carousel owl-carousel">
          <c:forEach var="item" items="${trending_book}">
            <div class="card-trending">
              <div class="product-item border-card">
                <div class="product-item-img" style="background-image: url(${item.pictureUrl});"></div>
                <p class="trending-item-name">${item.getProductName()}</p>
                <p class="trending-item-author">${item.getNameAuthor()}</p>
                <div class="product-action">
                  <span class="product-action-heart product-action-liked">
                    <i class="like-icon far fa-heart"></i>
                    <i class="liked-icon fas fa-heart"></i>
                  </span>
                  <div class="product-action-star">
                    <i class="star-gold fas fa-star"></i>
                    <i class="star-gold fas fa-star"></i>
                    <i class="star-gold fas fa-star"></i>
                    <i class="star-gold fas fa-star"></i>
                    <i class="star-gold far fa-star"></i>
                  </div>
                </div>
                <div class="trending-item-price">
                  <span class="price-old mr-up"> ${item.price}</span>
                  <span class="price-current mr-up">${item.getPriceDiscount() }</span>
                </div>
  
                <button class="btn_add-to-cart">Add to cart</button>
                <div class="product-item-favourite">
                  <i class="fas fa-check"></i> Interesting
                </div>
                      <c:choose>
                      <c:when test="${item.discount == 0 }">
                      
                      </c:when>
                      <c:otherwise>
                      <div class="product-item-sale">
                        <span class="product-item-label">Discount</span>
                        <span class="product-item-percent">${item.getDiscount()}%</span>
                      </div>
                      </c:otherwise>
                      </c:choose>
              </div>
          </div>
          </c:forEach>
        </div>
      </div>
    </div>
    <br/>
    <br/>
   	<div class="grid">
      <div class="grid_row">
        <div class="trending_book">
          <div class="trending_book-header">
            <span class="trending_book-title">Popular eBook Pre-orders <br> 
            <p class="trending_book-title-after">
            Stay ahead of the curve and get the most anticipated eBooks of the year the moment they come out. </p></span>
            <ul class="trending-list mrtop">
              <li class="trending_item"><a href="" class="trending_item-link">Discount</a></li>
              <li class="trending_item"><a href="" class="trending_item-link">Newest</a></li>

            </ul>
          </div>
          <div class="carousel owl-carousel">
          <c:forEach var="item" items="${po_order}">
            <div class="card-trending">
              <div class="product-item border-card">
                <div class="product-item-img" style="background-image: url(${item.pictureUrl});"></div>
                <p class="trending-item-name">${item.getProductName() }</p>
                <p class="trending-item-author">${item.getNameAuthor() }</p>
                <div class="product-action">
                  <span class="product-action-heart product-action-liked">
                    <i class="like-icon far fa-heart"></i>
                    <i class="liked-icon fas fa-heart"></i>
                  </span>
                  <div class="product-action-star">
                    <i class="star-gold fas fa-star"></i>
                    <i class="star-gold fas fa-star"></i>
                    <i class="star-gold fas fa-star"></i>
                    <i class="star-gold fas fa-star"></i>
                    <i class="star-gold far fa-star"></i>
                  </div>
                </div>
                <div class="trending-item-price">
                  <span class="price-old mr-up"> ${item.price}</span>
                  <span class="price-current mr-up">${item.getPriceDiscount()}</span>
                </div>
  
                <button class="btn_add-to-cart">Add to cart</button>
                <div class="product-item-favourite">
                  <i class="fas fa-check"></i> Interesting
                </div>
                      <c:choose>
                      <c:when test="${item.discount == 0 }">
                      
                      </c:when>
                      <c:otherwise>
                      <div class="product-item-sale">
                        <span class="product-item-label">Discount</span>
                        <span class="product-item-percent">${item.getDiscount()}%</span>
                      </div>
                      </c:otherwise>
                      </c:choose>
              </div>
            </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  <br> <br>
  <c:import url="sharedView/footer.jsp"></c:import>
  <script>
    $(".carousel").owlCarousel({
      margin: 20,
      loop: true,
      nav: true,
      autoplay: true,
      autoplayTimeout: 3000,
      smartSpeed: 1000,
      autoplayHoverPause: true,
      responsive: {
        0: {
          items: 2,
          nav: false
        },
        600: {
          items: 3,
          nav: false
        },
        1000: {
          items: 6,
          nav: false
        }
      }
    });
    var owl = $(".owl-carousel");
    owl.owlCarousel({
      items: 1,
      loop: true,
      nav: true,
      autoplay: true,
      autoplayTimeout: 3000,
      smartSpeed: 1000,
      autoplayHoverPause: true,
    });
    
  </script>
</body>

</html>
