(function(){
    'use strict' ;

    angular.module('app').factory('SearchService',SearchService);
    SearchService.$inject = ['$cookieStore', '$rootScope', '$http'];

    function SearchService($cookieStore, $rootScope, $http) {
        var serverUrl = 'http://localhost:8080/hotelReservationSystem' ;
        var service = {} ;

        service.setSearch = function(searchObj) {
            return $http.post(serverUrl + '/setSearch' , searchObj).then(function(res) {
                return res.statusText ;
            } , handleError('Error setting creds'));
        }

        service.setSearchHotels = function(hotelList) {
            console.log(hotelList) ;
            return $http.post(serverUrl + '/setSearchHotels' , hotelList).then(function(res) {
                console.log(res) ;
                return res.statusText ;
            } , handleError('Error setting creds'));
        }

        service.getSearch = function() {
            console.log('Inside get Search') ;
            return $http.get(serverUrl + '/getSearch').then(handleSuccess, handleError('No search History found'));
        }

        service.getSearchHotels = function() {
            console.log('Inside get Search Hotels') ;
            return $http.get(serverUrl + '/getSearchHotels').then(function(res) {
                console.log(res) ;
                console.log(res.data) ;
                return {
                    success : true ,
                    searchHotels : res.data
                }
            }, handleError('No search History found'));
        }

        service.clearSearch = function() {
            return $http.get(serverUrl + '/clearSearch').then(function(res){
                return res.statusText ;
            } , handleError('Error clearing search'));
        }

        service.clearSearchHotels = function() {
            return $http.get(serverUrl + '/clearSearchHotels').then(function(res){
                return res.statusText ;
            } , handleError('Error clearing search'));
        }

        function handleSuccess(res) {
            return {
                success : true ,
                search : res.data
            };
        }

        function handleError(error) {
            return function() {
                return {
                    success : false ,
                    message : error
                };
            };
        }
        return service ;
    }

})();
