// Empty constructor
function CitiGuidePlugin() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
CitiGuidePlugin.prototype.show = function(options, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, 'CitiGuidePlugin', 'show', [options]);
}

// Installation constructor that binds CitiGuidePlugin to window
CitiGuidePlugin.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.citiguidePlugin = new CitiGuidePlugin();
  return window.plugins.citiguidePlugin;
};
cordova.addConstructor(CitiGuidePlugin.install);

