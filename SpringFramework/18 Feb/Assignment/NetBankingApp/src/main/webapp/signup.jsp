<html>
<head>
    <title>SignUp</title>
</head>
<body>
    <h2>Please Sign Up</h2>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <form action="verifySignUp" method="POST">
		Customer ID: <input type="text" name="customerId" value="${customerId != null ? customerId : ''}"><br>

        Username: <input type="text" name="user" value="${user}"><br>

        Password: <input type="password" name="password"><br>

        Confirm Password: <input type="password" name="cpassword"><br>
        <br>

        <input type="submit" value="Sign Up">
        <input type="reset" value="Clear">
        <input type="button" value="Exit">
    </form>

    <h3>Already a user? <a href="signin">Click Here</a></h3>
</body>
</html>
