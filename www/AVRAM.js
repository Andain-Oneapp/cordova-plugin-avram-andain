var exec = require('cordova/exec');

module.exports = {
	getAvailableRAM: function (success, error) {
		exec(success, error, 'Avram', 'getAvailableRAM', []);
	}
};