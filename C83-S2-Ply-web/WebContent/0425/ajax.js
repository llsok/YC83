// 第一步  创建 ajax 对象
var xmlhttp;
try {
	// 老 IE 浏览器
	xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
	try {
		// 新 IE 浏览器
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} catch (e) {
		try {
			// 非 IE 浏览器
			xmlhttp = new XMLHttpRequest();
			if (xmlhttp.overrideMimeType) {
				xmlhttp.overrideMimeType("text/xml");
			}
		} catch (e) {
			alert(e.message);
		}
	}
}

function get( url, callback ){
	ajax("get", url, callback);
}

function post( url, callback ){
	ajax("post", url, callback);
}

function ajax( method, url, callback ){
	// 定义回调函数
	xmlhttp.onreadystatechange = function(){
		// readyState 是客户端发送状态 0~4
		if (xmlhttp.readyState == 4){
			// status 是服务器端的结果码
			if (xmlhttp.status == 200) {
				callback(xmlhttp.responseText);
			}
		}
	};
	// 创建请求 true: 表示异步请求
	xmlhttp.open( method, url, true);
	if(method == 'post'){
		//如果以post方式请求，必须要添加
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
	}
	// 发送请求
	xmlhttp.send(null);
}


