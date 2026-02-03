# Reels

Kotlin Multiplatform app — short video feed and community screens. Shared logic and UI (Compose) for Android and iOS.

---

## What’s in the app

- **Home:** Vertical full-screen reels (video/thumbnail), likes, comments, community/group chips.
- **Community:** Detail screen with banner, profile, join/share, stats (members, groups, videos), and two tabs — Groups (cards with recent posts thumbnails) and Members.

Tech: KMP, Compose Multiplatform, Ktor, Coil, Koin, Navigation.

---

## Architecture

We’ve kept **clean architecture** with clear layers so business logic, data, and UI stay separate and testable.

- **Domain:** Models and repository interfaces. No framework here — only what the feature needs (e.g. `CommunityDetails`, `CommunityRepository`).
- **Data:** Repository implementations, API calls, DTOs, and mappers (DTO → domain). All I/O and “how we get data” lives here.
- **Presentation:** ViewModels, UI models, Compose screens. ViewModels talk only to repositories and expose state; they don’t know about HTTP or JSON.

**MVVM** on top of that: ViewModel holds state and logic, UI (Compose) observes state and sends user actions back. We use `StateFlow` for state and collect it in the UI with `collectAsStateWithLifecycle`.

---

## How we handle state

We use two constructs so the UI always knows what to show and the data layer can report success or failure in a uniform way.

**1. Result in the data/domain layer — `ResultOf<D, E>`**

Repositories return `ResultOf<SuccessData, ErrorMessage>` (we use `String` for error). So the ViewModel never gets raw exceptions or HTTP details — only “success with this data” or “error with this message”. That keeps error handling consistent and avoids leaking implementation details.

**2. UI state — `AppState<S, E>`**

For the screen we use a sealed type: `Idle`, `Loading`, `Success(data)`, `Error(message)`. ViewModel updates a `StateFlow<AppState<...>>` and the UI switches on it (loading indicator, content, or error text). So every screen follows the same pattern: idle → loading → success or error.

---

## Networking and errors

We wanted one place for HTTP + parsing and one set of user-facing error messages.

- **`safeCall`:** Wraps the actual HTTP call. Catches timeout, no internet, and other failures and returns `ResultOf.Error(message)` instead of throwing. So callers (repositories) don’t deal with try-catch for network.
- **`responseToResult`:** After we get a response, we check status code (e.g. 408, 429, 5xx) and map them to fixed error messages. 2xx goes to body parsing.
- **`safeBodyParse`:** Parses JSON. If serialization fails we return a single error message instead of crashing.
- **`DataError`:** An enum of messages like “request timed out”, “check internet”, “something went wrong”, etc. All API/parsing errors are converted to these strings so the presentation layer only sees readable messages and can show them as-is (or map once in a single place if needed).

So: data layer always gives back `ResultOf<Data, String>`, and that `String` is something we can show the user. No scattered exception handling in the UI.

---

## Repositories

Each feature that needs data has a **repository interface in domain** (e.g. `CommunityRepository`) and an **implementation in data** (e.g. `CommunityRepositoryImpl`).

The impl uses a shared `HttpClient`, calls `safeCall` + `apiCall` for the request, maps response DTO to domain model, and returns `ResultOf.Success(domainModel)` or `ResultOf.Error(message)`. ViewModel then does `repository.getX().onSuccess { } .onError { }` and updates `AppState` accordingly. So the flow is: one place to change API/URL/parsing (repository impl) and one place to react (ViewModel).

---

## Parallel API calls (Community screen)

On the community detail screen we need three things: details, groups (loops), and members. We don’t want to call them one after the other — that’s slow.

So we use **coroutines**: `viewModelScope.launch` and inside that a `supervisorScope` with three `async` jobs (one per API). We `await` all three. If one fails we don’t cancel the others (that’s what `supervisorScope` is for). Each job updates its own `StateFlow` (e.g. `detailsState`, `loopsState`, `membersState`) on success or error. The UI then has three independent states — e.g. details loaded but groups still loading, or groups error while members succeeded. That way we get faster load and independent error handling without blocking the user on the slowest or failing call.

---

## Coroutines usage

- Repositories expose **suspend** functions; ViewModel calls them from `viewModelScope.launch` or inside `async`.
- No callbacks — we use suspend + `ResultOf` so flow is linear and easy to read.
- Network/parsing is already wrapped in `safeCall` / `safeBodyParse`, so we don’t need extra withContext for “switch to IO” in the repository; the HTTP client and our wrappers handle that. We use `withContext(Dispatchers.Default)` only where we do CPU work (e.g. response handling).

---

## Project structure (high level)

- **`core/`**  
  - **data/utils:** `safeCall`, `responseToResult`, `safeBodyParse`, `apiCall`, HTTP client setup, API constants.  
  - **domain/util:** `ResultOf`, `DataError`, theme option.  
  - **presentation:** `AppState`, navigation, format/parse helpers.

- **`features/`**  
  - **community:** domain (models, `CommunityRepository`), data (repository impl, DTOs, mappers), presentation (ViewModel, UI models, Compose screens).  
  - **home:** same idea — data mappers, presentation (ViewModel, screens, reel item).

- **`di/`**  
  Koin modules: HTTP client, repositories, ViewModels, etc.

- **`theme/`**  
  Colors, typography, `ReelsTheme`, `PreviewWithTheme`.

So: one feature = domain + data + presentation, with shared core for networking, state, and errors.

---

## Running the app

- **Android:** Open in Android Studio, sync Gradle, run `composeApp` on emulator or device.
- **iOS:** Open the iOS app project (e.g. `iosApp`), select team/device, run.

Backend: point to your API base URL (see `Constants` / config) and ensure the endpoints match what the app expects (feed, community details, loops, members).

---

## Summary

- **Clean architecture:** Domain (models + repo interfaces), Data (repo impl + API + mappers), Presentation (ViewModel + Compose).
- **MVVM:** ViewModel + StateFlow + AppState; UI only observes and sends events.
- **State:** `AppState` for UI (Idle/Loading/Success/Error); `ResultOf` for data layer (Success/Error with message).
- **Errors:** `DataError` + `safeCall` / `responseToResult` / `safeBodyParse` so the UI always gets a single, consistent error string.
- **Repositories:** One interface per feature in domain, one impl in data using `safeCall` + `apiCall`, returning `ResultOf`.
- **Parallel calls:** Community screen uses `supervisorScope` + `async` for details, loops, and members so they run in parallel and fail independently.

If you’re extending the app (e.g. new screen or API), follow the same flow: repository returns `ResultOf`, ViewModel maps it to `AppState`, UI reacts to `AppState`. Keeps things consistent and easy to maintain.
