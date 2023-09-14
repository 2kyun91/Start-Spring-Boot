const replyManager = (function () {
    let getAll = function (obj, cb) {
        $.getJSON("/replies/" + obj.boardId + "/" + obj.postId, cb);
    }

    let add = function (obj, cb) {
        $.ajax({
            type : "post",
            url : "/replies/" + obj.boardId + "/" + obj.postId,
            data : JSON.stringify(obj),
            dataType : "json",
            contentType : "application/json",
            success : cb,
            error : function (err) {
                console.log(err);
            }
        });
    }

    let update = function (obj, cb) {
        $.ajax({
            type : "put",
            url : "/replies/" + obj.boardId + "/" + obj.postId,
            data : JSON.stringify(obj),
            dataType : "json",
            contentType : "application/json",
            success : cb,
            error : function (err) {
                console.log(err);
            }
        });
    }

    let remove = function (obj, cb) {
        $.ajax({
            type : "delete",
            url : "/replies/" + obj.boardId + "/" + obj.postId + "/" + obj.replyId,
            dataType : "json",
            contentType : "application/json",
            success : cb,
            error : function (err) {
                console.log(err);
            }
        });
    }

    return {
        getAll : getAll,
        add : add,
        update : update,
        remove : remove
    }
})();