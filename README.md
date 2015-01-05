HearIT
------
HearIT is an audio infrastructure based on Patchfield which provides a simple API to implement plugins
for real-time audio processing. Plugins are unaware of each other, being all communication between
them managed by HearIT. That way, plugin developers can focus on audio processing implementation.
Plugins are connected as chain, where each plugin only have one source plugin (the plugin connected
to its input channels) and one sink plugin (the plugin connected to its output channels).

Profiles
--------
Profiles are a simple way to organize and manage plugins. Each profile contains at least one plugin
and it is identified by its name. When a profile is selected, HearIT will check if the required
plugins are installed in device and only allow their activation if all required plugins were installed.

Getting started
---------------
Each plugin is an APK built in such way, that can be automatically found and launched by HearIT.

# Build instructions

## Pre-requisites
  a. Maven must be installed and configured
  b. Java 7 must be installed
  c. Android Studio 1.0 or newer

## Build project

The first time you build this sample, you need to install the dependencies in your local maven
repository. In order to install them go to project root directory and run:

>`./gradlew installDependencies`

Once the dependencies are installed you don't have to execute this task again.

The next step is build the native code.

>`./gradlew ndkBuild`

At this point you can build and install the application.

