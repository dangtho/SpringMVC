<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>Dashboard - Hỏi Dân IT</title>
    <link href="/css/styles.css" rel="stylesheet" />
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
              <div class="d-flex justify-content-between">
                <h3>Table User</h3>
                <a href="/admin/user/create" class="btn btn-primary mb-3"
                  >Create new user</a
                >
              </div>
              <div class="col-md-12 col-m2 mx-auto">
                <table class="table table-hover table-bordered">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">Email</th>
                      <th scope="col">Full Name</th>
                      <th scope="col">Role</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="user" items="${usersList}">
                      <tr>
                        <th>${user.id}</th>
                        <td>${user.email}</td>
                        <td>${user.fullName}</td>
                        <td>${user.role.name}</td>
                        <td>
                          <a
                            class="btn btn-success btn-sm"
                            href="/admin/user/${user.id}"
                          >
                            View
                          </a>

                          <a
                            class="btn btn-warning btn-sm"
                            href="/admin/user/update/${user.id}"
                          >
                            Update
                          </a>
                          <a
                            href="/admin/user/delete/${user.id}"
                            class="btn btn-danger btn-sm"
                          >
                            Delete
                          </a>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
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
