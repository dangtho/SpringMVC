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
    <title>Update User - Hỏi Dân IT</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>
      $(document).ready(() => {
        const avatarFile = $("#avatarFile");
        avatarFile.change(function (e) {
          const imgURL = URL.createObjectURL(e.target.files[0]);
          $("#avatarPreview").attr("src", imgURL);
          $("#avatarPreview").css({ display: "block" });
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
            <h1 class="mt-4">Manage Users</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">Manage Users</li>
            </ol>
            <div class="mt-5">
              <div class="row">
                <div class="col-md-6 col-m2 mx-auto">
                  <form:form
                    class="form-create"
                    method="post"
                    action="/admin/user/update"
                    modelAttribute="user"
                    enctype="multipart/form-data"
                  >
                    <p class="fs-4 fw-bold">Update new user</p>
                    <div class="divider"></div>
                    <div class="mb-3" style="display: none">
                      <label class="form-label">id</label>
                      <form:input path="id" type="text" class="form-control" />
                    </div>
                    <div class="row g-3">
                      <div class="mb-3 col">
                        <label class="form-label">Email address</label>
                        <form:input
                          path="email"
                          type="email"
                          class="form-control"
                          aria-describedby="emailHelp"
                          disabled="true"
                        />
                      </div>
                      <div class="mb-3 col">
                        <label class="form-label">Phone number</label>
                        <form:input
                          path="phone"
                          type="text"
                          class="form-control"
                        />
                      </div>
                    </div>
                    <div class="row g-3">
                      <div class="mb-3 col">
                        <label class="form-label">Full name</label>
                        <form:input
                          path="fullName"
                          type="text"
                          class="form-control"
                        />
                      </div>
                      <div class="mb-3 col">
                        <label class="form-label">Address</label>
                        <form:input
                          path="address"
                          type="text"
                          class="form-control"
                        />
                      </div>
                    </div>
                    <div class="row g-3">
                      <div class="col">
                        <label class="form-label">Role</label>
                        <form:select
                          path="role.name"
                          class="form-select"
                          aria-label="Default select example"
                        >
                          <form:option value="USER">User</form:option>
                          <form:option value="ADMIN">Admin</form:option>
                        </form:select>
                      </div>
                      <div class="col">
                        <label class="form-label">Avatar</label>
                        <input
                          type="file"
                          accept=".png,.jpg,.jpeg"
                          class="form-control"
                          id="avatarFile"
                          name="avatarFile"
                        />
                      </div>
                      <div class="col-12 mb-3">
                        <img
                          id="avatarPreview"
                          src="#"
                          alt="Avatar preview"
                          style="max-height: 250px; display: none"
                        />
                      </div>
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
