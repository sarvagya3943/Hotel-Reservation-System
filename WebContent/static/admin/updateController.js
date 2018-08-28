(function(){
	'use strict';
	angular.module('app')
	.controller('updateController' , updateController);
	updateController.$inject = ['$location','HotelService','UserService','AuthenticationService','$routeParams', '$rootScope' , '$filter'];
	function updateController($location,HotelService,UserService,AuthenticationService,$routeParams, $rootScope , $filter){
		var vm = this;
		vm.allHotelData = [];
		vm.hotel= [];
		vm.user = null;
		function init(){
			UserService.isLoggedIn().then(function(response) {
                console.log(response);
                if(response.success) {
                    vm.isLoggedIn = true ;
                    vm.user = response.user ;
                }
            });
			var urlPaths = $location.path().split('/') ;
            vm.hotelId = parseInt(urlPaths[urlPaths.length - 1]) ;
			console.log(vm.hotelId);
			 
			HotelService.getHotelForUpdate(vm.hotelId).then(function(response){
				console.log(response);
				vm.hotel = response;
			});
			


		}
		vm.logout = function() {
            AuthenticationService.clearCredentials() ;
            // SearchService.clearSearch() ;
            // SearchService.clearSearchHotels() ;
        }

	}
})();