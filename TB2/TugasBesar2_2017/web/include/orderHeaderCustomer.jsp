<%--
  Created by IntelliJ IDEA.
  User: Finiko
  Date: 11/28/2017
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%!
    //page = SelectDestination, SelectDriver, ChatDriver, CompleteOrder
    public String printOrderHeaderCustomer (String page) {
      String markup = "";
      markup = markup + "<div class=\"section-header\">";
      markup = markup + "    <div class=\"section-title\">";
      markup = markup + "        MAKE AN ORDER";
      markup = markup + "    </div>";
      markup = markup + "</div>";
      if (page=="SelectDestination") {
          markup = markup + "<div class=\"section-step row\">";
          markup = markup + "    <div class=\"step active\">";
          markup = markup + "        <div class=\"step-no\">1</div>";
          markup = markup + "        <div class=\"step-guide\">Select Destination</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no\">2</div>";
          markup = markup + "        <div class=\"step-guide\">Select A Driver</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no-container\">";
          markup = markup + "            <div class=\"step-no\">3</div>";
          markup = markup + "        </div>";
          markup = markup + "        <div class=\"step-guide\">Chat Driver</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no\">4</div>";
          markup = markup + "        <div class=\"step-guide\">Complete your orders</div>";
          markup = markup + "    </div>";
          markup = markup + "</div>";
      } else if (page=="SelectDriver") {
        markup = markup + "<div class=\"section-step row\">";
        markup = markup + "    <div class=\"step\">";
        markup = markup + "        <div class=\"step-no\">1</div>";
        markup = markup + "        <div class=\"step-guide\">Select Destination</div>";
        markup = markup + "    </div>";
        markup = markup + "    <div class=\"step active\">";
        markup = markup + "        <div class=\"step-no\">2</div>";
        markup = markup + "        <div class=\"step-guide\">Select A Driver</div>";
        markup = markup + "    </div>";
        markup = markup + "    <div class=\"step\">";
        markup = markup + "        <div class=\"step-no-container\">";
        markup = markup + "            <div class=\"step-no\">3</div>";
        markup = markup + "        </div>";
        markup = markup + "        <div class=\"step-guide\">Chat Driver</div>";
        markup = markup + "    </div>";
        markup = markup + "    <div class=\"step\">";
        markup = markup + "        <div class=\"step-no\">4</div>";
        markup = markup + "        <div class=\"step-guide\">Complete your orders</div>";
        markup = markup + "    </div>";
        markup = markup + "</div>";
      } else if (page=="ChatDriver") {
          markup = markup + "<div class=\"section-step row\">";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no\">1</div>";
          markup = markup + "        <div class=\"step-guide\">Select Destination</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no\">2</div>";
          markup = markup + "        <div class=\"step-guide\">Select A Driver</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step active\">";
          markup = markup + "        <div class=\"step-no-container\">";
          markup = markup + "            <div class=\"step-no\">3</div>";
          markup = markup + "        </div>";
          markup = markup + "        <div class=\"step-guide\">Chat Driver</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no\">4</div>";
          markup = markup + "        <div class=\"step-guide\">Complete your orders</div>";
          markup = markup + "    </div>";
          markup = markup + "</div>";
      } else if (page=="CompleteOrder") {
          markup = markup + "<div class=\"section-step row\">";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no\">1</div>";
          markup = markup + "        <div class=\"step-guide\">Select Destination</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no\">2</div>";
          markup = markup + "        <div class=\"step-guide\">Select A Driver</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step\">";
          markup = markup + "        <div class=\"step-no-container\">";
          markup = markup + "            <div class=\"step-no\">3</div>";
          markup = markup + "        </div>";
          markup = markup + "        <div class=\"step-guide\">Chat Driver</div>";
          markup = markup + "    </div>";
          markup = markup + "    <div class=\"step active\">";
          markup = markup + "        <div class=\"step-no\">4</div>";
          markup = markup + "        <div class=\"step-guide\">Complete your orders</div>";
          markup = markup + "    </div>";
          markup = markup + "</div>";
      }
      return (markup);
    }
%>