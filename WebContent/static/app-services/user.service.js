(function() {
    'use strict' ;
    angular.module('app')
    .factory('UserService', UserService);

    UserService.$inject = ['$http'] ;

    function UserService ($http) {
        var serverUrl = 'http://localhost:8080/hotelReservationSystem' ;
        var service = {} ;



        service.create = function (user) {
        	console.log("Inside create");
        	var dataObj = $http.post(serverUrl + '/register' ,user)
            .then(handleSuccess, handleError('Error creating user'));
        	console.log(dataObj) ;
        	return dataObj ;
        } ;

        service.getByEmail = function (Email) {
            console.log('inside get by email' + Email) ;
            /*var obj = $http.post(serverUrl + '/login' , {email : Email}) ;
            console.log(obj) ;*/
            return $http.post(serverUrl + '/login' , {email : Email})
            .then(handleSuccess , handleError('Error getting user by email')) ;
        }
        service.getByID = function(id) {
            console.log(id) ;
            return $http.post(serverUrl + '/getByid' , {userId : id}).then(handleSuccess , handleError('Error getting user')) ;
        }

        service.isLoggedIn = function() {
            return $http.get(serverUrl + '/getCurrentUser').then(handleSuccess, handleError('No user logged in'));
        }
        function handleSuccess(res) {
            console.log('res : ' + res.data);
        	console.log('res.status : ' + res.status);
            return {
                success : true ,
                user : res.data 
            };
        };

        function handleError(error) {
            return function() {
                return {
                    success : false ,
                    message : error
                };
            };
        };
        //service.create = create ;
        return service ;
    };
})();
