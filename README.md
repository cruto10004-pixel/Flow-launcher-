# Flow Launcher

Минималистичный Android-лаунчер с iOS-анимациями.

## Возможности
- Плавные spring-анимации открытия приложений (stiffness 300, damping 0.75)
- App Drawer с анимацией слайда снизу
- Поиск по приложениям
- Часы с датой на главном экране
- Жест свайп вверх для открытия App Drawer
- Прозрачный статус/навигейшн бар

## Сборка

```bash
./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`

## GitHub Actions

При каждом push в `main` или `develop`:
- собирает Debug и Release APK
- прогоняет lint
- загружает артефакты

## Структура

```
app/src/main/java/com/flow/launcher/
├── LauncherActivity.kt       — точка входа
├── model/AppInfo.kt          — модель приложения
├── util/AppLoader.kt         — загрузка списка приложений
├── adapter/AppGridAdapter.kt — RecyclerView адаптер
├── animation/LaunchAnimator.kt — spring + window анимации
└── ui/
    ├── home/HomeFragment.kt  — главный экран
    ├── home/SwipeUpGestureListener.kt
    ├── apps/AppDrawerFragment.kt
    └── widgets/ClockWidget.kt
```

## Установка как лаунчер

После установки APK → Настройки → Приложения по умолчанию → Лаунчер → Flow Launcher
