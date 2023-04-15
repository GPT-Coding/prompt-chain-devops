#!/bin/bash

# Set default JVM options
DEFAULT_JVM_OPTS="-Xmx512m"

# Use user-defined JVM options if provided
JVM_OPTS=${JVM_OPTS:-$DEFAULT_JVM_OPTS}

# Start the Java application
java $JAVA_OPTS -jar /app/app.jar