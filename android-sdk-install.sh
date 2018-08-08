#!/usr/bin/env bash

FILENAME=android-sdk_r24.2-linux.tgz
wget http://dl.google.com/android/$FILENAME
tar zxvf $FILENAME
export ANDROID_HOME="/var/jenkins_home/android-sdk-linux"
export PATH="$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$PATH"
android update sdk --no-ui