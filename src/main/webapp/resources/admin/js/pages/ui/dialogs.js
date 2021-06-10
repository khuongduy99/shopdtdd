//These codes takes from http://t4t5.github.io/sweetalert/
function showBasicMessage() {
    swal("Here's a message!");
}

function showWithTitleMessage() {
    swal("Here's a message!", "It's pretty, isn't it?");
}

function showSuccessMessage(mes) {
    swal(mes, "", "success");
}

function showConfirmMessage() {
    swal({
        title: "Are you sure?",
        text: "You will not be able to recover this imaginary file!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    }, function () {
        swal("Deleted!", "Your imaginary file has been deleted.", "success");
    });
}

function showConfirmDelete(ids) {
    swal({
        title: "Bạn Có Chắc Muốn Xóa?",
        text: "Bạn sẽ không thể khôi phục nếu xác nhận có.",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Có, hãy xóa nó!",
        cancelButtonText: "Không!",
        closeOnConfirm: false
    }, function () {
    	remove(ids);
    });
}

function showConfirmDeleteFile(ids) {
    swal({
        title: "Bạn Có Chắc Muốn Xóa?",
        text: "Bạn sẽ không thể khôi phục nếu xác nhận có.",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Có, hãy xóa nó!",
        cancelButtonText: "Không!",
        closeOnConfirm: false
    }, function () {
    	removeFile(ids);
    });
}

function showCancelMessage() {
    swal({
        title: "Are you sure?",
        text: "You will not be able to recover this imaginary file!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel plx!",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        if (isConfirm) {
            swal("Deleted!", "Your imaginary file has been deleted.", "success");
        } else {
            swal("Cancelled", "Your imaginary file is safe :)", "error");
        }
    });
}

function showWithCustomIconMessage() {
    swal({
        title: "Sweet!",
        text: "Here's a custom image.",
        imageUrl: "../../images/thumbs-up.png"
    });
}

function showHtmlMessage() {
    swal({
        title: "HTML <small>Title</small>!",
        text: "A custom <span style=\"color: #CC0000\">html<span> message.",
        html: true
    });
}

function showAutoCloseTimerMessage() {
    swal({
        title: "Auto close alert!",
        text: "I will close in 2 seconds.",
        timer: 2000,
        showConfirmButton: false
    });
}

function showPromptMessage() {
    swal({
        title: "An input!",
        text: "Write something interesting:",
        type: "input",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
        inputPlaceholder: "Write something"
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
            swal.showInputError("You need to write something!"); return false
        }
        swal("Nice!", "You wrote: " + inputValue, "success");
    });
}

function showAjaxLoaderMessage() {
    swal({
        title: "Ajax request example",
        text: "Submit to run ajax request",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true,
    }, function () {
        setTimeout(function () {
            swal("Ajax request finished!");
        }, 2000);
    });
}

function showAjaxBlockUser(id) {
    swal({
        title: "Khóa tài khoản",
        text: "Tài khoản sẽ bị khóa đến khi nào bạn mở khóa lại.",
        type: "warning",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Ok!",
        cancelButtonText: "Hủy!",
    }, function () {
        setTimeout(function () {
        	blockUser(id);
        }, 1000);
    });
}

function showAjaxUnBlockUser(id) {
    swal({
        title: "Mở khóa tài khoản",
        text: "Tài khoản sẽ được mở khóa",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true,
        confirmButtonColor: "#337AB7",
        confirmButtonText: "Ok!",
        cancelButtonText: "Hủy!",
    }, function () {
        setTimeout(function () {
        	unBlockUser(id);
        }, 1000);
    });
}