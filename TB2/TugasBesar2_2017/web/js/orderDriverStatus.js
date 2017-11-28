function changeToFinding() {
    var passivePage = document.getElementsByClassName("passivePage");
    var findingPage = document.getElementsByClassName("findingPage");

    passivePage.setAttribute("style", "display:none");
    findingPage.setAttribute("style", "display:inline-block");
}
function cancelFinding() {
    var passivePage = document.getElementsByClassName("passivePage");
    var findingPage = document.getElementsByClassName("findingPage");

    passivePage.setAttribute("style", "display:inline-block");
    findingPage.setAttribute("style", "display:none");
}