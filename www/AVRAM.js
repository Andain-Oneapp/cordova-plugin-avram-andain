var exec = require('cordova/exec');

module.exports = {
	getAvailableRAM: function (params, success, error) {
		exec(success, error, 'AVRAM', 'getAvailableRAM', []);
	}
};