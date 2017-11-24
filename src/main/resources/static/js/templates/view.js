angular.module('app', [])
.controller('ctrl', function($scope, $http, $window) {
	
	var userId = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
	var url = "http://localhost:8080/user/show/" + userId;
	
    $http.get(url).
        then(function(response) {
            $scope.name = response.data.name;
        },
		function(errResponse) {
			alert(errResponse);
		}
    );

    
});