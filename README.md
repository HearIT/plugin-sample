HearIT plugin sample
====================
This sample exemplifies how to create a plugin for HearIT.

HearIT
------
HearIT is an audio infrastructure based on [Patchfield](https://github.com/google/patchfield) which provides a simple
API to implement plugins for real-time and low-latency audio processing. **Low latency is only possible in devices that
support that feature.** Plugins are unaware of each other, being all communication between them managed by HearIT. That
way, plugin developers can focus on audio processing implementation. Plugins are connected as chain, where each plugin
only have one source plugin (the plugin connected to its input channels) and one sink plugin (the plugin connected to
its output channels).

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

Plugin architecture
-------------------
Plugins are composed by three components:

* **Plugin Broadcast Receiver:** in order to be found by HearIT, plugins need to provide a Broadcast Receiver, that must
be registered in AndroidManifest.xml and listen actions `info.hearit.action.LOAD_PLUGIN` (to start plugin) and
`info.hearit.action.STOP_PLUGIN` to stop plugin.
* **Plugin Audio Module:** it is this component that has access to audio stream and where the audio processing will take
place.
* **Plugin Service:** the service where the audio module will run.

In order to make it easy for developers create plugins, HearIT provides a library, pluginsLib, which has abstract classes
for each one of this three components. That way, tasks related with plugin lifecycle (like loading and stopping) are
implemented in library, and developers can focus on audio processing. Plugins developers must extend that three classes,
as exemplified in this sample, and implement that audio processing function in native code.

Android Manifest
----------------
The following list enumerates some of the tags present in AndroidManifest.xml and a brief description to justify their
presence in manifest.

* `<uses-permission android:name="com.noisepages.nettoyeur.patchfield.service.USE_PATCHFIELD"/>`: as mentioned above,
HearIT is built on top of Patchfield, that requires the `com.noisepages.nettoyeur.patchfield.service.USE_PATCHFIELD`
permission.
* `<activity (...) android:excludeFromRecents="true">`: from the user point of view, plugins are deeply integrated with
HearIT main application and behave like they are only one application, and so, it does not make sense show plugins in
list of recent applications. For the same reason `<category android:name="android.intent.category.LAUNCHER"/>` tag is
missing from `<intent-filter>`, so plugins does not appear in Android launcher and cannot be launched individually.
* `<service android:name="com.noisepages.nettoyeur.patchfield.service.PatchfieldService" (...) />`: Patchfield runs in a
service that requires `com.noisepages.nettoyeur.patchfield.service.USE_PATCHFIELD` permissions.