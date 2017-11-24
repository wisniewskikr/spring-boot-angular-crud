angular.module('app', [])
.controller('ctrl', function($scope, $http, $window) {
    $scope.create = function () {
    	
    	$http({
    		url: 'http://localhost:8080/user/add',
    		method: "POST",
    		data: { 'name' : $scope.name }
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