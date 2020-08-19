# NOTE: In alpha stage

# jfreecycle

Command-line utility for [Freecycle.org](https://www.freecycle.org/).

### Prerequisites

Install:
- [Java](https://openjdk.java.net/install/)
- [Maven](https://maven.apache.org/download.cgi)

## Building

```bash
> git clone https://github.com/pskenny/libjfreecycle
> cd libjfreecycle
> mvn package # package libjfreecycle
> mvn install # install to local Maven repository
> cd ..
> git clone https://github.com/pskenny/jfreecycle # Clone repo
> cd jfreecycle # Change working directory to repo
> mvn clean compile assembly:single # Generate Jar
```

## Running

### Option 1: java -jar command

```bash
> java -jar /path/to/jar/jfreecycle.jar
```

### Option 2: aliasing

Bash:

```bash
> echo "alias jfreecycle=\"java -jar /path/to/jar/jfreecycle.jar\"" >> ~/.bashrc
```
