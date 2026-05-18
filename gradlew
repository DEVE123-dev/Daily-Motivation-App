#!/usr/bin/env sh

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

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls -ld "$PRG"
    link=`expr "$PRG" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="$(cd "$(dirname "$PRG")" && pwd)"
APP_HOME="$(cd "$(dirname "$SAVED")" && pwd)"

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='-Xmx64m -Xms64m'

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn () {
    echo "$*" >&2
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support (must be 'true' or 'false').
darwin=false
msys=false
cygwin=false
nonstop=false
case "$(uname)" in
  Darwin* )
    darwin=true
    ;;
  MINGW* )
    msys=true
    ;;
  CYGWIN* )
    cygwin=true
    ;;
  NONSTOP* )
    nonstop=true
    ;;
esac

if [ "$cygwin" = true ] -o [ "$msys" = true ] ; then
    [ -n "$JAVA_HOME" ] && JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
fi

if [ "$darwin" = true ] -a [ -z "$JAVA_HOME" ] ; then
    JAVA_HOME=`/usr/libexec/java_home`
fi

if [ -z "$JAVA_HOME" ] ; then
    die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
fi

if [ ! -x "$JAVA_HOME/bin/java" ] ; then
    die "ERROR: JAVA_HOME is set to \"$JAVA_HOME\" but there is no 'java' executable at \"$JAVA_HOME/bin/java\"."
fi

if [ -z "$JAVA_HOME" ] ; then
    javadir=`which java`
    [ -z "$javadir" ] && die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
fi

# Increase the maximum file descriptors if we can.
if [ "$darwin" = false ] -a [ "$cygwin" = false ] -a [ "$msys" = false ] ; then
    MAX_FD_LIMIT=`ulimit -H -n`
    [ "$MAX_FD_LIMIT" != 'unlimited' ] && MAX_FD=$MAX_FD_LIMIT
fi

if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "limit" ] ; then
    MAX_FD_LIMIT=`ulimit -H -n`
    [ "$MAX_FD_LIMIT" != 'unlimited' ] && [ "$MAX_FD_LIMIT" -ge $MAX_FD ] && MAX_FD=$MAX_FD_LIMIT
fi
[ "$MAX_FD" = "maximum" -o "$MAX_FD" = "limit" ] || [ $MAX_FD -lt 1024 ] && MAX_FD=1024

[ "$darwin" = true ] && [ $MAX_FD -lt 200 ] && MAX_FD=200

ulimit -n $MAX_FD
[ $? -eq 0 ] || warn "Could not set maximum file descriptor limit: $MAX_FD"

if [ -z "$GRADLE_HOME" ] ; then
    GRADLE_HOME="$APP_HOME/gradle"
fi

export GRADLE_HOME
export PATH="$GRADLE_HOME/bin:$PATH"

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to \"$JAVA_HOME\" but there is no 'java' executable in \"$JAVA_HOME/bin\"."
    fi
else
    JAVACMD="java"
    command -v java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
fi

if [ -z "$JAVA_OPTS" ] ; then
    JAVA_OPTS=$DEFAULT_JVM_OPTS
fi

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

if [ -z "$GRADLE_HOME" ] ; then
    GRADLE_HOME="$APP_HOME/gradle"
fi

exec "$JAVACMD" $DEFAULT_JVM_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
