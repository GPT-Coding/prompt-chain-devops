# Set the base image to the official OpenJDK image
FROM openjdk:11

# Set the working directory
WORKDIR /app

# Copy the shell script for running the Java application into the container
COPY run-java.sh /app

# Copy the Java application jar file into the container
COPY ${jar_file} /app/app.jar

# Expose port 8080
EXPOSE 8080

# Set the entry point for the container to be the shell script
ENTRYPOINT ["/bin/sh", "/app/run-java.sh"]
