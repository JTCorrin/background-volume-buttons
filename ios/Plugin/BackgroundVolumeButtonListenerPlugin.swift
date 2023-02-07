import Foundation
import Capacitor
import AVFoundation

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(BackgroundVolumeButtonListenerPlugin)
public class BackgroundVolumeButtonListenerPlugin: CAPPlugin {
    private let implementation = BackgroundVolumeButtonListener()

    var buttonPressCount = 0
    var triggerCount = 3
    var timeout = 1000
    var listenerName = ""
    var bringToForeground = true
    var resetTimer: Timer?
    
    @objc func startListening(_ call: CAPPluginCall) {
        guard let listenerName = call.options["listenerName"] as? String else {
            call.reject("Must provide an listener name")
            return
        }

        triggerCount = call.getInt("triggerCount") ?? triggerCount
        timeout = call.getInt("timeout") ?? timeout
        //listenerName = call.getString("listenerName") ?? listenerName

        NotificationCenter.default.addObserver(self, selector: #selector(volumeChanged), name: "AVSystemController_SystemVolumeDidChangeNotification", object: nil)
        call.resolve()
    }
    
    @objc func stopListening(_ call: CAPPluginCall) {
        NotificationCenter.default.removeObserver(self, name: "AVSystemController_SystemVolumeDidChangeNotification", object: nil)
        resetTimer?.invalidate()
        buttonPressCount = 0
        call.resolve()
    }
    
    @objc func volumeChanged(_ notification: Notification) {
        if let userInfo = notification.userInfo {
            let volumeChangeType = userInfo["AVSystemController_AudioVolumeChangeReasonNotificationParameter"] as! String
            if volumeChangeType == "ExplicitVolumeChange" {
                self.buttonPressCount += 1
                resetTimer?.invalidate()
                resetTimer = Timer.scheduledTimer(withTimeInterval: 1, repeats: false) { timer in
                    self.buttonPressCount = 0
                }
                
                if buttonPressCount == triggerCount {
                    self.buttonPressCount = 0
                    self.notifyListeners(listenerName, data: [:])
                }
            }
        }
    }
}
