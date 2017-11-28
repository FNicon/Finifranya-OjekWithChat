<%@ include file="include/common.jsp" %>
<%@ include file="include/orderHeaderCustomer.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>NGEEENG! - A Solution for Your Transportation</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <% if ((Boolean) session.getAttribute("isDriver")) {
        response.sendRedirect("order-driverpassive.jsp");
    } else { %>
        <div class="container">
            <%
                out.println(printHeader((String) session.getAttribute("username")));
                out.println(printNavbar("order"));
            %>
            <div class="section">
                <%
                    out.println(printOrderHeaderCustomer("SelectDestination"));
                %>
                <form action="SelectDriver" name="order" method="post" onsubmit="return validateOrder('order')">
                    <input type="hidden" name="userId" value="${sessionScope.id}">
                    <div class="input-set">
                        <div class="label">Picking point</div>
                        <div class="field"><input type="text" name="origin"></div>
                    </div>
                    <div class="input-set">
                        <div class="label">Destination</div>
                        <div class="field"><input type="text" name="destination"></div>
                    </div>
                    <div class="input-set">
                        <div class="label">Preferred Driver</div>
                        <div class="field"><input type="text" name="preferreddriver" placeholder="(optional)"></div>
                    </div>
                    <div class="row">
                        <div class="align-middle"><input type="submit" value="NEXT" class="button-green"></div>
                    </div>
                </form>
            </div>
        </div>
        <script src="js/validate.js"></script>
    <% } %>
</body>
</html>