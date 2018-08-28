(function(){
    'use strict';

    angular.module('app').controller('bookingController', bookingController);
    bookingController.$inject = ['BookingService', '$location', 'UserService', 'SearchService','$filter', 'AuthenticationService'];

    function bookingController(BookingService, $location, UserService, SearchService, $filter, AuthenticationService) {
        console.log('Here') ;
        var vm = this ;
        vm.bookingInfo = [] ;
        vm.jsonInfo = [] ;
        vm.hotelId = 0 ;
        vm.reservations = [] ;
        vm.user = null ;
        vm.search = {} ;
        vm.reserve = reserve ;
        vm.totalCost = 0 ;
        vm.isLoggedIn = false ;
        // vm.reserve = reserve ;
        vm.currentDate = $filter('date')(new Date(),'yyyy-MM-dd')
        init() ;
        function init() {
            var urlPaths = $location.path().split('/') ;
            vm.hotelId = parseInt(urlPaths[urlPaths.length - 1]) ;
            console.log(vm.hotelId) ;
            BookingService.getJsonInfo().then(function(response) {
                if(response.success) {
                    vm.jsonInfo = response.jsonInfo ;
                }
                BookingService.getBookingInfo().then(function(response) {
                    console.log(response) ;
                    if(response.success) {
                        vm.bookingInfo = response.bookingInfo ;
                    }
                    UserService.isLoggedIn().then(function(response) {
                        console.log(response);
                        if(response.success) {
                            vm.isLoggedIn = true ;
                            vm.user = response.user ;
                        }
                        SearchService.getSearch().then(function(response) {
                            if(response.success) {
                                vm.search = response.search ;
                            }
                            for(var i = 0 ; i < vm.bookingInfo.length ; ++i) {
                                vm.reservations.push({
                                    fromDate : vm.search.checkInStr ,
                                    toDate : vm.search.checkOutStr ,
                                    total_price : vm.bookingInfo[i].cost,
                                    userId : vm.user.userId ,
                                    hotelId : vm.jsonInfo[0].hotel.hotel_id ,
                                    roomCount : vm.bookingInfo[i].rooms ,
                                    roomType : vm.bookingInfo[i].type  ,
                                    status : 'active'
                                    });
                                vm.totalCost += vm.bookingInfo[i].cost ;
                            }
                        }) ;
                        if(!vm.isLoggedIn) {
                            alert('You need to be logged in to view this page') ;
                            $location.path('/login');
                        }
                        });
                });
            });
        }
        console.log(vm.reservations) ;
        function reserve() {
            console.log('Inside reserve function') ;
            if(!vm.isLoggedIn) {
                alert('You need to be logged in to move ahead') ;
                $location.path('/login') ;
            }
            else {
                BookingService.reserve(vm.reservations).then(function(response) {
                    vm.bookingStatus = response ;
                    $location.path('/');
                    console.log(vm.bookingStatus);
                    if(vm.bookingStatus === 'Created') alert('Booking confirmed') ;
                    else alert('Error booking hotels') ;
                });
            }
        }
        vm.logout = function() {
            console.log('Inside logout function') ;
            AuthenticationService.clearCredentials() ;
            $location.path('/');
        }
    }
})();
