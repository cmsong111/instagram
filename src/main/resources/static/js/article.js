const handle201 = function (data) {
    alert("글이 등록되었습니다.");

    window.location.href = "/article/" + data.id;
};

const handle401 = function (data) {
    alert("로그인이 필요합니다.");
    window.location.href = "/user/login";
}

function postArticle() {
    const data = {
        title: $("#title").val(),
        content: $("#content").val()
    };

    $.ajax({
        type: "POST",
        url: "/article/api/articles",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        statusCode: {
            201: handle201,
            401: handle401
        }
    });
}