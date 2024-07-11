

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .list-group-item.active a {
        color: #fff;

    }
    .list-group-item a {
        text-decoration: none;
    }


    .filter-container {
        max-width: 300px;
        border: 1px solid #ccc;
        padding: 20px;
        border-radius: 5px;
    }
    .filter-container h2 {
        margin-top: 0;
    }
    .filter-container .filter-button {
        display: inline-block;
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .filter-container .filter-button:hover {
        background-color: #0056b3;
    }
    .filter-container .brand-checkbox {
        display: flex;
        align-items: center;
        margin: 10px 0;
    }
    .filter-container .brand-checkbox input {
        margin-right: 10px;
    }
    input[type="checkbox"]#asus:checked + label,
    input[type="checkbox"]#apple:checked + label {
        color: red;
    }



</style>

<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group">
            <li class="list-group-item text-white <c:if test='${view == "0"}'>active</c:if>'">
                    <a href="category?categoryId=0">All</a>
                </li>
            <c:forEach items="${listC}" var="category">
                <li class="list-group-item text-white <c:if test='${category.categoryId == view}'>active</c:if>'">
                    <a href="category?categoryId=${category.categoryId}" <c:if test='${category.categoryId == view}'>class="text-danger"</c:if>>${category.categoryName}</a>
                    </li>
            </c:forEach>
        </ul>
    </div>

                    <label for="sortBy" style=" font-family: inherit"><h3>Sort by (Price)</h3></label>
    <form action="home" method="post" class="mb-3" style="border: none; margin: 0; padding: 0;">
        <div class="d-flex align-items-center">
            <select id="sortBy" name="sortBy" class="form-select text-success" style="max-width: 200px; border: 1px solid green; border-radius: 5px;">
                <option value="Default" ${empty param.sortBy ? 'selected' : ''}>default</option>
                <option value="AToZ" ${param.sortBy == 'AToZ' ? 'selected' : ''}>a to z</option>
                <option value="ZToA" ${param.sortBy == 'ZToA' ? 'selected' : ''}>z to a</option>
                <option value="LowToHigh" ${param.sortBy == 'LowToHigh' ? 'selected' : ''}>low to high</option>
                <option value="HighToLow" ${param.sortBy == 'HighToLow' ? 'selected' : ''}>high to low</option>

            </select>
            <button type="submit" class="btn text-white ms-2" style="background-color: #f64876; border-radius: 5px;">
                Sort
            </button>
        </div>
    </form>

    <h2 class="pt-2 mb-4">Filters</h2>
    <section class="mb-4">
        <div class="d-flex justify-content-between align-items-center mt-0">
            <form action="search" method="post" class="d-flex">
                <div class="input-group">
                    <input name="productName" type="text" value="${pi}" class="form-control" aria-label="Search" placeholder="Search...">
                    <button type="submit" class="btn btn-secondary">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </form>
        </div>
    </section>
       <section class="mb-4">

        <h2>Brands</h2>
        <form action="filter-brand" method="post" id="brandForm" onchange="document.getElementById('brandForm').submit()">

            <div class="brand-checkbox">
                <input type="checkbox" id="asus" name="brand" value="Asus">
                <label for="asus">Asus</label>
            </div>
            <div class="brand-checkbox">
                <input type="checkbox" id="apple" name="brand" value="Apple">
                <label for="apple">Apple</label>
            </div>
            <!--<button type="submit" class="btn-sm">FILTER</button>-->
        </form>




        </section>
    <script>
        window.addEventListener('DOMContentLoaded', (event) => {
            let checkboxes = document.querySelectorAll('input[name="brand"]');
            checkboxes.forEach((checkbox) => {
                let brand = checkbox.value;
                if (window.location.search.includes(brand)) {
                    checkbox.checked = true;
                }
            });
        });
 
    </script>
</div>


