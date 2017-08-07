(function() {
    'use strict';

    angular
        .module('dataPoolGeneratorApp')
        .controller('DataPoolDialogController', DataPoolDialogController);

    DataPoolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DataPool'];

    function DataPoolDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DataPool) {
        var vm = this;

        vm.dataPool = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.dataPool.id !== null) {
                DataPool.update(vm.dataPool, onSaveSuccess, onSaveError);
            } else {
                DataPool.save(vm.dataPool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dataPoolGeneratorApp:dataPoolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
