(function(){
    'use strict';

    angular.module('app').controller('hotelDetailsController', hotelDetailsController);
    hotelDetailsController.$inject = ['SearchService', '$location', 'BookingService', '$routeParams', 'UserService', 'AuthenticationService'];

    function hotelDetailsController(SearchService, $location, BookingService, $routeParams, UserService, AuthenticationService) {
        var vm = this ;
        /* Remember to put validations for all this , boring work */
        console.log("Here") ;
        vm.bookingInfo = [] ;
        vm.searchHotels = [] ;
        vm.jsonInfo = [];
        vm.passBookingInfo = passBookingInfo ;
        init() ;
        function init() {
            //console.log(SearchService.getSearchHotels()) ;
            var urlPaths = $location.path().split('/') ;
            vm.hotelId = parseInt(urlPaths[urlPaths.length - 1]) ;
            //vm.hotelId = $routeParams.id ;
            console.log(vm.hotelId) ;
            UserService.isLoggedIn().then(function(response) {
                console.log(response);
                if(response.success) {
                    vm.isLoggedIn = true ;
                    vm.user = response.user ;
                }
            });
            SearchService.getSearchHotels().then(function(response) {
                console.log(response) ;
                if(response.success) {
                    vm.searchHotels = response.searchHotels ;
                }
                vm.jsonInfo = vm.searchHotels.filter(function(elem) {
                    return elem.hotel.hotel_id === vm.hotelId ;
                }) ;
                BookingService.getBookingInfo().then(function(response) {
                    console.log(response) ;
                    if(response.success) {
                        vm.bookingInfo = response.bookingInfo ;
                    }
                    else {
                        var len = vm.jsonInfo.length ;
                        for(var i = 0 ; i < len ; ++i) vm.bookingInfo.push({
                            type : vm.jsonInfo[i].roomType.type ,
                            rooms : 0 ,
                            guests : 0 ,
                            cost : 0
                        }) ;
                    }
                    vm.getCost = function(i) {
                        console.log(i) ;
                        var res = 0 ;
                        res += vm.bookingInfo[i].rooms * vm.jsonInfo[i].roomType.cost ;
                        var guests = vm.bookingInfo[i].guests - vm.bookingInfo[i].rooms * vm.jsonInfo[i].roomType.count;
                        if(guests > 0) {
                            res += vm.bookingInfo[i].guests * vm.jsonInfo[i].roomType.extraGuestPrice ;
                        }
                        return res ;
                    }
                    vm.getTotalCost = function() {
                        var i = 0 , cost = 0 , len = vm.jsonInfo.length ;
                        while(i < len) {
                            vm.bookingInfo[i].cost = vm.getCost(i) ;
                            cost += vm.bookingInfo[i].cost
                            i += 1 ;
                        }
                        console.log(vm.bookingInfo) ;
                        return cost;
                    }
                });


            }) ;

        }
        function bla() {
            console.log('Here') ;
        }
        function passBookingInfo() {
            BookingService.setBookingInfo(vm.bookingInfo) ;
            BookingService.setJsonInfo(vm.jsonInfo) ;
        }
        vm.logout = function() {
            console.log('Inside logout function') ;
            AuthenticationService.clearCredentials() ;
            $location.path('/');
        }
    }
})();
