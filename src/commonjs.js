function loadMenu(){
	var element = document.getElementById("div_menu");
	element.width ="100%";
	element.align ="center";
	createLink(element,"index.php","Home");
	createLink(element,"categories.php","Category");
	createLink(element,"products.php?id=","Product");
	createLink(element,"invoices.php","Invoice");
	createLink(element,"orders.php","Orders");
	createLink(element,"merge.php","Invoice&Orders");
	
	var element = document.getElementById("div_header");
	var para = document.createElement("a");
	para.href= "#";
	var node = document.createTextNode("BAGDOOM.COM");
	para.appendChild(node);
	element.appendChild(para);
}
function createLink(div,link, text){
	var para = document.createElement("a");
	para.href= link;
	var node = document.createTextNode(text);
	para.appendChild(node);
	div.appendChild(para);
}
