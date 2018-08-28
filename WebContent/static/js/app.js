(function(){
    'use strict' ;
    angular
    .module('app', ['ngRoute', 'ngCookies', 'ngAnimate', 'ngMaterial', 'ngMessages']).config(config).run(run);
    config.$inject = ['$routeProvider','$locationProvider'];
    function config($routeProvider,$locationProvider) {
        console.log("Inside");
        $routeProvider
            .when('/',{
            	controller : 'homeController',
                templateUrl : 'static/home/home.view.html',
                controllerAs : 'vm'
            })
            .when('/register' , {
                controller : 'registerController' ,
            	templateUrl : 'static/register/register.view.html' ,
                controllerAs : 'vm'
            })
            .when('/login' , {
                controller : 'loginController' ,
            	templateUrl : 'static/login/login.view.html' ,
                controllerAs : 'vm'
            })
            .when('/hotel/:id', {
                controller : 'hotelDetailsController' ,
                templateUrl : 'static/hotel/hotel.view.html' ,
                controllerAs : 'vm'
            })
            .when('/user/:id', {
                controller : 'userProfileController' ,
                templateUrl : 'static/user/profile.view.html' ,
                controllerAs : 'vm'
            })
            .when('/hotel/book/:id', {
                controller : 'bookingController' ,
                templateUrl : 'static/booking/booking.view.html' ,
                controllerAs : 'vm'
            })
            .when('/admin' , {
                controller : 'adminController' ,
                templateUrl : 'static/admin/admin.view.html' ,
                controllerAs : 'vm'
            })
             .when('/admin/addHotel' , {
                controller : 'adminController' ,
                templateUrl : 'static/admin/addHotel.view.html' ,
                controllerAs : 'vm'
            })
             .when('/updateHotel/:id' , {
                controller : 'updateController' ,
                templateUrl : 'static/update/updateHotel.view.html' ,
                controllerAs : 'vm'
            }).when('/viewBookings/:id' , {
                controller : 'viewController' ,
                templateUrl : 'static/viewBookings/viewBookings.view.html' ,
                controllerAs : 'vm'
            })
            .otherwise({
                redirectTo : '/'
            });

        console.log("Outside");
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'] ;

    function run($rootScope, $location, $cookieStore, $http) {
        $rootScope.globals = $cookieStore.get('globals') || {} ;
        if($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata ;
        }
        $rootScope.$on('$locationChangeStart', function(event, next, current) {
            //var notRestrictedPages = ['/login' , '/register', '/' , ''] ;
            var restrictedPages = [] ;
            console.log($location.path()) ;
            var isRestrictedPage = $.inArray($location.path(),restrictedPages) !== -1 ;
            var loggedIn = $rootScope.globals.currentUser ;
            if(isRestrictedPage && !loggedIn) {
                $location.path('login') ;
            }
        });
    }
})();
