# Daily Motivation App

A beautiful, lightweight Android app built with **100% Kotlin** and **Jetpack Compose** that displays motivational quotes and lets you save your favorites.

## Features

- **Modern UI** - Built with Jetpack Compose and Material Design 3
- **Offline Support** - Works without internet using Room database
- **Favorites System** - Save and manage your favorite quotes
- **Share Functionality** - Share quotes via email, messaging apps, and more
- **Dark/Light Theme** - Automatic theme support based on system settings
- **Clean Architecture** - MVVM pattern with proper separation of concerns

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Persistence Library
- **Async**: Kotlin Coroutines & Flow
- **Minimum API Level**: 24 (Android 7.0+)
- **Target API Level**: 34 (Android 14)

## Project Structure

```
src/main/
├── kotlin/com/example/dailymotivation/
│   ├── MainActivity.kt              # App entry point and navigation
│   ├── ui/
│   │   ├── screens/
│   │   │   ├── HomeScreen.kt        # Quote display screen
│   │   │   └── FavoritesScreen.kt   # Favorites list screen
│   │   └── theme/
│   │       ├── Color.kt
│   │       ├── Theme.kt
│   │       └── Type.kt
│   ├── viewmodel/
│   │   └── QuoteViewModel.kt        # Business logic & state management
│   └── data/
│       ├── model/
│       │   └── Quote.kt
│       ├── database/
│       │   ├── QuoteEntity.kt
│       │   ├── QuoteDao.kt
│       │   └── QuoteDatabase.kt
│       └── repository/
│           └── QuoteRepository.kt   # Data layer abstraction
```

## Features Breakdown

### Home Screen
- Displays today's quote (selected by day of year)
- Shows quote text and author attribution
- Heart button to toggle favorite status
- Share button to send quote via system intent
- Fallback to random quote selection

### Favorites Screen
- Displays all saved quotes in a scrollable list
- Each quote shown in a Material Card
- Delete button to remove quotes from favorites
- Empty state message when no favorites exist

### Data Layer
- **Room Database**: Persists quotes and favorite status locally
- **DAO**: Direct access objects for database queries
- **Repository**: Abstraction layer for data access
- **Seed Data**: Pre-loaded with 10 motivational quotes

### State Management
- **StateFlow**: Reactive state management
- **ViewModel**: Survives configuration changes
- **Coroutines**: Async database operations on IO dispatcher

## Getting Started

### Prerequisites
- Android Studio (2023.1 or later)
- Android SDK 34
- Kotlin 1.9+
- Gradle 8.0+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/DEVE123-dev/Daily-Motivation-App.git
   cd Daily-Motivation-App
   ```

2. Open in Android Studio:
   - File → Open → Select the project directory
   - Wait for Gradle sync to complete

3. Build the project:
   ```bash
   ./gradlew build
   ```

4. Run on emulator or device:
   ```bash
   ./gradlew installDebug
   ```

## Usage

1. **View Today's Quote**: Open the app to see a featured quote
2. **Save to Favorites**: Tap the heart icon to save the quote
3. **Share a Quote**: Tap the share button to send the quote to others
4. **View Favorites**: Tap the "Favorites" tab to see all saved quotes
5. **Remove from Favorites**: In the Favorites tab, tap the delete icon

## Customization

### Change Theme Colors
Edit `src/main/kotlin/com/example/dailymotivation/ui/theme/Color.kt`:
```kotlin
val Purple80 = Color(0xFFD0BCFF)  // Change these color values
val PurpleGrey80 = Color(0xFFCCC2DC)
```

### Add More Quotes
Edit `src/main/kotlin/com/example/dailymotivation/data/repository/QuoteRepository.kt`:
```kotlin
private val seedQuotes = listOf(
    Quote(11, "Your new quote here.", "Author Name"),
    // Add more quotes...
)
```

## Dependencies

- androidx.core:core-ktx:1.12.0
- androidx.lifecycle:lifecycle-runtime-ktx:2.6.2
- androidx.activity:activity-compose:1.8.1
- androidx.compose.material3:material3
- androidx.room:room-runtime:2.6.1
- androidx.navigation:navigation-compose:2.7.5
- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3

## Testing

Basic test dependencies included:
- JUnit 4 for unit testing
- Espresso for UI testing
- Compose test utilities

## Learning Resources

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose/)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [ViewModel & LiveData](https://developer.android.com/topic/architecture/data-layer)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## Future Enhancements

- [ ] Daily notifications with new quotes
- [ ] API integration for fresh quotes
- [ ] Screen transitions and animations
- [ ] Quote categories/filtering
- [ ] Search functionality
- [ ] Export favorites as PDF
- [ ] Quote of the Day widget
- [ ] Cloud sync with Firebase

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues and questions, please open a GitHub Issue.

---

**Built using Kotlin and Jetpack Compose**
