import SwiftUI

public struct HomePage: View {

    var onFinished: () -> Void

    public init(onFinished: @escaping () -> Void) {
        self.onFinished = onFinished
    }

    public var body: some View {
        Text("Home page")
        Button("Exit") {
            onFinished()
        }
    }
}
