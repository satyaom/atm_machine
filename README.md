
# ATM Monitoring Application

---

## Prerequisites

- **ARM64 (Apple Silicon) architecture**
- **Homebrew** installed ([Install Homebrew](https://brew.sh/))
- **Java 17 JDK** (compatible with ARM64)
- **Maven** (should be installed with Homebrew)

---

## Installation Steps

### Step 1: Install Redis
   ```bash
   brew install redis
   ```

### Step 2: Install PostgreSQL 14
   ```bash
   brew install postgresql@14
   ```
- After installation, make sure to start the PostgreSQL service:
  ```bash
  brew services start postgresql@14
  ```

### Step 3: Download and Install JDK 17
Download the JDK from the official Oracle site:
[Oracle JDK 17](https://download.oracle.com/java/17/archive/jdk-17.0.12_macos-aarch64_bin.tar.gz)

- After downloading, extract and install:
  ```bash
  tar -xzf jdk-17.0.12_macos-aarch64_bin.tar.gz
  sudo mv jdk-17.0.12 /Library/Java/JavaVirtualMachines/
  ```
- Set JAVA_HOME to the JDK path:
  ```bash
  export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.0.12/Contents/Home
  export PATH=$JAVA_HOME/bin:$PATH
  ```

### Step 4: Clone the Project Repository
   ```bash
   git clone git@github.com:satyaom/atm_machine.git
   cd atm_machine
   ```

### Step 5: Build the Project with Maven
   ```bash
   mvn clean
   mvn install
   ```

### Step 6: Set Up PostgreSQL Database
- Launch the PostgreSQL shell:
  ```bash
  psql postgres
  ```
- Create a database:
  ```sql
  CREATE DATABASE spring_practice;
  ```
- Set up the user:
  ```sql
  CREATE USER postgres WITH ENCRYPTED PASSWORD 'postgres';
  ALTER ROLE postgres SUPERUSER;
  ```

### Step 7: Run the Application
- Launch the application by running the main class:
  ```bash
  mvn spring-boot:run -Dspring-boot.run.main-class=com.example.Atmmachine.AtmApplication
  ```

---

## Troubleshooting

- **Java Version Error:** Make sure `JAVA_HOME` is set to Java 17.
  ```bash
  java -version
  ```
  If the output does not show Java 17, check your environment variables.

- **Database Connection Errors:** Confirm PostgreSQL service is running.
  ```bash
  brew services start postgresql@14
  ```

- **Dependency Errors:** Run `mvn clean install` again to ensure all dependencies are correctly set up.

---

