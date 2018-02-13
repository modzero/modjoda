# Android Java Deserialization Vulnerability Tester

## About

This project includes Android apps that are intended as a tool to test and create Proof of Concept (PoC) exploits for Java deserialization vulnerabilities in Android. It is based on [ysoserial](https://github.com/frohoff/ysoserial) by frohoff, but targeting the Android platform.

This project was developed by Jan Girlich (@vollkorn1982) of modzero (@mod0).

## Background

Java deserialization vulnerabilities are a long known and [well researched](https://github.com/GrrrDog/Java-Deserialization-Cheat-Sheet) topic. Essentially, there is a high risk involved when deserializing untrusted, serialized Java objects. This usually leads to code execution in the context of the attacked application while deserializing malicious objects.

On Android this becomes even more problematic because of the way how data exchange between apps is handled. Intents are messages sent between Android apps to enable communication between apps. These intents can carry extra data like strings, integers, or serialized objects. Unfortunately, when any part of intent's extra data is accessed all the extra data is unpacked. This means that any serialized Java object is deserialized as soon as any extra data of an intent is used.

## Vulnerability

Any app, which

1. receives an intent and accesses any of its extra data and
1. has any Java class exploitable through a Java deserialization loaded

is vulnerable against Java deserialization attacks on Android.

In other words, except for using vulnerable dependencies (such as CommonsCollection6) and using regular functionality of Android (getting extras from an exported Intent), the app does not need to have any other security weakness.

It's remarkable how such a security-critical operation is discussed [without](https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents) [discussing](https://stackoverflow.com/questions/4233873/how-do-i-get-extra-data-from-intent-on-android) [security](https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android), not even in the [official documentation](https://developer.android.com/reference/android/content/Intent.html#getExtras()).

## Contents of this repository

This repository consist of two Android apps:

* The [attacker app](deserialization_sender/), which creates a payload and sends the malicious intent: [deserialization_sender](deserialization_sender/)
* The [vulnerable demo app](deserialization_receiver/), which is vulnerable to the CommonsCollection exploit: [deserialization_receiver](deserialization_receiver/)

For a demonstration, open the vulnerable demo app, switch to the attacker app and use the intent target "ch.modzero.intent_receiver.deserialize.pwn".

## Usage

1. Checkout, open in Android Studio and compile the [attacker app](deserialization_sender/) folder.
1. Install the resulting apk on a phone or emulator where you also install the app you want to test for deserialization vulnerabilities (or the [vulnerable demo app](deserialization_receiver/)).
1. Make sure your potentially vulnerable app is running.
1. Put the name of an intent of the potentially vulnerable app listens to into the input field on the top of the main activity (or "ch.modzero.intent_receiver.deserialize.pwn" for the [vulnerable demo app](deserialization_receiver/)).
1. Hit the send button on the bottom right corner.

The app will send all known payloads via the above entered intent. If any payload is successfully executed, the victim app will send back an intent to the attacker app to signal that it was executed. If such a payload's intent is received, the corresponding checkbox in the main activity of the attacker app gets checked.


## Status

For this app it was attempted to port all known Java deserialization exploits from ysoserial to Android. But due to limitations in the Java reflection API and other APIs missing on Android, it was only possible to port one payload so far. You can compare it with the [list of payloads](https://github.com/frohoff/ysoserial#usage) in ysoserial.

If an app accesses any intent's extra data and includes the vulnerable java library on the right hand side of the table, it is exploitable by the payload named in the left column. The app does not even need to use any functionality of the vulnerable library. Just having the classes loaded in the Java Virtual Machine is enough.

| Name | Vulnerable Java Library |
| ---- | ----------------------- |
| CommonsCollection6 | commons-collections:3.1 |

## Contribution

This app is missing more working payloads to test with. Help in porting more payloads from ysoserial or adding new ones is welcome.
