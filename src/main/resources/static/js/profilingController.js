webApp.controller('profilingController', function($scope,service, $location,$rootScope) {
	
$rootScope.selectedLink ="profiling";

	$scope.selectedTable="Sample";
	$scope.selectedTab = "advance";
	
	$scope.changetab = function(value){
		$scope.selectedTab = value;
	}
	
var renderHighChartCompletnessFinal = function(data){
		
		Highcharts.chart('completness', {

			  title: {
			            text: 'Data Completness',
			            x: -20 //center
			        },
			        
			        yAxis: {
			            title: {
			                text: 'Count'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Total Count',
			            data: data.total
			        },{
			            name: 'Final Count',
			            data: data.qualified
			        }]
			});
	}
var renderHighChartConsistencyFinal = function(data){
		
		Highcharts.chart('consistency', {

			  title: {
			            text: 'Data Consistency',
			            x: -20 //center
			        },
			        
			        yAxis: {
			            title: {
			                text: 'Count'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Total Count',
			            data: data.total
			        },{
			            name: 'Final Count',
			            data: data.qualified
			        }]
			});
	}
	
var renderHighChartConformityFinal = function(data){
		
		Highcharts.chart('conformity', {

			  title: {
			            text: 'Data Conformity',
			            x: -20 //center
			        },
			        
			        yAxis: {
			            title: {
			                text: 'Count'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Total Count',
			            data: data.total
			        },{
			            name: 'Final Count',
			            data: data.qualified
			        }]
			});
	}
var renderHighChartAccuracyFinal = function(data){
		
		Highcharts.chart('container', {

			  title: {
			            text: 'Data Accuracy',
			            x: -20 //center
			        },
			       
			        yAxis: {
			            title: {
			                text: 'Count'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Total Count',
			            data: data.total
			        },{
			            name: 'Final Count',
			            data: data.qualified
			        }]
			});
	}


	var renderHighChartAccuracy = function(){
		
		Highcharts.chart('container', {

			  title: {
			            text: 'Data Accuracy',
			            x: -20 //center
			        },
			       
			        yAxis: {
			            title: {
			                text: 'Count'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Total Count',
			            data: [0,0,0,0]
			        },{
			            name: 'Final Count',
			            data: [0,0,0,0]
			        }]
			});
	}
	
	
	
	var renderHighChartConformity = function(){
		
		Highcharts.chart('conformity', {

			  title: {
			            text: 'Data Conformity',
			            x: -20 //center
			        },
			        
			        yAxis: {
			            title: {
			                text: 'Count'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Total Count',
			            data: [0,0,0,0]
			        },{
			            name: 'Final Count',
			            data: [0,0,0,0]
			        }]
			});
	}

	
	var renderHighChartConsistency = function(){
		
		Highcharts.chart('consistency', {

			  title: {
			            text: 'Data Consistency',
			            x: -20 //center
			        },
			        
			        yAxis: {
			            title: {
			                text: 'Count'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Total Count',
			            data: [0,0,0,0]
			        },{
			            name: 'Final Count',
			            data: [0,0,0,0]
			        }]
			});
	}

	var renderHighChartCompletness = function(){
		
		Highcharts.chart('completness', {

			  title: {
			            text: 'Data Completness',
			            x: -20 //center
			        },
			        
			        yAxis: {
			            title: {
			                text: 'Count'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Total Count',
			            data: [0,0,0,0]
			        },{
			            name: 'Final Count',
			            data: [0,0,0,0]
			        }]
			});
	}
	
	
	
	
	
	
var renderHighChartAccuracyPer = function(perdata){
		
		Highcharts.chart('container_per', {

			  title: {
			            text: 'Data Accuracy',
			            x: -20 //center
			        },
			       
			        yAxis: {
			            title: {
			                text: 'Percentage'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Percentage',
			            data: perdata
			        }]
			});
	}
	
	var renderHighChartConformityPer = function(perdata){
		
		Highcharts.chart('conformity_per', {

			  title: {
			            text: 'Data Conformity',
			            x: -20 //center
			        },
			       
			        yAxis: {
			            title: {
			                text: 'Percentage'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Percentage',
			            data: perdata
			        }]
			});
	}

	
	var renderHighChartConsistencyPer = function(perdata){
		
		Highcharts.chart('consistency_per', {

			  title: {
			            text: 'Data Consistency',
			            x: -20 //center
			        },
			       
			        yAxis: {
			            title: {
			                text: 'Percentage'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Percentage',
			            data: perdata
			        }]
			});
	}

	var renderHighChartCompletnessPer = function(perdata){
		
		Highcharts.chart('completness_per', {

			  title: {
			            text: 'Data Completness',
			            x: -20 //center
			        },
			        
			        yAxis: {
			            title: {
			                text: 'Percentage'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '°C'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'Percentage',
			            data: perdata
			        }]
			});
	}
	
	$scope.rerenderPer =function(){
		renderHighChartCompletnessPer([9,8,0,4]);
		renderHighChartConformityPer([9,8,0,4]);
		renderHighChartAccuracyPer([9,8,0,4]);
		renderHighChartConsistencyPer([9,8,0,4]);
	}
	
	$scope.rerender = function(){
		setTimeout($scope.rerenderPer(),1000);
		setTimeout($scope.rerenderPer(),5000);
	}
	
	$scope.rerenderHighchart =function(){
		renderHighChartCompletness();
		renderHighChartConformity();
		renderHighChartAccuracy();
		renderHighChartConsistency();
		
	}
	$scope.getRandomArray = function(){
		var arr = []
		for(var i = 0; i<10;i++){
			arr.push($scope.generateRandom());
		}
		return arr;
	}
	if($rootScope.profilingTableName){
		setTimeout($scope.rerenderHighchart(),1000);
		setTimeout($scope.rerenderHighchart(),5000);
	}
	
	
	
	
	
	/*Stomp for socket code
	 * 
	 * */
	
	
	$scope.accuracyData = [];
	$scope.accuracyDataAxis = {
			total:[],
			qualified:[]
	}
	$scope.consistencyData = [];
	$scope.consistencyDataAxis = {
			total:[],
			qualified:[]
	}
	$scope.conformityData = [];
	$scope.conformityDataAxis = {
			total:[],
			qualified:[]
	}
	$scope.completnessData = [];
	$scope.completnessDataAxis = {
			total:[],
			qualified:[]
	}
	$scope.socketConnected = false;
	function connect() {
	    var socket = new SockJS('/dataquality');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        /*setConnected(true);*/
	    	
	    	$scope.socketConnected = true;
	    	$scope.$apply();
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/dataquality/accuracy', function (greeting) {
	            if($scope.accuracyData.length > 10){
	            	$scope.accuracyData.shift();
	            	$scope.accuracyData.push(greeting.body);
	            	
	            }
	            else{
	            	$scope.accuracyData.push(greeting.body);
	            }
	            $scope.accuracyDataAxis = {
	        			total:[],
	        			qualified:[]
	        	}
	            angular.forEach($scope.accuracyData,function(data){
	            	var parsedJson = JSON.parse(data);
	            	 $scope.accuracyDataAxis.total.push(parsedJson.totalRowCount);
	            	 $scope.accuracyDataAxis.qualified.push(parsedJson.qualifiedRowCount);
	            })
	            
	            renderHighChartAccuracyFinal($scope.accuracyDataAxis);
	            
	        });
	        
	        stompClient.subscribe('/dataquality/consistency', function (greeting) {
	        	console.log(greeting);
	        	
	        	if($scope.consistencyData.length > 10){
	            	$scope.consistencyData.shift();
	            	$scope.consistencyData.push(greeting.body);
	            	
	            }
	            else{
	            	$scope.consistencyData.push(greeting.body);
	            }
	            $scope.accuracyDataAxis = {
	        			total:[],
	        			qualified:[]
	        	}
	            angular.forEach($scope.consistencyData,function(data){
	            	var parsedJson = JSON.parse(data);
	            	 $scope.consistencyDataAxis.total.push(parsedJson.totalRowCount);
	            	 $scope.consistencyDataAxis.qualified.push(parsedJson.qualifiedRowCount);
	            })
	            
	            renderHighChartConsistencyFinal($scope.consistencyDataAxis);
	        });
	        
	        stompClient.subscribe('/dataquality/conformity', function (greeting) {
	        	console.log(greeting);
	        	
	        	if($scope.conformityData.length > 10){
	            	$scope.conformityData.shift();
	            	$scope.conformityData.push(greeting.body);
	            	
	            }
	            else{
	            	$scope.conformityData.push(greeting.body);
	            }
	            $scope.accuracyDataAxis = {
	        			total:[],
	        			qualified:[]
	        	}
	            angular.forEach($scope.conformityData,function(data){
	            	var parsedJson = JSON.parse(data);
	            	 $scope.conformityDataAxis.total.push(parsedJson.totalRowCount);
	            	 $scope.conformityDataAxis.qualified.push(parsedJson.qualifiedRowCount);
	            })
	            
	            renderHighChartConformityFinal($scope.conformityDataAxis);
	        });
	        
	        stompClient.subscribe('/dataquality/completness', function (greeting) {
	        	console.log(greeting);
	        	if($scope.completnessData.length > 10){
	            	$scope.completnessData.shift();
	            	$scope.completnessData.push(greeting.body);
	            	
	            }
	            else{
	            	$scope.completnessData.push(greeting.body);
	            }
	            $scope.completnessDataAxis = {
	        			total:[],
	        			qualified:[]
	        	}
	            angular.forEach($scope.completnessData,function(data){
	            	var parsedJson = JSON.parse(data);
	            	$scope.completnessDataAxis.total.push(parsedJson.totalRowCount);
	            	$scope.completnessDataAxis.qualified.push(parsedJson.qualifiedRowCount);
	            })
	            
	            renderHighChartCompletnessFinal($scope.completnessDataAxis);
	        });
	    });
	}

	
	function disconnect() {
	    if (stompClient !== null) {
	        stompClient.disconnect();
	        $scope.socketConnected = false;
	    }
	    /*setConnected(false);*/
	    console.log("Disconnected");
	}
	
	connect();
	
	
	$scope.connectSocket = function(){
		connect();
	}
});