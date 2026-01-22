<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> <%@page
contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- <link rel="stylesheet" href="/css/demo.css" /> -->
    <title>User detail</title>
  </head>
  <body>
    <div class="container mt-5">
      <div class="row">
        <div class="d-flex justify-content-between">
          <a>User detail ${idUser}</a>
        </div>
        <hr />
        <div class="alert alert-danger" role="alert">
          Do you want to delete this user?
        </div>
        <form:form
          method="post"
          action="/admin/user/delete"
          modelAttribute="user"
        >
          <div class="mb-3">
            <label class="form-label">id</label>
            <form:input
              value="${user.id}"
              path="id"
              type="text"
              class="form-control"
            />
          </div>
          <button type="submit" class="btn btn-danger mt-3" style="width: 20%">
            Confirm
          </button>
          <button type="button" class="btn btn-danger mt-3" style="width: 20%">
            cancel
          </button>
        </form:form>
      </div>
    </div>
  </body>
</html>
