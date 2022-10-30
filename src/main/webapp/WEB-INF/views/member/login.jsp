<%--
  Created by IntelliJ IDEA.
  User: zerock
  Date: 2022-10-04
  Time: 오전 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<style>
  .login-wrap{
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .login-content{
    border: 1px solid black;
    padding: 15px;
    border-radius: 15px;
  }
</style>
<div class="login-wrap">
  <div class="login-content">
    <form action="/login" method="post">
      <h1>${param.error}</h1>
      <div class="mb-3">
        <label for="mid" class="form-label">ID</label>
        <input type="text" class="form-control" name="mid" id="mid" aria-describedby="emailHelp">
      </div>
      <div class="mb-3">
        <label for="mpw" class="form-label">Password</label>
        <input type="password" class="form-control" name="mpw" id="mpw">
      </div>
      <div>
        <input type="checkbox" name="remember-me">자동로그인
      </div>
      <button type="submit" class="btn btn-primary">LOGIN</button>
    </form>
  </div>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>








