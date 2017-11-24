angular.module('app', [])
.controller('ctrl', function($scope, $http, $window) {
	
	var userId = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
	var url = "http://localhost:8080/user/show/" + userId;
	$scope.userId = userId;
	
    $http.get(url).
        then(function(response) {
            $scope.name = response.data.name;
        },
		function(errResponse) {
			alert(errResponse);
		}
    );
    
    $scope.update = function () {
    	
    	$http({
    		url: 'http://localhost:8080/user/update',
    		method: "PUT",
    		data: { 
    			'id' : $scope.userId,
    			'name' : $scope.name 
    			}
    	})
    	.then(
    			function(response) {
    				$window.location.href = '/list';
    			},
    			function(errResponse) {
    				alert(errResponse);
    			}
    	);
    	
    };

    
});