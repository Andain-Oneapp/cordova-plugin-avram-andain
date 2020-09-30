#import <Cordova/CDVPlugin.h>

@interface Avram : CDVPlugin

// Retorna la cantidad de memoria RAM disponible en MB en el momento que se llama esta función
- (void) getAvailableRAM:(CDVInvokedUrlCommand *)command;
@end