webApp.controller('profilingController', function($scope,service, $location,$rootScope) {
	
$rootScope.selectedLink ="profiling";

	$scope.selectedTable="Sample";
	$scope.selectedTab = "advance";
	
	$scope.changetab = function(value){
		$scope.selectedTab = value;
	}
	
	var render = function(){
		
		var data = {
				  "name": "cluster",
				  "children": [
				    { "name": "Profiling","mcap": 360.43,"color":"green" },
				    { "name": "Completness", "mcap": 325.71,"color":"green" },
				    { "name": "Accuracy", "mcap": 183.75 ,"color":"green"},
				    { "name": "Accuracy", "mcap": 117.66 ,"color":"green"},
				    { "name": "Consistency", "mcap": 61.72 ,"color":"green"},
				    { "name": "Conformity", "mcap": 130.27 ,"color":"red"}
				  ]
				};

				var color = d3.scale.category20c();

				var treemap =
				  d3.layout.treemap()
				  // use 100 x 100 px, which we'll apply as % later
				  .size([100, 100])
				  .sticky(true)
				  .value(function(d) { return d.mcap; });

				var div = d3.select(".treemap");

				function position() {
				  this
				    .style("left", function(d) { return d.x + "%"; })
				    .style("top", function(d) { return d.y + "%"; })
				    .style("width", function(d) { return d.dx + "%"; })
				    .style("height", function(d) { return d.dy + "%"; });
				}

				function getLabel(d) {
				  return d.name;
				}

				function getColor(d) {
				  return d.color;
				}

				var node =
				  div.datum(data).selectAll(".node")
				  .data(treemap.nodes)
				  .enter().append("div")
				  .attr("class", "node")
				  .call(position)
				  .style("background", function(d) { return getColor(d) }).
				style("border","1px solid gray")
				  .text(getLabel);
		
	}
	
	render();
	
	$scope.rerender =function(){
		render();
	}
	
});