import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    private let settings = NSUserDefaultsSettings(delegate: UserDefaults.standard)
    let componentContext: ComponentContext

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(componentContext: componentContext, settings: settings)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}



