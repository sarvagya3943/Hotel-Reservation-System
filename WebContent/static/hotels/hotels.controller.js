(function(){
    'use strict';

    angular.module('app').controller('hotelsController', hotelsController);
    hotelsController.$inject = ['HotelService'];

    function hotelsController(HotelService) {
        var vm = this ;
        vm.allHotels = [] ;

        (function initController(){
            loadAllHotels();
        })();

        function loadAllHotels() {
            HotelService.getAllHotels().then(
                function(hotels) {
                    vm.allHotels = hotels ;
                    console.log(vm.allHotels) ;
                }
            );
        }
    }
})();
