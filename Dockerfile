FROM openjdk:8

# This file is based entirely off the following blog post by user Elye
# https://medium.com/@elye.project/intro-to-docker-building-android-app-cb7fb1b97602
# Without an official dockerhub image, I was forced to replicate it and slightly tweek it
# to my liking.

# Environment variables setup
ENV SDK_URL="https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip" \
    ANDROID_HOME="/usr/local/android-sdk" \
    ANDROID_VERSION=26 \
    ANDROID_BUILD_TOOLS_VERSION=26.0.2

# Download Android SDK
RUN mkdir "$ANDROID_HOME" .android \
    && cd "$ANDROID_HOME" \
    && curl -o sdk.zip $SDK_URL \
    && unzip sdk.zip \
    && rm sdk.zip \
    && yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses

# Ensure gradle download finishes by creating the directory it will download items to
RUN touch ~/.android/repositories.cfg

# Install Android Build Tool and Libraries
RUN $ANDROID_HOME/tools/bin/sdkmanager --update
RUN $ANDROID_HOME/tools/bin/sdkmanager "build-tools;${ANDROID_BUILD_TOOLS_VERSION}" \
    "platforms;android-${ANDROID_VERSION}" \
    "platform-tools"

# Set working directory
RUN mkdir /application
WORKDIR /application