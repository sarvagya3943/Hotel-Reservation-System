(function(){
    'use strict' ;
    angular.module('app')
    .controller('registerController', registerController);

    // inject dependencies
    registerController.$inject = ['UserService','$location'] ;

    function registerController(UserService, $location) {
        var vm = this ;
        console.log("Here") ;
        vm.register = function() {
        	console.log("Inside controller") ;
            console.log(vm.user) ;
            console.log(vm.user.gender);
            vm.dataLoading = true ;
            UserService.create(vm.user)
            .then(function(response) {
            	console.log("response : " + response) ;
                if(response.success) {
                	console.log("UserService.create() returned Success");
                    alert('Registered successfully') ;
                    $location.path('/login') ;
                }
                else {
                    alert('An account with this email id already exists');
                }
            }) ;
        } ;
        
    }
})();
