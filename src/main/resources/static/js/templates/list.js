angular.module('app', [])
.controller('ctrl', function($scope, $http, $window) {
	
    $http.get('http://localhost:8080/user/list').
        then(function(response) {
            $scope.users = response.data;
        }
    );
    
    $scope.select = function (id) {
    	$scope.selected = id;
    };
    
    $scope.view = function () {
    	$window.location.href = '/view/' + $scope.selected;
    };
    
    $scope.edit = function () {
    	$window.location.href = '/edit/' + $scope.selected;
    };
    
    $scope.delete = function () {
    	$window.location.href = '/delete/' + $scope.selected;
    };
    
});