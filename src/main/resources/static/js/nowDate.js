$(function(){
	$('.date:eq(0)').click(function(e) {
	    e.preventDefault();
	    var currentDate = new Date();
	    var sevenDaysAgo = new Date(currentDate);
	    sevenDaysAgo.setDate(currentDate.getDate() - 7);
	    var year = sevenDaysAgo.getFullYear();
	    var month = sevenDaysAgo.getMonth() + 1;
	    var day = sevenDaysAgo.getDate();
	    var yearnow = currentDate.getFullYear();
	    var monthnow = currentDate.getMonth() + 1;
	    var daynow = currentDate.getDate();
	    
	    
	    var nowbefore = year + "-" + month + "-" + day;
	    var now = yearnow + "-" + monthnow + "-" + daynow;
	    $('input[name=begin]').val(nowbefore);
	    $('input[name=end]').val(now);
	});
	
	$('.date:eq(1)').click(function(e) {
	    e.preventDefault();
	    var currentDate = new Date();
	    var fifteenDaysAgo = new Date(currentDate);
	    fifteenDaysAgo.setDate(currentDate.getDate() - 15);
	    var year = fifteenDaysAgo.getFullYear();
	    var month = fifteenDaysAgo.getMonth() + 1;
	    var day = fifteenDaysAgo.getDate();
	    var beforeNow = year + "-" + month + "-" + day;
	    var yearnow = currentDate.getFullYear();
	    var monthnow = currentDate.getMonth() + 1;
	    var daynow = currentDate.getDate();
	    var now = yearnow + "-" + monthnow + "-" + daynow;
	    $('input[name=begin]').val(beforeNow);
	    $('input[name=end]').val(now);
	});
	
	$('.date:eq(2)').click(function(e) {
	    e.preventDefault();
	    var currentDate = new Date();
	    var oneMonthAgo = new Date(currentDate);
	    var yearnow = currentDate.getFullYear();
	    var monthnow = currentDate.getMonth() + 1;
	    var daynow = currentDate.getDate();
	    var now = yearnow + "-" + monthnow + "-" + daynow;
	    oneMonthAgo.setMonth(currentDate.getMonth() - 1);
	
	    var year = oneMonthAgo.getFullYear();
	    var month = (oneMonthAgo.getMonth() + 1).toString().padStart(2, '0');
	    var day = oneMonthAgo.getDate().toString().padStart(2, '0');
	
	    var beforeNow = year + "-" + month + "-" + day;
	    $('input[name=begin]').val(beforeNow);
	    $('input[name=end]').val(now);
	});
	
	$('.date:eq(3)').click(function(e) {
	    e.preventDefault();
	    var currentDate = new Date();
	    var year = currentDate.getFullYear();
	    var month = 11; // 11월
	    var day = 1;

	    var novemberStart = year + '-' + month.toString().padStart(2, '0') + '-' + day.toString().padStart(2, '0');
	    var lastDayOfMonth = new Date(year, month, 0).getDate();
	    var novemberEnd = year + '-' + month.toString().padStart(2, '0') + '-' + lastDayOfMonth.toString().padStart(2, '0');
	    
	    $('input[name=begin]').val(novemberStart);
	    $('input[name=end]').val(novemberEnd);
	});

	
	$('.date:eq(4)').click(function(e) {
	    e.preventDefault();
	    var currentDate = new Date();
	    var year = currentDate.getFullYear();
	
	    // 10월
	    var octoberStart = year + "-10-01";
	    var octoberEnd = year + "-10-31";
	    $('input[name=begin]').val(octoberStart);
	    $('input[name=end]').val(octoberEnd);
	
	    // 9월
	    var septemberStart = year + "-09-01";
	    var septemberEnd = year + "-09-30";
	});
	$('.date:eq(5)').click(function(e) {
	    e.preventDefault();
	    var currentDate = new Date();
	    var year = currentDate.getFullYear();

	    var septemberStart = year + "-09-01";
	    var septemberEnd = year + "-09-30";
	    $('input[name=begin]').val(septemberStart);
	    $('input[name=end]').val(septemberEnd);
	});
	// 8월
	$('.date:eq(6)').click(function(e) {
	    e.preventDefault();
	    var currentDate = new Date();
	    var year = currentDate.getFullYear();
	
	    var augustStart = year + "-08-01";
	    var augustEnd = year + "-08-31";
	    $('input[name=begin]').val(augustStart);
	    $('input[name=end]').val(augustEnd);
	});
	
	// 7월
	$('.date:eq(7)').click(function(e) {
	    e.preventDefault();
	    var currentDate = new Date();
	    var year = currentDate.getFullYear();
	
	    var julyStart = year + "-07-01";
	    var julyEnd = year + "-07-31";
	    $('input[name=begin]').val(julyStart);
	    $('input[name=end]').val(julyEnd);
	});
	
	$('.btnDateConfirm').click(function(){
		var begin = $('input[name=begin]').val();
		var end = $('input[name=end]').val();
		  window.location.href = '/LotteON/my/order?begin='+begin+"&end="+end;
	})
	$('.btnPointConfirm').click(function(){
		var begin = $('input[name=begin]').val();
		var end = $('input[name=end]').val();
		  window.location.href = '/LotteON/my/point?begin='+begin+"&end="+end;
	})
})