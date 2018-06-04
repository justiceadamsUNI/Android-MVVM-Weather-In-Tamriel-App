#!/bin/bash

# Build docker image
docker build -t android .

# Build debug APK using new docker container
# The Kotlin compiler has some issues when the gradle dameon runs in parralel, so these flags are necessary
docker run -it --rm -v "$PWD":/application android ./gradlew assembleDebug -Dorg.gradle.daemon=false -Dorg.gradle.parallel=false -Dkotlin.incremental=false

# Move the apk file to the base directory
mv ./app/build/outputs/apk/debug/app-debug.apk .