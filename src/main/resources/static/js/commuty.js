
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if (!content){
        alert("内容不能为空");
        return;
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1

        }),
        success:function (response) {
            if (response.code == 200){
                window.location.reload();
            }else {
                if (response.code == 2003){
                    var conf = confirm(response.message);
                    if (conf){
                        window.open("/");
                        // window.localStorage.setItem("closable",true);
                    }
                    
                }else {
                    alert(response.message);
                }

            }
            console.log(response);
        },
        dataType:"json"
    });

    
}
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comm = $("#comment-" + id);
    var attribute = e.getAttribute("data-collapse");
    if (attribute) {
        comm.removeClass("in");
        e.removeClass("data-collapse");
        // e.classList.remove("active");
    } else {
        comm.addClass("in");
        e.setAttribute("data-collapse", "in");
        // e.classList.add("active");

    }


}
function showselectTag() {
    $("#sele").show();
    
}
function selectTag(e) {
    var value = e.getAttribute("data-tag")
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }

    }

}