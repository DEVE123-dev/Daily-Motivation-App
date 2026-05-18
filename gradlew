#!/bin/sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

set -e

DIRNAME=$(cd "$(dirname "$0")" || exit; pwd -P)
cd "$DIRNAME" || exit 1

if [ ! -f "gradle/wrapper/gradle-wrapper.jar" ]; then
    echo "Error: gradle-wrapper.jar not found"
    exit 1
fi

DEFAULT_JVM_OPTS="-Xmx64m -Xms64m"

if [ -z "$JAVA_HOME" ]; then
    JAVACMD=$(command -v java)
    if [ -z "$JAVACMD" ]; then
        echo "Error: JAVA_HOME is not set and java command not found in PATH"
        exit 1
    fi
else
    JAVACMD="$JAVA_HOME/bin/java"
    if [ ! -x "$JAVACMD" ]; then
        echo "Error: JAVA_HOME is set to $JAVA_HOME but java executable not found"
        exit 1
    fi
fi

if [ -z "$GRADLE_HOME" ]; then
    GRADLE_HOME="$DIRNAME/gradle"
fi

export GRADLE_HOME
export PATH="$GRADLE_HOME/bin:$PATH"

CLASSPATH="$DIRNAME/gradle/wrapper/gradle-wrapper.jar"

exec "$JAVACMD" $DEFAULT_JVM_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
