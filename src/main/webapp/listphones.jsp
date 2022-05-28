<%@ page import="com.example.wcdpractice.entity.Phone" %>
<%@ page import="java.util.List" %><%
    List<Phone> listPhone = (List<Phone>)request.getAttribute("listPhone");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Phone</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>List Phone</h2>
    <a href="/add-phone"><p>Add phone</p></a>
    <table class="table">
        <thead>
        <tr>
            <th>Phone Number</th>
            <th>Phone Name</th>
        </tr>
        </thead>
        <ta>
        <tbody>
        <tr>
            <%
                for (Phone phone : listPhone) {
            %>
                <td><%=phone.getPhoneNumber()%></td>
                <td><%=phone.getPhoneName()%></td>
            <%}
            %>
        </tr>
        </tbody>
        </ta>
    </table>
</div>

</body>
</html>
