summerready = function(){
  $summer.fixStatusBar($summer.byId('header'));
  bindEvents();
}

function openWin(){
	summer.openWin({
        "id" : 'list',
        "url" : 'html/list.html',
    });
}

function bindEvents(){
	$('.um-btn').off().on('click',function(e){
		e.preventDefault();
		openWin();	
	})
}