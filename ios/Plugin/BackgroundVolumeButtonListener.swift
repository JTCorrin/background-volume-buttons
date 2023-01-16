import Foundation

@objc public class BackgroundVolumeButtonListener: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
