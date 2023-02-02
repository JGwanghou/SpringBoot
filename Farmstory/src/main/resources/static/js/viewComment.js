/*

*/
$(function(){

    $('#btnComplete').on('click', function(e){
        e.preventDefault();

        let regip  = $('input[name=regip]').val();
        let uid    = $('input[name=uid]').val();
        let parent = $('input[name=parent]').val();
        let comment = $('#comment').val();

        console.log(regip);
        console.log(uid);
        console.log(parent);
        console.log(comment);

        let jsonData = {
            "parent":parent,
            "comment":comment,
            "regip":regip,
            "uid":uid,
        };

        $.ajax({
            url:'/Farmstory/board/writeComment',
            method:'post',
            data:jsonData,
            dataType:'json',
            success : function(data){
                console.log(data); // data.result 안나옴..
                if(data == 1){
                    alert('댓글 입력이 완료되었습니다.')
                }else{
                    alert('댓글 작성에 실패했어요.')
                }
            }
        })

    })


})