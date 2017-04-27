var map = new Map();
var m = new Map();
$(function(){
	$('#demand_input_btn').click(demandAction);
	$('#page_input_btn').click(pageAction);
	findCount();
//	page(-1);
});
function demandAction(){
	console.log("demandAction");
	var station = $('#station').val();
	console.log(station);
	if(station==null||station==""){
		$('#station_error').html("请输入数字");
		return;
	}
	var reg = /[0-9]+/;
	if(!reg.test(station)){
		$('#station_error').html("请输入数字");
		return;
	}
	findCountByStation(station);
//	pageByStation(-1,station);
}

function pageAction(){
	console.log("pageAction");
	var p = $('#page_text_input').val();
	var s = $('#station').val();
	console.log("page:"+p);
	console.log("station:"+s);
	if(s==null||s==""){
		page(p-1);
	}else{
		pageByStation(p-1,s);
	}

}

function findCount(){
	console.log("findCount");
	$.ajax({
		type : "post",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		url : "real/count.do",
		async: false,
		data : {},
		success : function(result) {
			if(result.state==0){
				console.log(result);
				map = result.data;
				console.log(map);
				$('#stationCount').empty();
				$('#stationCount').html(map.stationCount);
				page(-1);
				return;
			}
			alert(result.message);
			window.parent.location.href="login.html";
		}
	});

}

function page(n){
	console.log("page");
	var pageIndex = 0;
	var pageSize = 6;
	$(function() {
		if(n>=0){
			InitTable(n);
			pageIndex = n;
		}else{
			InitTable(0);
		}   
		$("#Pagination").pagination(map.count, {
			callback: PageCallback,
			prev_text: '上一页',
			next_text: '下一页',
			items_per_page: pageSize,
			num_display_entries: 6,//连续分页主体部分分页条目数
			current_page: pageIndex,//当前页索引
			num_edge_entries: 2//两侧首尾分页条目数
		});

		//翻页调用
		function PageCallback(index, jq) {     
			console.log("+++"+jq);
			InitTable(index);
		}
		//请求数据
		function InitTable(pageIndex) {                                
			$.ajax({ 
				type: "POST",
				dataType: "json",
				url: 'real/get.do',
				data: "pageIndex=" + (pageIndex + 1) + "&pageSize=" + pageSize,
				success: function(result) {
					console.log(result);
					if(result.state==0){
						var list= new Array();
						list = result.data;
						show(list);
						return;
					}
					alert(result.message);
					window.parent.location.href="login.html";
				}
			});            
		}
	});
}

function show(list){
	console.log(list);
	var template="<tr><td>[station]</td>"+
	"<td>[Press]</td>"+
	"<td>[Battvoltage]</td>"+
	"<td>[status]</td>"+
	"<td>[tm]</td></tr>";
	var tr;
	console.log(list.length);
	$("#tbody").empty();
	for(var i=0; i<list.length; i++){
		var tbPress = list[i];
		console.log(tbPress);
		console.log(tbPress.station)
		//id name
		tr=template.replace('[station]',tbPress.station);
		tr=tr.replace('[Press]',tbPress.press);
		tr=tr.replace('[Battvoltage]',tbPress.battvoltage);
		tr=tr.replace('[status]',tbPress.status);
		tr=tr.replace('[tm]',tbPress.tm);
		$("#tbody").append(tr);//将返回的数据追加到表格
	}
}

function findCountByStation(station){
	console.log("findStationCount");
	$.ajax({
		type : "post",
		dataType : "json",
		url : "real/stationCount.do",
		async: false,
		data : {'station':station},
		success : function(result) {
			console.log("result");
			console.log(result);
			if(result.state==0){
				console.log(result.data);
				m = result.data;
				console.log("m:");
				console.log(m);
				$('#stationCount').empty();
				$('#stationCount').html(m.stationCount);
				pageByStation(-1,station);
				return;
			}else if(result.state==2){
				$('#station_error').html(result.message);
				return;
			}
			alert(result.message);
			window.parent.location.href="login.html";
		}
	});

}
function pageByStation(n,station){
	console.log("pageByStation");
	console.log(m.count);
	var pageIndex = 0;
	var pageSize = 6;
	$(function() {
		if(n>=0){
			pageIndex = n;
			InitTables(n);
		}else{
			InitTables(0);
		}
		$("#Pagination").pagination(m.count, {
			callback: PageCallback,
			prev_text: '上一页',
			next_text: '下一页',
			items_per_page: pageSize,
			num_display_entries: 6,//连续分页主体部分分页条目数
			current_page: pageIndex,//当前页索引
			num_edge_entries: 2//两侧首尾分页条目数
		});

		//翻页调用
		function PageCallback(index, jq) {     
			console.log("+++"+jq);
			InitTables(index);
		}
		//请求数据
		function InitTables(pageIndex) {                                
			$.ajax({ 
				type: "POST",
				dataType: "json",
				url: 'real/getStation.do',
				data: "pageIndex=" + (pageIndex + 1) + "&pageSize=" + pageSize + "&station="+station,
				success: function(result) {
					console.log(result);
					if(result.state==0){
						var list= new Array();
						list = result.data;
						console.log(list);
						show(list);
						return;
					}else if(result.state==2){
						$('#station_error').html(result.message);
						return;
					}
					alert(result.message);
					window.parent.location.href="login.html";
				}
			});            
		}
	});
}
