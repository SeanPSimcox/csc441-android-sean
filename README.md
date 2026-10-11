# Common Network Ports

Common Network Ports is an Android app that provides a quick reference for common networking ports
and services. Users can review common ports, add custom entries, remove entries, and view all ports
in a scrolling list.

## Screens

- Home — View known ports, add new ports, remove the last port, view the current count, open the list
screen, and access the About screen.
- List — View all ports in a scrolling list, remove individual ports, remove all ports, and return to
the home screen.
- About — Displays information about the application and its author.
 
## Input Rules

| Rule | App Response |
|------|--------------|
| Empty input | Enter a Port name |
| Less than 3 characters | Port name must be at least 3 characters |
| More than 30 characters | Keep it less than or equal to 30 characters |
| No letters present | A port entry must contain at least one service name |
| Duplicate entry | [name] already exists |

## How to Run

    1. Clone this repository.
    2. Open the project in Android Studio.
    3. Wait for Gradle synchronization.
    4. Run the application on an emulator or Android device.

## Known Limitations

Ports added by the user are not saved permanently. Closing the app or recreating it causes
user-added ports to be lost because the application does not currently use persistent storage.

The list screen also does not preserve its scroll position after rotation.