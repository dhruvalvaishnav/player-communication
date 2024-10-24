#!/bin/bash

# Compile the project
mvn clean compile

# Run the main class
java -cp target\classes org.demo.player.Main

