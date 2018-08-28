(function(){
    'use strict' ;

    angular.module('app').factory('HotelService',HotelService);

    HotelService.$inject = ['$http'];

    function HotelService($http) {
        var serverURL = 'http://localhost:8080/hotelReservationSystem' ;
        var service = {} ;
        service.getAllHotels = getAllHotels ;
        service.getHotelsByNameCity = function (searchAdmin){
            console.log(searchAdmin) ;
            var objj =  $http.post(serverURL + '/admin' , searchAdmin).then(handleSuccess,handleError());
            return objj;
        }
        service.addHotel = function(hotel){
            console.log("Inside Add Hotel");
            var dataObj = $http.post(serverURL + '/admin/addHotel' , hotel).then(handleSuccess,handleError());
            console.log(dataObj);
            return dataObj;
        }
        service.deleteHotel = function(hotelInfo){
        console.log("Inside Delete Hotel");
            var kk = $http.post(serverURL + '/admin/deleteHotel' , hotelInfo).then(handleSuccess('Deleted') , handleError('Not deleted')); 
            console.log(kk);
            return kk;
        }
        service.getHotelForUpdate = function(hotelId){
            console.log(hotelId);
            var objectt = $http.post(serverURL + '/update/updateHotel' , {hotelId: hotelId}).then(handleSuccess,handleError());
            console.log(objectt);
            return objectt;
        }
        service.changeRoom = function(newRoom){
            console.log(newRoom);
            return $http.post(serverURL + '/update/changeRoom' , newRoom).then(handleSuccess , handleError()) ;
        }
        function getAllHotels() {
            return $http.get(serverURL + '/hotels').then(handleSuccess,handleError('Error getting all hotels'));
        }
        service.getHotelsBySearch = function(searchObject) {
            console.log(searchObject) ;
            return $http.post(serverURL + '/search' , searchObject).then(handleSuccess , handleError()) ;
        };
        function handleSuccess(res) {
            // console.log('response : ' + res.status) ;
        	// console.log('response : ' + res.data[0].hotel) ;
            return res.data ;
        };
        function handleError(error) {
            return function() {
                return {
                    success : false ,
                    message : error
                };
            };
        };
        return service ;
    }

})();
