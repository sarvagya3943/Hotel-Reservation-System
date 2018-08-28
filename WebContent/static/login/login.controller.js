(function() {
    'use strict' ;
    angular.module('app').controller('loginController',loginController) ;

    loginController.$inject = ['UserService','AuthenticationService','$location'];
    function loginController(UserService , AuthenticationService, $location) {
        var vm = this ;

        vm.user_id = 0 ;
        init() ;
        function init() {
            //reset login status
            //AuthenticationService.clearCredentials() ;
            UserService.isLoggedIn().then(function(response) {
                console.log(response);
                if(response.success) {
                    vm.isLoggedIn = true ;
                    vm.user = response.user ;
                    alert('Try logging out , you are already logged in') ;
                    $location.path('/');
                }
            });
        };
        vm.login = function() {
            console.log('Inside login function' + vm.user.email + ' ' + vm.user.password) ;
            vm.dataLoading = true ;
            AuthenticationService.login(vm.user.email, vm.user.password , function(response) {
                console.log(response) ;
                if(response.success) {
                    console.log(response.user);
                    console.log(response.message) ;
                    console.log('Login Successful') ;
                    AuthenticationService.setCredentials(response.user) ;
                    if(response.message === "admin") {
                        console.log('Inside admin ');
                        $location.path('/admin') ;
                    }
                    else {
                        // redirect to home page

                        $location.path('/') ;
                    }
                }
                else {
                    vm.dataLoading = false ;
                    alert(response.message) ;
                    console.log('Login Failed') ;
                }
            }) ;
        }
    }
})();
