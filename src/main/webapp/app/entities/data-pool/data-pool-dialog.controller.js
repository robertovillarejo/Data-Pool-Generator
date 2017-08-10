(function () {
    'use strict';

    angular
        .module('dataPoolGeneratorApp')
        .controller('DataPoolDialogController', DataPoolDialogController);

    DataPoolDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DataPool'];

    function DataPoolDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, DataPool) {
        var vm = this;

        vm.dataPool = entity;
        vm.clear = clear;
        vm.save = save;
        vm.addDataType = addDataType;
        vm.removeDataType = removeDataType;
        vm.toJson = toJson;
        vm.addSourceData = addSourceData;
        vm.dataTypes = [
            "NAME",
            "LAST_NAME",
            "EMAIL",
            "LOREM",
            "COMPANY",
            "CITY",
            "COUNTRY",
            "STATE",
            "ZIP_CODE",
            "COLOR",
            "DEPARTMENT",
            "PRICE", 
            "PROFESSION", 
            "PASSWORD", 
            "FULL_NAME", 
            "PHONE_NUMBER", 
            "UNIVERSITY"
        ];

        if (vm.dataPool.id == null) {
            vm.dataPool.request = {
                rowsNumber: 1,
                repeatTimes: 1,
                addDataTypes: [],
                repeatDataTypes: []
            };
        }

        vm.columns = { dataType: {} };
        vm.repeat = { dataType: {} };

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            vm.isSaving = true;
            if (vm.dataPool.id !== null) {
                DataPool.update(vm.dataPool, onSaveSuccess, onSaveError);
            } else {
                DataPool.save(vm.dataPool, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('dataPoolGeneratorApp:dataPoolUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }

        function addDataType(place) {
            if ("columns" === place) {
                vm.dataPool.request.addDataTypes.push(vm.columns.dataType);
                vm.columns.dataType = {};
            } else {
                vm.dataPool.request.repeatDataTypes.push(vm.repeat.dataType);
                vm.repeat.dataType = {};
            }
            
        }

        function removeDataType(dataType, place) {
            if ("columns" === place) {
                var index = vm.dataPool.request.addDataTypes.indexOf(dataType);
                vm.dataPool.request.addDataTypes.splice(index, 1);
            }
            if ("sourceData" === place) {
                var index = vm.dataPool.sourceData.indexOf(dataType);
                vm.dataPool.sourceData.splice(index, 1);
            }
            if ("repeat" === place) {
                var index = vm.dataPool.request.repeatDataTypes.indexOf(dataType);
                vm.dataPool.request.repeatDataTypes.splice(index, 1);
            }
        }

        function toJson() {
            var config = {
                delimiter: "",	// auto-detect
                newline: "",	// auto-detect
                quoteChar: '"',
                header: true,
                dynamicTyping: false,
                preview: 0,
                encoding: "windows-1250",
                worker: false,
                comments: false,
                step: undefined,
                complete: addSourceData,
                error: undefined,
                download: false,
                skipEmptyLines: false,
                chunk: undefined,
                fastMode: undefined,
                beforeFirstChunk: undefined,
                withCredentials: undefined
            }
            var fileElement = $("#field_data_source");
            Papa.parse(fileElement[0].files[0], config);
        }

        function addSourceData(results) {
            var columns = {};
            var headers = Object.keys(results.data[0]);
            //Create arrays for store the data with header as key
            headers.forEach(function (header) {
                columns[header] = [];
            });
            //Store data in arrays
            results.data.forEach(function (element) {
                headers.forEach(function (header) {
                    var data = element[header];
                    if (data !== null && data !== undefined && data !== "") {
                        columns[header].push(data);
                    }
                });
            });
            var dataColumns = [];
            //Convert data to DataColumn Array
            headers.forEach(function (header) {
                var dataColumn = {};
                dataColumn.header = header;
                dataColumn.data = columns[header];
                dataColumns.push(dataColumn);
            });
            vm.dataPool.sourceData = dataColumns;
        }
    }
})();
