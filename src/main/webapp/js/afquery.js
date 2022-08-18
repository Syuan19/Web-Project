
// 自定义的一些工具方法

var Af = {}

Af.rest = function(url, data, success, error){
	if(data == null) data = {}; // 若data为null,则发送一个默认JSON{}
	
	$.ajax({
		method: 'POST' ,  
		url: url ,
		contentType: 'application/json' ,  // HTTP请求中的 Content-Type
		data : JSON.stringify(data) ,      // HTTP请求正文 
		processData : false,               // 
		dataType: 'json',                  // HTTP应答按JSON处理
		success : success ,
		error : error ,
	})	
}


