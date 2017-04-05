var frame_w, frame_h;
var jsonArray = [];
var imgArr = [];
summerready=function(){
	//alert("hello");
	frame_w = window.innerWidth;
	frame_h = window.innerHeight;
	loadData(); 
	
}

//function load(callback){
function load(){
	$service.writeConfig({
        "host" : "192.168.31.140", //"10.11.65.21",//向configure中写入host键值
        "port" : "16697"         // "9898"//向configure中写入port键值
    });
    $service.callAction({
         "viewid" : "com.yonyou.ctrl.DeviceManagerController", //后台带包名的Controller名
         "action" : "load", //方法名
         	//"callback":function(){
         		//callback.call(window,null);
        	 //}
         "callback":"loadData()",
		"error" : "errorcallma()"
    });
}

function errorcallma(args){
	$alert("errorMsg: "+args);
}

function load(){
$service.callAction({
         "viewid" : "com.yonyou.ctrl.DeviceManagerController", //后台带包名的Controller名
         "action" : "getCads", //方法名
         "callback" : function(args){
         	$alert("maBack: "+args);
         	
         	//Knockout绑定
			var ViewModel = function () {};
         	var viewModel = new ViewModel();
			viewModel.data = ko.observableArray(jsonArray);
			ko.applyBindings(viewModel);
			//构造控件实例
			var listview = UM.listview("#cadList");						
			//添加控件方法
			listview.on("itemClick",function(sender,args){
				
				// 获取行 - row
				var row = args.rowIndex+1;
				alert(row);
				//这里可以处理行点击事件，参数sender即为当前列表实例对象，args对象有4个属性，即groupIndex(当前行所在分组的索引),childIndex(当前行在所在分组内的索引),rowIndex(当前行在整个列表中的行索引),$target(目标行的jquery对象)
				//alert("您点击了分组列表第" + (args.groupIndex+1) + "分组的第" + (args.childIndex+1) + "行！");
				
				clickRow(row);
			});
         	
         }, //请求回来后执行的js方法
         "error" :"error_callback()"//失败回调的js方法 
    });
		
}

function loadData(){
	 	imgArr = ["../img/cad1.png", "../img/cad2.png", "../img/cad3.jpg"];
		jsonArray = [{
							"cadName":"消防监控室电机设计图",
							"updatePName":"Henry",
							"cadCode":"XMC10011964",
							"updateTime":"17/12/22 12:22:33",
							"imgSrc" :"../img/cadBg.png"
						},{
							"cadName":"特种发动机设计图",
							"updatePName":"吉姆",
							"cadCode":"TMC10011965",
							"updateTime":"17/12/22 12:22:33",
							"imgSrc" :"../img/cadBg.png"
						},{
							"cadName":"配电室电机设计图",
							"updatePName":"大卫",
							"cadCode":"PMC10011967",
							"updateTime":"17/12/22 12:22:33",
							"imgSrc" :"../img/cadBg.png"
						}];
		var viewModel = {};
		viewModel.data = ko.observableArray(jsonArray);
		ko.applyBindings(viewModel);
	
		//构造控件实例
		var listview = UM.listview("#cadList");						
		//添加控件方法
		listview.on("itemClick",function(sender,args){
			
			// 获取行 - row
			var row = args.rowIndex+1;
			
			//这里可以处理行点击事件，参数sender即为当前列表实例对象，args对象有4个属性，即groupIndex(当前行所在分组的索引),childIndex(当前行在所在分组内的索引),rowIndex(当前行在整个列表中的行索引),$target(目标行的jquery对象)
			//alert("您点击了分组列表第" + (args.groupIndex+1) + "分组的第" + (args.childIndex+1) + "行！");
			
			clickRow(row);
		});
		
}

 function error_callback(args){
 	$alert("errorMsg: "+args);	
 }
// 行点击事件

function clickRow(row) {
	console.log("点击了第:"+row +"行");
	// 打开图片
	// var picPath = $(obj).attr("src");
	var index = row - 1;
	var picPath = imgArr[index];
	summer.openWin({
		id : "viewPic",
		url : "html/viewPic.html",
		pageParam:{
			param : {
				path : picPath
			}
		}
	});

}

// 图片点击事件
function viewPic(obj){
	var picPath = $(obj).attr("src");
	summer.openWin({
		id : "viewPic",
		url : "html/app_public/viewPic.html",
		pageParam:{
			param : {
				path : picPath
			}
		}
	});
}
