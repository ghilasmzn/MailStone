function displayMore(value) {
    var dots = document.getElementById("dots" + value);
    var moreText = document.getElementById("more" + value);
    console.log(value);
    var btnText = document.getElementById('moreBtn' + value);

    if (dots.style.display === "none") {
        dots.style.display = "inline";
        btnText.innerHTML = "<i class= \"bi bi-chevron-double-down\"></i>"; 
        moreText.style.display = "none";
    }
    else {
        dots.style.display = "none";
        btnText.innerHTML = "<i class=\"bi bi-chevron-double-up\"></i>"; 
        moreText.style.display = "inline";
    }
}