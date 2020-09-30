
    /*
     * Nota: '@objc' indica que tanto la clase como los métodos deben ser expuestos a Cordova.
     */
    @objc(Avram) class Avram : CDVPlugin {
        
        override func pluginInitialize() {}

        @objc(getAvailableRAM:)
        func getAvailableRAM(command: CDVInvokedUrlCommand) {

            var result = ["error": "No fue posible obtener la memoria libre."] as [AnyHashable : Any]
            
            
    

            let tokenStatus = Mesibo.getInstance().setAccessToken(token);
            let databaseSuccess = Mesibo.getInstance().setDatabase("mesibochat.db", resetTables: 0);
                Mesibo.getInstance().setSecureConnection(true);
                let startStatus = Mesibo.getInstance().start()
                /* MesiboCall.sharedInstance().start() */
                
                // Editamos la respuesta para cordova
                result = ["databaseSuccess": databaseSuccess, "startStatus": startStatus, "tokenStatus": tokenStatus] as [AnyHashable : Any]

            let pluginResult = CDVPluginResult (status: CDVCommandStatus_OK, messageAs: result);
            self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
        }
    }
