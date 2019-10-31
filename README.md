# weatherApp

### Application's purposes:
1. allows to receive valid information about weather forecast in Estonia;
2. display details for current day in selected place;
3. works with internet connection and without it (exclude first luanch);
4. works in two orientations: portrait and landscape.

### Architecture, environment and tools:
1. MVP (passive view);
2. Dagger + AutoFactory for DI;
3. Room Persistance Library for data storing;
4. Kotlin coroutines for concurrency;
5. Mockito for Unit-tests (;


### Additional tasks:
1. I chose the data storing task (in case absent internet connection), because it gives application more user-friendly baheviour. Each time, when user is launching the applicaton with enabling internet connection, fresh "live" data are saving in local database and it's used in case absent internet connection;
2. Using weather icons instead text weather description gives to user visualization of current weather state;
3. Temperature info is displayed as text instead numeric values, because it was an interest task to realize for me ;).
