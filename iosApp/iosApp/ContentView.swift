import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    let componentContext: ComponentContext

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(componentContext: componentContext))
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}



