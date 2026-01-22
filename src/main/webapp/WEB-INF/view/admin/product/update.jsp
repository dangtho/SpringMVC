<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Update Product - Hỏi Dân IT</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>
      $(document).ready(() => {
        const imageFile = $("#imageFile");
        // const orgImage = "${product.image}";

        // if (orgImage) {
        //   const urlImage = "images/product/" + orgImage;
        //   $("#productImagePreview").attr("src", urlImage);
        //   $("#productImagePreview").css({ display: "block" });
        // }
        imageFile.change(function (e) {
          const imgURL = URL.createObjectURL(e.target.files[0]);
          $("#productImagePreview").attr("src", imgURL);
          $("#productImagePreview").css({ display: "block" });
        });
      });
    </script>

    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>

  <body class="sb-nav-fixed">
    <jsp:include page="/WEB-INF/view/admin/layout/header.jsp" />
    <div id="layoutSidenav">
      <jsp:include page="/WEB-INF/view/admin/layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Manage Product</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">
                <a href="/admin/product">Manage Product</a>
              </li>
              <li class="breadcrumb-item active">Update Product</li>
            </ol>
            <div class="mt-5">
              <div class="row">
                <div class="col-md-6 col-m2 mx-auto">
                  <form:form
                    class="form-create"
                    method="post"
                    action="/admin/product/update"
                    modelAttribute="product"
                    enctype="multipart/form-data"
                  >
                    <p class="fs-4 fw-bold">Update product</p>
                    <div class="divider"></div>
                    <div class="col" style="display: none">
                      <label class="form-label">Id</label>
                      <form:input path="id" type="text" class="form-control" />
                    </div>
                    <div class="row g-3">
                      <div class="col">
                        <label class="form-label">Name</label>
                        <c:set var="errorName">
                          <form:errors
                            path="name"
                            cssClass="invalid-feedback"
                          />
                        </c:set>
                        <form:input
                          onfocusout="validateName(this.value)"
                          path="name"
                          type="text"
                          class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                        />
                        ${errorName}
                      </div>
                      <div class="col">
                        <label class="form-label">Price</label>
                        <c:set var="errorPrice">
                          <form:errors
                            path="price"
                            cssClass="invalid-feedback"
                          />
                        </c:set>
                        <form:input
                          path="price"
                          type="number"
                          class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
                        />
                        ${errorPrice}
                      </div>
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Description</label>
                      <c:set var="errorDetailDescription">
                        <form:errors
                          path="detailDescription"
                          cssClass="invalid-feedback"
                        />
                      </c:set>
                      <form:textarea
                        path="detailDescription"
                        type="text"
                        class="form-control ${not empty errorDetailDescription ? 'is-invalid' : ''}"
                      />
                      ${errorDetailDescription}
                    </div>
                    <div class="row g-3">
                      <div class="col">
                        <label class="form-label">Short description</label>
                        <c:set var="errorShortDescription">
                          <form:errors
                            path="shortDescription"
                            cssClass="invalid-feedback"
                          />
                        </c:set>
                        <form:input
                          path="shortDescription"
                          type="text"
                          class="form-control ${not empty errorShortDescription ? 'is-invalid' : ''}"
                        />
                        ${errorShortDescription}
                      </div>

                      <div class="col">
                        <label class="form-label">Quality</label>
                        <c:set var="errorQuality">
                          <form:errors
                            path="quantity"
                            cssClass="invalid-feedback"
                          />
                        </c:set>
                        <form:input
                          path="quantity"
                          type="number"
                          class="form-control ${not empty errorQuality ? 'is-invalid' : ''}"
                        />
                        ${errorQuality}
                      </div>
                    </div>
                    <div class="row g-3">
                      <div class="col">
                        <label class="form-label">Factory</label>
                        <form:select
                          path="factory"
                          class="form-select"
                          aria-label="Default select example"
                        >
                          <form:option value="Apple(Mac book)"
                            >Apple(Mac book)</form:option
                          >
                          <form:option value="Apple (Mac mini)"
                            >Apple (Mac mini)</form:option
                          >
                          <form:option value="Lenovo">Lenovo</form:option>
                          <form:option value="LG">LG</form:option>
                        </form:select>
                      </div>
                      <div class="col">
                        <label class="form-label">Target</label>
                        <form:select
                          path="target"
                          class="form-select"
                          aria-label="Default select example"
                        >
                          <form:option value="Gaming">Gaming</form:option>
                          <form:option value="Thiet-ke">Thiet ke</form:option>
                        </form:select>
                      </div>
                    </div>
                    <div class="col">
                      <label class="form-label">Avatar</label>
                      <input
                        type="file"
                        accept=".png,.jpg,.jpeg"
                        class="form-control"
                        id="imageFile"
                        name="imageFile"
                      />
                    </div>
                    <div class="col-12 mb-3">
                      <img
                        id="productImagePreview"
                        src="/images/product/${product.image}"
                        alt="Avatar preview"
                        style="max-height: 250px; display: block"
                      />
                    </div>
                    <div class="col-12 mb-3">
                      <button type="submit" class="btn btn-primary">
                        Submit
                      </button>
                    </div>
                  </form:form>
                </div>
              </div>
            </div>
          </div>
        </main>
        <jsp:include page="/WEB-INF/view/admin/layout/footer.jsp" />
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="js/scripts.js"></script>
  </body>
</html>
