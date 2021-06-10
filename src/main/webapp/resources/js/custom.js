//Popup
const popup = document.querySelector(".popup");
const closePopup = document.querySelector(".popup__close");

if (popup) {
    closePopup.addEventListener("click", () => {
        popup.classList.add("hide__popup");
    });

    window.addEventListener("load", () => {
        setTimeout(() => {
            popup.classList.remove("hide__popup");
        }, 500);
    });
}
/*==================== SHOW SCROLL TOP ====================*/
function scrollTop(){
    const scrollTop = document.getElementById('scroll-top');
    // When the scroll is higher than 560 viewport height, add the show-scroll class to the a tag with the scroll-top class
    if(this.scrollY >= 560) scrollTop.classList.add('show-top'); else scrollTop.classList.remove('show-top')
}
window.addEventListener('scroll', scrollTop);

