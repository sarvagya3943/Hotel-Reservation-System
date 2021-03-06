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
		init();
		function init(){
			console.log('Maan jaa bhai');
			var urlPaths = $location.path().split('/') ;
            vm.hotelId = parseInt(urlPaths[urlPaths.length - 1]) ;
			console.log(vm.hotelId);
			 UserService.isLoggedIn().then(function(response) {
                console.log(response);
                if(response.success) {
                    vm.isLoggedIn = true ;
                    vm.user = response.user ;
                }
            });
			HotelService.getHotelForUpdate(vm.hotelId).then(function(response){
				console.log(response);
				vm.hotel = response;
			});
			


		}
			var urlPath2 = $location.path().split('/') ;
            vm.hotelId2 = parseInt(urlPath2[urlPath2.length - 1]) ;
		vm.logout = function() {
            AuthenticationService.clearCredentials() ;
            // SearchService.clearSearch() ;
            // SearchService.clearSearchHotels() ;
        }
        // vm.room.hotelId = vm.hotelId2;
        // console.log(vm.room.hotelId);
        vm.changeRoom = function(){
        	console.log('Inside change room');
        	vm.dataloading = true;
        	HotelService.changeRoom(vm.room).then(function(response){
        		console.log(response);
        		alert('Room updated');
        		$location.path('/admin') ;
        	});

        };

	}
})();