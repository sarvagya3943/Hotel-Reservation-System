(function(){
	'use strict';
	angular
	.module('app')
	.controller('adminController' , adminController);
	adminController.$inject = ['$location','HotelService','UserService','AuthenticationService','$routeParams', '$rootScope' , '$filter'];
	function adminController($location , HotelService , UserService , AuthenticationService, $rootscope , $filter,$routeParams){
		var vm = this;
        vm.hoteldata = [];
        vm.hello = {};
        vm.message = {};
        vm.user = null ;
        // vm.imagePath = 'images/hotel-1.jfif' ;

		// vm.isLoggedIn = $rootScope.globals.currentUser ;
        initController() ;
        function initController() {
            UserService.isLoggedIn().then(function(response) {
                console.log(response);
                if(response.success) {
                    vm.isLoggedIn = true ;
                    vm.user = response.user ;
                }
            });

        }

        vm.logout = function() {
            AuthenticationService.clearCredentials() ;
            // SearchService.clearSearch() ;
            // SearchService.clearSearchHotels() ;
        }
		
		vm.searchAdmin = function() {
            console.log('Inside search Hotels') ;
            console.log(vm.search) ;
            HotelService.getHotelsByNameCity(vm.search).then(function(response) {
                console.log(response) ;
                vm.hoteldata = response;
            });
//            console.log(vm.searchAdmin) ;
        }

        vm.addHotel =function(){
            console.log(vm.hotel);
            vm.dataLoading = true ;
            HotelService.addHotel(vm.hotel).then(function(response){
                vm.hello = response;
                console.log(response);
                if(response.name !=undefined && response.city!=undefined){
                    console.log("Hotel Added");
                    alert(vm.hotel.name + ' in City : ' +vm.hotel.city+ ' Added!');
                    window.location.reload();
                    $location.path("/admin");
                }
                    else{
                        vm.dataLoading = false;
                    }
                });
            }


        vm.deleteHotel=function(hotelInfo){
            console.log(hotelInfo);
            HotelService.deleteHotel(hotelInfo).then(function(response){
                console.log(response);
                alert(hotelInfo.name + ' in City : ' +hotelInfo.city+ ' Deleted!');
                window.location.reload();
                $location.path("/admin");
            });
        }
	}
})();