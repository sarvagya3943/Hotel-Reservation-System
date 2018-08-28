(function(){
    'use strict' ;
    angular.module('app', ['ngRoute', 'ngCookies', 'ngAnimate']).config(config).run(run);
    config.$inject = ['$routeProvider'];
    function config($routeProvider) {
        $routeProvider
            .when('/home', {
                controller : 'HomeController',
                templateUrl : 'static/home/home.view.html',
                controllerAs : 'vm'
            })
            .otherwise({ redirectTo : '/home'});
    }
})();
