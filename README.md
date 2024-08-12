# FitFlexTV: Home Workout App 🏋️‍♀️📱

<img width="auto" height="200px" align="left" src="doc/main_logo.png" />

This project is a home workout application designed to provide personalized workout plans and track your fitness progress, all from the comfort of your home. 💪

**FitFlexTV** aims to make staying fit and healthy convenient and enjoyable, offering a variety of workouts tailored to your goals and preferences. With a user-friendly interface and comprehensive features, you can easily follow workout routines, monitor your progress, and stay motivated on your fitness journey.

<p align="center">
  <img src="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white" />
  <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white" />
  <img src="https://img.shields.io/badge/Material%20UI-007FFF?style=for-the-badge&logo=mui&logoColor=white" />
</p>

## Overview 🌐

## Key Features ✨

- **Personalized Workout Plans**: Get customized workout routines that suit your fitness level and goals. 📝
- **Progress Tracking**: Monitor your progress and achievements to stay motivated. 📊
- **Secure Authentication**: Keep your data safe with Firebase Authentication. 🔒
- **Real-time Data Sync**: Enjoy seamless data synchronization with Firebase Firestore. 🔄
- **Media Storage**: Store and retrieve workout media using Firebase Storage. 🗄️

## Technologies Used 🛠️

- **Kotlin**: For developing the Android application.
- **Firebase Platform**:
  - **Firestore**: For real-time database and storage.
  - **Firebase Auth**: For authentication and user management.
  - **Firebase Storage**: For storing user profile images and other media.
- **Coroutines**: For asynchronous programming and managing background tasks.
- **Clean Architecture**: For a robust and scalable architecture.
- **MVI (Model-View-Intent)**: For a unidirectional data flow and state management.
- **Jetpack Compose for TV**: For building the UI with a focus on reusability.
- **Mapper Pattern**: For converting between different data models (DTOs, BOs, etc.).

## Architecture 🏗️

### Clean Architecture

We chose Clean Architecture for the following reasons:

- **Separation of Concerns**: Clean Architecture helps keep the codebase modular and separates concerns across different layers, making the codebase easier to manage and scale.
- **Independence**: Each layer of the architecture operates independently of frameworks, UI, database, and any external agency.
- **Testability**: With well-defined boundaries and separations, unit testing becomes more straightforward and effective.

### MVI (Model-View-Intent)

MVI complements Clean Architecture by:

- **Unidirectional Data Flow**: Ensuring a predictable state management by following a unidirectional data flow.
- **State Management**: Managing the state effectively, making the UI reflect the current state of the application.
- **Reactivity**: Ensuring the UI reacts to state changes, making the application more responsive and robust.

### Repository Pattern

The repository pattern abstracts the data layer, providing a clean API for data access. This helps in managing data from multiple sources (local database, remote server) and makes the data layer more modular and testable.

### Mapper Pattern

Mappers are used to convert data between different layers of the application (e.g., from network DTOs to business objects). This ensures that each layer of the application works with its own data format, keeping the layers decoupled and easier to manage.

### Jetpack Compose for TV

We use Jetpack Compose for TV to:

- **Reusability**: Build UI components that are highly reusable across different parts of the application.
- **Declarative UI**: Create UIs in a declarative manner, making the codebase more readable and maintainable.
- **Flexibility**: Easily adapt the UI for different screen sizes and orientations, ensuring a seamless experience on TV screens.

## Firebase Platform Integration 🔥

### Firestore

- **Real-time Database**: Firestore is used to store and sync data in real-time across all clients.
- **Scalable**: Automatically scales to handle large datasets and high-traffic applications.

### Firebase Auth

- **User Authentication**: Firebase Auth is used to handle user authentication, supporting email/password sign-in, third-party providers (Google, Facebook, etc.), and anonymous sign-in.

### Firebase Storage

- **Media Storage**: Firebase Storage is used to store user profile images and other media files, ensuring fast and secure uploads and downloads.

## App Screenshots

Here are some screenshots from our app to give you a glimpse of its design and functionality.

## Project Structure 🗂️

```plaintext
.
├── data
│   ├── model
│   ├── repository
│   └── source
│       ├── local
│       └── remote
├── domain
│   ├── model
│   └── usecase
├── ui
│   ├── view
│   ├── intent
│   ├── state
│   └── reducer
├── utils
└── di

data: Contains the data layer of the application, including repositories and data sources.domain: Contains the business logic of the application, including use cases and domain models.ui: Contains the presentation layer, including views, intents, states, and reducers.utils: Contains utility classes and extensions.di: Contains dependency injection setup.Why This Architecture? 🤔Scalability: The architecture is designed to scale with the addition of new features without significant changes to the existing codebase.Maintainability: With clear separation of concerns, each module can be developed and maintained independently.Testability: By keeping business logic in ViewModels and UseCases, and using repository patterns, the code is more testable, ensuring higher code quality.Reusability: Using Jetpack Compose for TV, the UI components are highly reusable, which accelerates the development process.Getting Started 🚀To get started with FitFlexTV:Clone the repository:git clone https://github.com/yourusername/FitFlexTV.gitOpen the project in Android Studio.Sync the project with Gradle files.Run the app on an emulator or a physical device.Contributing 

🤝We welcome contributions from the community! Please fork the repository and submit a pull request with your changes.License 

📄This project is licensed under the MIT License - see the LICENSE file for details.
