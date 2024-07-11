<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Product Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
        <link href="css/detail.css" rel="stylesheet">
        <jsp:include page="includes/navbar.jsp"></jsp:include>
            <style>
                .product-info {
                    padding: 20px;
                }
                .attr {
                    width: 25px;
                    background: #5a5a5a;
                }
                .attr2 {
                    background: #5a5a5a;
                    color: white;
                    padding: 5px;
                    margin-right: 5px;
                }


                .modal {
                    display: none;
                    position: fixed;
                    z-index: 1;
                    padding-top: 50px;
                    left: 0;
                    top: 0;
                    width: 100%;
                    height: 100%;
                    overflow: auto;
                    background-color: rgb(0,0,0);
                    background-color: rgba(0,0,0,0.9);
                }

                /* Modal Content (image) */
                .modal-content {
                    margin: auto;
                    display: block;
                    width: 80%;
                    max-width: 700px;
                }

                /* Close Button */
                .close {
                    position: absolute;
                    top: 15px;
                    right: 35px;
                    color: #f1f1f1;
                    font-size: 40px;
                    font-weight: bold;
                    transition: 0.3s;
                }

                .close:hover,
                .close:focus {
                    color: #bbb;
                    text-decoration: none;
                    cursor: pointer;
                }
                .item-photo img:hover {
                    transform: scale(1.2); /* Phóng to ảnh khi di chuột vào */
                    transition: transform 0.3s ease; /* Tạo hiệu ứng mượt mà */
                }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="row">
                    <div id="myModal" class="modal">
                        <span class="close">&times;</span>
                        <img class="modal-content" id="img01">
                    </div>
                    <div class="col-md-5 item-photo">
                    <c:if test="${not empty detail.productImanges}">
                        <c:forEach items="${detail.productImanges}" var="image">
                            <img src="${image.image}" style="max-width:250px; max-height:300px;" alt="${detail.productName} Image"/>
                        </c:forEach>
                    </c:if>
                </div>


                <div class="col-md-7 product-info">
                    <div class="attr5">Create Date: ${detail.createDate}</div>
                    <h3><a href="home" style="text-decoration: none;" > ${detail.productName}</a></h3>
                    <h5 style="color:#337ab7"> <a href="home" style="text-decoration: none;" >${detail.productBrand} </a><small style="color:#337ab7"></small></h5>
                    <h6 class="title-price"><small>Price</small></h6>
                    <h3> <fmt:formatNumber value="${detail.productPrice}" type="currency" currencySymbol="VND"/></h3>
                    <div class="section" style="padding-bottom:5px;">
                        <h6 class="title-attr"><small>Size</small></h6>
                        <div>
                            <div class="attr2">${detail.specification.size}</div>

                        </div> <br>

                        <h6 class="title-attr"><small>Weight</small></h6>
                        <div>
                            <div class="attr2">${detail.specification.weight}</div>

                        </div>
                    </div>
                    <div class="section" style="padding-bottom:20px;">
                        <h6 class="title-attr2" style="font-size: 23px"><small>description: ${detail.productDescription}</small></h6>
                    </div>
                    <div class="section" style="padding-bottom:20px;">
                        <c:choose>
                            <c:when test="${detail.productQuantity == 0}">
                                <button class="btn btn-secondary" disabled>Sold Out</button>
                            </c:when>
                            <c:otherwise>
                                <a href="cartprocess?productId=${detail.productId}" class="btn btn-danger">
                                    <i class="fa-solid fa-cart-shopping"></i> <!-- Biểu tượng giỏ hàng từ Bootstrap Icons -->
                                    Add to Cart
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <hr>
                    <!-- Similar Products Section -->
                    <!-- Similar Products Section -->
                    <div class="container similar-products">
                        <h4>Sản phẩm Tương tự</h4>
                        <div class="row">
                            <c:forEach items="${similarProducts}" var="similarProduct">
                                <div class="col-md-3">
                                    <div class="product">
                                        <img src="${similarProduct.productImages[0].image}" class="product-image" alt="${similarProduct.productName}"/>
                                        <div class="product-details">
                                            <h4 class="product-name"><a href="productDetail?productId=${similarProduct.productId}">${similarProduct.productName}</a></h4>
                                            <p class="product-price"><fmt:formatNumber value="${similarProduct.productPrice}" type="currency" currencySymbol="VND"/></p>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </div>
            </div>


        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                //-- Click on detail
                document.querySelectorAll("ul.menu-items > li").forEach(function (item) {
                    item.addEventListener("click", function () {
                        // Remove 'active' class from all list items
                        document.querySelectorAll("ul.menu-items > li").forEach(function (otherItem) {
                            otherItem.classList.remove("active");
                        });
                        // Add 'active' class to the clicked item
                        item.classList.add("active");
                    });
                });
            });

            document.addEventListener("DOMContentLoaded", function () {
                //-- Click on detail
                document.querySelectorAll("ul.menu-items > li").forEach(function (item) {
                    item.addEventListener("click", function () {
                        // Remove 'active' class from all list items
                        document.querySelectorAll("ul.menu-items > li").forEach(function (otherItem) {
                            otherItem.classList.remove("active");
                        });
                        // Add 'active' class to the clicked item
                        item.classList.add("active");
                    });
                });

                // Get the modal
                var modal = document.getElementById("myModal");

                // Get the image and insert it inside the modal
                var imgs = document.querySelectorAll(".item-photo img");
                var modalImg = document.getElementById("img01");
                for (var i = 0; i < imgs.length; i++) {
                    imgs[i].addEventListener("click", function () {
                        modal.style.display = "block";
                        modalImg.src = this.src;
                        modalImg.classList.add("zoomed"); // Add the 'zoomed' class when opening the modal
                    });
                }

                // Get the <span> element that closes the modal
                var span = document.getElementsByClassName("close")[0];

                // When the user clicks on <span> (x), close the modal and reset the image size
                span.onclick = function () {
                    modal.style.display = "none";
                    modalImg.classList.remove("zoomed"); // Remove the 'zoomed' class
                };

                // When the user clicks anywhere outside of the modal, close it
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                        modalImg.classList.remove("zoomed"); // Remove the 'zoomed' class
                    }
                };
            });

        </script>
        <%@include  file="includes/finish.jsp" %>
    </body>
</html>
