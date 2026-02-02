# Reels - Kotlin Multiplatform Demo App

A modern cross-platform mobile application utilizing **Kotlin Multiplatform (KMP)** and **Compose Multiplatform** to demonstrate the implementation of short video scrolling experiences (similar to Reels/TikTok) and social networking features.

## 📱 Overview

This project serves as a comprehensive demo for building performant, beautiful, and feature-rich applications on both Android and iOS from a single codebase.

**Key Objectives:**
*   Implement smooth, vertical short video scrolling ("Reels").
*   Showcase detailed views for Communities and Groups.
*   Demonstrate modern KMP architecture and best practices.

## ✨ Features

*   **Short Video Feed:** Immersive full-screen vertical scrolling for short videos.
*   **Community Hub:** detailed information, feed, and members for various communities.
*   **Group Details:** Explore group metadata, upcoming events, and discussions.
*   **Cross-Platform UI:** 100% shared UI code using Jetpack Compose Multiplatform.

## 🛠 Tech Stack

*   **Language:** [Kotlin](https://kotlinlang.org/)
*   **Core:** [Kotlin Multiplatform (KMP)](https://kotlinlang.org/docs/multiplatform.html)
*   **UI:** [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) (Material 3)
*   **Dependency Injection:** [Koin](https://insert-koin.io/)
*   **Networking:** [Ktor](https://ktor.io/)
*   **Image Loading:** [Coil](https://coil-kt.github.io/coil/)
*   **Navigation:** [Jetpack Compose Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation.html)
*   **Local Storage:** [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
*   **Permissions:** [Moko Permissions](https://github.com/icerockdev/moko-permissions)

## 🏗 Architecture

The app follows a clean architecture principle with **MVI (Model-View-Intent)** or **MVVM** patterns, ensuring separation of concerns and testability.

*   **Domain Layer:** Business logic and use cases.
*   **Data Layer:** Repositories, implementation of data sources (API, Local DB).
*   **Presentation Layer:** Compose UI and ViewModels (shared).

## 🚀 Getting Started

### Prerequisites
*   Android Studio (latest version recommended)
*   Xcode (for iOS build)
*   JDK 17+

### Backend Setup
*(Add instructions here if there is a specific backend requirement or mock server needed)*

### Android
1.  Open the project in Android Studio.
2.  Sync Gradle.
3.  Select the `androidApp` run configuration.
4.  Run on an emulator or physical device.

### iOS
1.  Open the `iosApp/iosApp.xcworkspace` in Xcode (or run via Android Studio with KMM plugin).
2.  Ensure you have a valid development team selected if testing on a real device.
3.  Run on a Simulator or iPhone.

## 📂 Project Structure

*   **/composeApp**: Shared code (Common, Android, iOS logic).
    *   `src/commonMain`: 99% of the app logic and UI.
    *   `src/androidMain`: Android-specific implementations.
    *   `src/iosMain`: iOS-specific implementations.
*   **/iosApp**: Native iOS entry point.

## 🤝 Contribution

Contributions are welcome! Please feel free to verify the `Task.md` or open issues for bugs/features.

---
*Built with ❤️ using Kotlin Multiplatform*