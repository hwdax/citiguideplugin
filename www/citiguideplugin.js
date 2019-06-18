// Empty constructor
function CitiGuidePlugin() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
CitiGuidePlugin.prototype.show = function(flat, flon, tlat, tlon, successCallback, errorCallback) {
  var options = {};
  options.flat = flat;
  options.flon = flon;
  options.tlat = tlat;
  options.tlon = tlon;

  cordova.exec(successCallback, errorCallback, 'ToastyPlugin', 'show', [options]);
}

// Installation constructor that binds ToastyPlugin to window
CitiGuidePlugin.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.citiguidePlugin = new CitiGuidePlugin();
  return window.plugins.citiguidePlugin;
};
cordova.addConstructor(CitiGuidePlugin.install);

