(function(){
    'use strict';

    angular.module('app').controller('homeController', homeController);
    homeController.$inject = ['HotelService', 'UserService', '$rootScope', 'AuthenticationService', '$filter', 'SearchService'];

    function homeController(HotelService, UserService, $rootScope, AuthenticationService, $filter, SearchService) {
        var vm = this ;
        vm.allHotels = [] ;
        vm.filteredHotels = [] ;
        vm.search = {} ;
        vm.isLoggedIn = false ;
        vm.user = null ;
        vm.date = new Date(2018,8,22);
        vm.imagePath = 'static/images/hotel-1.jfif';
        vm.minDate = function() {
            return vm.date ;
        }
        function removeDuplicates(array) {
            var newArray = [] ;
            var lookup = {} ;
            var len = array.length ;
            for(var i = 0 ; i < len ; ++i) {
                lookup[array[i].hotel.hotel_id] = array[i] ;
            }
            for(i in lookup) newArray.push(lookup[i]) ;
            return newArray ;
        }
        initController() ;
        function initController() {
            UserService.isLoggedIn().then(function(response) {
                console.log(response);
                if(response.success) {
                    vm.isLoggedIn = true ;
                    vm.user = response.user ;
                }
            });
            console.log("inside user") ;
            SearchService.getSearch().then(function(response) {
                if(response.success) {
                    vm.search = response.search ;
                }
            }) ;
            SearchService.getSearchHotels().then(function(response) {
                console.log(response) ;
                if(response.success) {
                    vm.filteredHotels = removeDuplicates(response.searchHotels) ;
                    for(var i = 0 ; i < vm.filteredHotels.length ; ++i) {
                        vm.filteredHotels[i].imagePath = 'static/images/image' + vm.filteredHotels[i].hotel.hotel_id + '.jpg' ;
                    }
                    console.log(vm.filteredHotels) ;
                }
            }) ;
        }

        vm.logout = function() {
            AuthenticationService.clearCredentials() ;
            SearchService.clearSearch() ;
            SearchService.clearSearchHotels() ;
            location.reload() ;
        }

        function loadAllHotels() {
            HotelService.getAllHotels().then(
                function(hotels) {
                    vm.allHotels = hotels ;
                }
            );
        }
        vm.searchHotels = function() {
            console.log('Inside search Hotels') ;
            console.log(vm.search) ;
            vm.search.checkInStr = $filter('date')(vm.search.checkIn,'yyyy-MM-dd');
            vm.search.checkOutStr = $filter('date')(vm.search.checkOut,'yyyy-MM-dd');
            console.log(vm.allHotels) ;
            HotelService.getHotelsBySearch(vm.search).then(function(response) {
                vm.allHotels = response ;
                vm.filteredHotels = removeDuplicates(vm.allHotels) ;
                console.log(vm.filteredHotels) ;
                for(var i = 0 ; i < vm.filteredHotels.length ; ++i) {
                    vm.filteredHotels[i].imagePath = 'static/images/image' + vm.filteredHotels[i].hotel.hotel_id + '.jpg' ;
                }
                console.log(vm.filteredHotels) ;
                console.log('Searched hotels are' + vm.allHotels) ;
                SearchService.setSearchHotels(vm.allHotels) ;
                SearchService.setSearch(vm.search);
                console.log(SearchService.getSearch()) ;
                console.log(SearchService.getSearchHotels()) ;
                console.log(response) ;
                console.log(response.length) ;
                console.log(response[0]) ;
            });
            console.log(vm.search) ;
        }
    }
})();
