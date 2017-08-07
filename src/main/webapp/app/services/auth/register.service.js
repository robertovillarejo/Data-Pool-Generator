(function () {
    'use strict';

    angular
        .module('dataPoolGeneratorApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
