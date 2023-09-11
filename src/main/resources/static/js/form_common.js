$(function () {
    $('#summernote').summernote({
        placeholder: '내용을 입력해주세요.',
        tabsize: 2,
        height: 600,
        lang: 'ko-KR', // default: 'en-US'
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture']],
            ['view', ['codeview']]
        ],
        codeviewFilter: true,
        codeviewIframeFilter: true
    });

    // var markupStr = $('#summernote').summernote('code'); // get
    // var markupStr = 'hello world';
    // $('#summernote').summernote('code', markupStr); // set
});

function btnSaveClick() {
    let url = $(location).attr('pathname');
    let form = $('#writePostForm')[0];
    let formData = new FormData(form);
    formData.append("postContent", $('#summernote').summernote('code'));

    $.ajax({
        type: 'post',
        enctype: 'multipart/form-data',
        url: url,
        async: false,
        data: formData,
        dataType: 'json',
        processData: false,
        contentType: false,
        success: function (result) {
            location.href = "/board/detail/[(${boardId})]/" + result.postId;
        },
        error: function (request, status, error) {
            if (request.responseJSON) {
                alert(request.responseJSON.message);
            }
        }
    })
}

let dataTranster = new DataTransfer();

function setFileData(obj) {
    Array.from(obj.files)
        .forEach(file => {
            dataTranster.items.add(file);
        });
}

function addListTag(obj) {
    setFileData(obj);
    let files = Array.from(obj.files);
    files.forEach(file => {
        let htmlTag = '';
        htmlTag += `
                        <li id="${file.lastModified}" class="list-group-item list-group-item-secondary">
                            ${file.name}
                            <button data-index='${file.lastModified}' onclick="removeListTag(this);" style="margin-left: 10px;" class='btn btn-danger' type="button">X</button>
                        </li>`;
        $("#fileList").append(htmlTag);
    });
    $('#attachFiles')[0].files = dataTranster.files;
}

function removeListTag(obj) {
    if (obj.dataset.index) {
        let removeTargetId = obj.dataset.index;
        let removeTarget = $("#"+removeTargetId);
        let num = $("#fileList > li").index(removeTarget);
        dataTranster.items.remove(num);
        $('#attachFiles')[0].files = dataTranster.files;
        removeTarget.remove();
    } else {
        obj.parentNode.remove();
    }
}