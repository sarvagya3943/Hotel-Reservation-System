(function(){
    'use strict' ;
    angular.module('app')
    .controller('userProfileController', userProfileController);

    // inject dependencies
    userProfileController.$inject = ['UserService','$location', '$rootScope', 'BookingService', 'AuthenticationService'] ;

    function userProfileController(UserService, $location, $rootScope, BookingService, AuthenticationService) {
        var vm = this ;
        vm.user = null ;
        vm.reservations = null ;
        vm.cancelBooking = cancelBooking ;
        vm.getNext = getNext ;
        vm.getPrevious = getPrevious ;
        // get the user object
        UserService.isLoggedIn().then(function(response) {
            console.log(response);
            if(response.success) {
                vm.isLoggedIn = true ;
                vm.user = response.user ;
            }
            BookingService.getAllReservations(vm.user).then(function(res) {
                vm.reservations = res ;
                vm.currentPage = 1 ;
                vm.totalItems = vm.reservations.length ;
                vm.itemsPerPage = 5 ;
                vm.totalPages = Math.ceil(vm.totalItems / vm.itemsPerPage);
                vm.filteredReservations = get(vm.currentPage , vm.itemsPerPage) ;

            }) ;

        });

        function cancelBooking(id) {
            console.log(id) ;
            if(vm.currentPage > 1) id += (vm.currentPage - 1) * vm.itemsPerPage ;
            console.log(id) ;
            BookingService.cancelReservation(vm.reservations[id]).then(function(res) {
                console.log(res) ;
                location.reload();
            })
        }

        function get(current , perPage) {
            var begin = (current - 1) * perPage , end = current * perPage ;
            console.log(begin , end) ;
            if(end > vm.reservations.length - 1) end = vm.reservations.length;
            return vm.reservations.slice(begin, end) ;
        }


        function getPrevious() {
            if(vm.currentPage !== 1) {
                vm.currentPage -= 1 ;
                vm.filteredReservations = get(vm.currentPage, vm.itemsPerPage) ;
            }
        }
        function getNext() {
            console.log('Inside get next') ;
            console.log(vm.currentPage) ;
            if(vm.currentPage != vm.totalPages) {
                vm.currentPage += 1 ;
                vm.filteredReservations = get(vm.currentPage, vm.itemsPerPage) ;
            }
            console.log(vm.filteredReservations) ;
        }
        console.log(vm.user) ;
        vm.logout = function() {
            console.log('Inside logout function') ;
            AuthenticationService.clearCredentials() ;
            $location.path('/');
        }
    }
})();
