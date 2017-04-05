//here is your code...

var map;
var jsonArray;
var lat, logn, mechineName, depName,updateTime;
summerready = function() {
	
	map = new AMap.Map('container', {//显示地图
		resizeEnable : true, //是否支持缩放
		zoom : 13, //缩放比例
		center : [118.50231171, 31.70860014]//[116.397428, 39.90923] //地图中心
	});

	map.plugin(["AMap.ToolBar"], function() {
		map.addControl(new AMap.ToolBar());
	});
	if (location.href.indexOf('&guide=1') !== -1) {
		map.setStatus({
			scrollWheel : false
		})
	}
	var backBtn = $summer.byId("backBtn");
	var completedBtn = $summer.byId("completedBtn");
	// 返回
	$summer.addEvt(backBtn,"click", function(){
	
	});
	
	// 完成
	$summer.addEvt(completedBtn, "click", function(){
		console.log("完成, 进行数据收集&&消息推送");
		save();
	});
	
	// 加载数据
	loadData();
	
	// 调用定位服务 -- 获取当前位置
	currentLocaton(map);
	
	// 添加目标点
		addMark(map);
	// 绘制路线
	// walking();
 	drawLine();
	
};
/* - ---------- -------- ------- ------ -------- ----- ------ ---*/
function walkingRode(){

	AMap.service('AMap.Walking',function(){//回调函数
	    //实例化Walking
	    walking= new AMap.Walking({city: '北京市'});
	    //TODO: 使用walking对象调用步行路径规划相关的功能
	})	
	AMap.service(["AMap.Walking"],function(){
		MWalk = new AMap.Walking({
    		map: map,
   			 panel: "result"
		}); 
		walking.search([118.497991, 31.629849],[118.497366, 31.628623], function(status, result){
			
		});
	});

}

function drawLine(){
	var pointArr = [[118.497991, 31.629849],[118.497366, 31.628623],[118.496273, 31.627657]];
	var poyline = drawPolyline(map, pointArr);
	poyline.setMap(map);
}
/* - ---------- -------- ------- 获取维修地点的坐标------ -------- ----- ------ ---*/
/*** 绘制路线 ***/
function walking(){

      AMap.service(["AMap.Walking"],function(){

           var drive=new AMap.Walking();

           var ptStart=new AMap.LngLat(118.497991, 31.629849);
			var secPoint = new AMap.LngLat(118.497366, 31.628623);
           var ptEnd=new AMap.LngLat(118.496273, 31.627657);

           drive.search(ptStart,secPoint,routeResult);
		drive.search(secPoint,ptEnd,routeResult);
      });
}

function routeResult(status,result){

      if(status === 'complete' && result.info.toUpperCase()=== 'OK'){

           console.debug("route ok");

           console.debug(result);

           showRoutes(result.routes);

      }else{

           console.debug("error="+result);

      }
}

function showRoutes(routes){

    $(routes).each(function(index,route){

           var arrayRoute=new Array();//all points

           console.debug("route"+index+"="+route.steps);

           $(route.steps).each(function(index,step){

                 console.debug("step"+index+"="+step.instruction+",distance="+step.distance+",path="+step.path);

                 drawPolyline(map,step.path);

                 arrayRoute=arrayRoute.concat(step.path);

           });

           var car=addMarker(map);

           console.debug("all pts="+arrayRoute);

           car.moveAlong(arrayRoute,30,null,true);//animation

           map.setFitView();

      })

}

function addMarker(map){

      var car=new AMap.Marker({

           id:"test01",

           position:new AMap.LngLat(118.497991, 31.629849),

           icon:"../img/car.png",

           autoRotation:true,

           map:map

      });

      return car;

}

function drawPolyline(map,arrayLine){

      var polyline=new AMap.Polyline({

      map:map,

      path:arrayLine,

      strokeColor:"#3366FF", //线颜色

      strokeOpacity:1, //线透明度

      strokeWeight:5, //线宽

      strokeStyle:"solid", //线样式

      strokeDasharray:[10,5] //补充线样式

      });

      return polyline;

}

/* - ---------- -------- ------- ------ -------- ----- ------ ---*/
/*** 标注 ***/
function addMark(map) {
	map.clearMap();
	//首先清除地图覆盖物
	var markers = [{//给定标记的点的集合
		icon : 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b1.png', //标注的图片，可自定义本地设置
		position : [118.497991, 31.629849] //需要标注的经纬度
	}, {
		icon : 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b2.png',
		position :[118.497366, 31.628623]
	}, {
		icon : 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b3.png',
		position :[118.496273, 31.627657]
	}];
	// 添加一些分布不均的点到地图上,地图上添加三个点标记，作为参照
	markers.forEach(function(marker) {//对标注点遍历
		new AMap.Marker({
			map : map,
			icon : marker.icon,
			position : [marker.position[0], marker.position[1]],
			offset : new AMap.Pixel(-12, -36) //偏移位置
		});
	});
}

/* - ---------- -------- ------- ------ -------- ----- ------ ---*/
/**  定位  ***/
function currentLocaton(map) {
	// 定位
	map.plugin('AMap.Geolocation', function() {//定位参数AMap.Geolocation和添加控件都写在一个数组里，与添加控件原理相同
		geolocation = new AMap.Geolocation({//下面参数可根据需要增加或减少
			enableHighAccuracy : true, //是否使用高精度定位，默认:true
			timeout : 10000, //超过10秒后停止定位，默认：无穷大
			maximumAge : 0, //定位结果缓存0毫秒，默认：0
			convert : true, //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
			showButton : true, //显示定位按钮，默认：true
			buttonPosition : 'LB', //定位按钮停靠位置，默认：'LB'，左下角
			buttonOffset : new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
			showMarker : true, //定位成功后在定位到的位置显示点标记，默认：true
			showCircle : true, //定位成功后用圆圈表示定位精度范围，默认：true
			panToLocation : true, //定位成功后将定位到的位置作为地图中心点，默认：true
			zoomToAccuracy : true //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
		});
		map.addControl(geolocation);
		geolocation.getCurrentPosition();
		//获得当前位置
		AMap.event.addListener(geolocation, 'complete', onComplete);
		//返回定位信息
		AMap.event.addListener(geolocation, 'error', onError);
		//返回定位出错信息
	});

}

//解析定位结果
function onComplete(data) {
	var str = ['定位成功'];
	str.push('经度：' + data.position.getLng());
	str.push('纬度：' + data.position.getLat());
	str.push('精度：' + data.accuracy + ' 米');
	str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
	//document.getElementById('tip').innerHTML = str.join('<br>');
}

//解析定位错误信息
function onError(data) {
	//document.getElementById('tip').innerHTML = '定位失败';
}

function loadData(){

	jsonArray = [{
			"mechineName":"北配电室C3配电柜",
			"checkDep":"电力维修部",
			"completeTime":"",
			"switchState":"0",
			"lat":"31.627762",
			"logn":"118.496971"
		},{
			"mechineName":"北配电室C3配电柜",
			"checkDep":"电力维修部",
			"completeTime":"",
			"switchState":"0",
			"lat":"31.647307",
			"logn":"118.493438"
		},{
			"mechineName":"北配电室C3配电柜",
			"checkDep":"电力维修部",
			"completeTime":"",
			"switchState":"0",
			"lat":"",
			"logn":""
		}];
	
		var viewModel = {};
		viewModel.data = ko.observableArray(jsonArray);
		ko.applyBindings(viewModel);
		$('.um-switch1').on('click',function(e){
        	e.stopPropagation();
       	})
       	
       	// 给switch 添加监听方法
	$("input[type='checkbox']").change(function(){
		
		
		 if( $(this).is(':checked')){
		 	alert("选中");
		 	var date = new Date().format("yyyy/MM/dd hh:mm:ss");
			var ele = $(this).parents(".list-row").find("p.checkTime").html(date);
		 }else{
		 	alert("没选中");
		 	var ele = $(this).parents(".list-row").find("p.checkTime").html("");
		 }
	});
       	
		//构造控件实例
		var listview = UM.listview("#sortList");						
		//添加控件方法
		listview.on("itemClick",function(sender,args){

			// 获取行 - row
			var row = args.rowIndex+1;
			alert(row);
			//这里可以处理行点击事件，参数sender即为当前列表实例对象，args对象有4个属性，即groupIndex(当前行所在分组的索引),childIndex(当前行在所在分组内的索引),rowIndex(当前行在整个列表中的行索引),$target(目标行的jquery对象)
			alert("您点击了"+ row + "行！");
			
		});
}

function save(){
	//alert("hello");
	var _array=[];
	for(var i=0;i<jsonArray.length;i++){
		if($("ul li").eq(i).find('input[type="checkbox"]').is(':checked')){
			_array.push("完成")
		}else{
			_array.push("未完成")
		}
		//return _array;
	}
	console.log(_array);
}
