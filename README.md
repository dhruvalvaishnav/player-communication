# 🔁 Player Communication - Java Inter-Process Messaging

> A pure Java demonstration of **concurrent inter-process communication** - two Player instances exchange messages within the same JVM, showcasing thread design, synchronization, and clean object-oriented architecture.

[![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Maven](https://img.shields.io/badge/Maven-3.6%2B-C71A36?style=for-the-badge&logo=apache-maven)](https://maven.apache.org)
[![Pure Java](https://img.shields.io/badge/No_Frameworks-Pure_Java-blue?style=for-the-badge)]()
[![Concurrency](https://img.shields.io/badge/Topic-Concurrency_%26_Threading-orange?style=for-the-badge)]()

---

## 🎯 Problem Statement

Design a two-player messaging system where:

- Two `Player` instances communicate by passing messages back and forth
- The **Initiator** player sends the first message
- Each player on receiving a message **concatenates a counter** and sends it back
- The program **terminates gracefully** after the initiator sends and receives **10 messages**
- Both players run **within the same Java process** (same JVM, different threads)
- **Bonus:** Extended to support players in **separate Java processes** (different PIDs) via socket communication

---

## 🏗️ Design

```
Thread A (Initiator)              Thread B (Responder)
      │                                  │
      │──── "Hello [1]" ────────────────▶│
      │                                  │ receives, appends counter
      │◀─── "Hello [1] [1]" ────────────│
      │ receives, appends counter        │
      │──── "Hello [1] [1] [2]" ────────▶│
      │              ...                  │
      │         (10 exchanges)            │
      │──── graceful shutdown ───────────▶│
```

**Key design principles applied:**
- Each `Player` is a self-contained class with clearly documented responsibilities
- Thread synchronization via `wait()` / `notifyAll()` — no external libraries
- Clean separation between message passing logic and player state
- Graceful shutdown after stop condition is met

---

## 🛠️ Tech Stack

- **Java 11+** - pure Java, zero frameworks (as per hard requirement)
- **Maven** - build and dependency management
- **Java Concurrency** - `Thread`, `synchronized`, `wait()`, `notifyAll()`
- **Shell Script** - provided to start the program from CLI

---

## 🚀 Getting Started

### Prerequisites
- Java 11+
- Maven 3.6+

### Build & Run

```bash
# Clone the repository
git clone https://github.com/dhruvalvaishnav/player-communication.git
cd player-communication

# Build
mvn clean package

# Run (same JVM mode)
./start.sh

# Or via Maven
mvn exec:java -Dexec.mainClass="com.player.Main"
```

### Expected Output

```
Initiator -> Responder: Hello [1]
Responder -> Initiator: Hello [1] [1]
Initiator -> Responder: Hello [1] [1] [2]
Responder -> Initiator: Hello [1] [1] [2] [2]
...
[10 exchanges complete — shutting down gracefully]
```

---

## 📁 Project Structure

```
player-communication/
├── src/main/java/com/player/
│   ├── Main.java           # Entry point — wires up players and starts communication
│   ├── Player.java         # Core player class — handles send/receive and counter logic
│   ├── MessageQueue.java   # Shared message passing mechanism between threads
│   └── GameCoordinator.java # Manages stop condition and graceful shutdown
├── start.sh                # Shell script to start the program
└── pom.xml
```

---

## 🧠 What This Demonstrates

This project is a focused exercise in:

- **Thread design** - how to model concurrent actors cleanly in Java
- **Synchronization** - using `wait()`/`notifyAll()` correctly (with `while` loop, not `if`)
- **Clean OOP** - each class has a single, well-documented responsibility
- **Graceful shutdown** - controlled termination without race conditions

> 💡 In production systems (like Kafka consumers at Adobe), the same principles apply - controlled thread lifecycle, safe message handoff, and predictable shutdown behavior.

---

## 👨‍💻 Author

**Dhruval Vaishnav** - Senior Backend Engineer | Java · Distributed Systems · Concurrency

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/dhruvalvaishnav)
[![Medium](https://img.shields.io/badge/Medium-Follow-12100E?style=for-the-badge&logo=medium)](https://medium.com/@vdhruval)

---

⭐ If this helped you understand Java concurrency better, give it a star!
