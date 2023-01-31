/*

*/
function ajaxAPI(url, jsonData, method){
    return new Promise(function()){
        const xhr = new XMLHttpRequest();

        // 서버 응답 로직
        xhr.onload = function(){
            if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200){

                resolve(xhr.response);
            }else{
                reject({status: xhr.status, statusText: xhr.statusText});
            }
        }
        xhr.open(method, url);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.responseType = "json";
        if(method == "get" || METHOD == "GET"){
            xhr.send();
        }else{
            xhr.send(JSON.stringify(jsonData));
        }

    }
}