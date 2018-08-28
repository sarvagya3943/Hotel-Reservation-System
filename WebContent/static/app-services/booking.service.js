(function(){
    'use strict' ;

    angular.module('app').factory('BookingService',BookingService);

    BookingService.$inject = ['$http'] ;

    function BookingService($http) {
        var serverUrl = 'http://localhost:8080/hotelReservationSystem' ;
        var jsonInfo = [] , bookingInfo = [] ;
        var service = {} ;

        service.getAllReservations = function (user) {
            console.log(user) ;
            var bla = $http.post(serverUrl + '/getBookingsByUser' , user) ;
            console.log(bla) ;
            return bla.then(handleSuccess , handleError('Error getting all reservations')) ;
        }
        service.getBookings = function (hotelId) {
            console.log(hotelId) ;
            var bla1 = $http.post(serverUrl + '/getBookings' , {hotelId: hotelId}) ;
            console.log(bla1) ;
            return bla1.then(handleSuccess , handleError('Error getting all bookings')) ;
        }
        service.reserve = function (reservations) {
            return $http.post(serverUrl + '/book' , reservations).then(function(res){
                return res.statusText ;
            } , handleError('Error making reservations'));
        }

        service.setJsonInfo = function(info) {
            console.log(info) ;
            return $http.post(serverUrl + '/setJsonInfo' , info).then(function(res) {
                console.log(res) ;
                return res.statusText ;
            } , handleError('Error setting jsonInfo'));
        }
        service.setBookingInfo = function(info) {
            console.log(info) ;
            return $http.post(serverUrl + '/setBookingInfo' , info).then(function(res) {
                console.log(res) ;
                return res.statusText ;
            } , handleError('Error setting bookingInfo'));
        }
        service.getJsonInfo = function() {
            console.log('Inside get Json Info') ;
            return $http.get(serverUrl + '/getJsonInfo').then(function(res) {
                console.log(res) ;
                console.log(res.data) ;
                return {
                    success : true ,
                    jsonInfo : res.data
                }
            }, handleError('No json Info found'));
        }
        service.getBookingInfo = function() {
            console.log('Inside get Json Info') ;
            return $http.get(serverUrl + '/getBookingInfo').then(function(res) {
                console.log(res) ;
                console.log(res.data) ;
                return {
                    success : true ,
                    bookingInfo : res.data
                }
            }, handleError('No booking History found'));
        }

        service.cancelReservation = function(reservation) {
            return $http.post(serverUrl + '/deleteBooking' , reservation).then(function(res) {
                console.log(res) ;
                return res.statusText ;
            }, handleError('Error cancelling reservation')) ;
        }
        function handleSuccess(res) {
            console.log(res.data) ;
            return res.data ;
        }
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
