![GitHub](https://img.shields.io/github/license/pskenny/jfreecycle)

# ♻️ jfreecycle

__NOTE:__ In alpha stage, do not use. <br />
__NOTE:__ Be cool. Don't blast freecycle with requests.

Command-line utility for [freecycle.org](https://www.freecycle.org/).

[![asciicast](https://asciinema.org/a/JMUnHCrE6SzTuCPYeZ64JtE8k.svg)](https://asciinema.org/a/JMUnHCrE6SzTuCPYeZ64JtE8k)

## Table Of Contents

- [♻️ jfreecycle](#️-jfreecycle)
  - [Table Of Contents](#table-of-contents)
  - [🏗️ Building](#️-building)
    - [Prerequisites](#prerequisites)
    - [Build Jar](#build-jar)
  - [🚀 Running](#-running)
    - [Option 1: java -jar command](#option-1-java--jar-command)
    - [Option 2: aliasing](#option-2-aliasing)
  - [🐾 Usage](#-usage)

## 🏗️ Building

### Prerequisites

- [Java](https://openjdk.java.net/install/)
- [Maven](https://maven.apache.org/download.cgi)

### Build Jar

```bash
> git clone https://github.com/pskenny/libjfreecycle # Clone freecycle library
> cd libjfreecycle
> mvn package # Package libjfreecycle
> mvn install # Install to local Maven repository
> cd ..
> git clone https://github.com/pskenny/jfreecycle # Clone jfreecycle
> cd jfreecycle
> mvn clean compile assembly:single # Generate jar
> java -jar ./target/jfreecycle-0.1-jar-with-dependencies.jar # Run jar
```

_Note:_ jar is built in `target/` folder. 

## 🚀 Running

### Option 1: java -jar command

```bash
java -jar /path/to/jar/jfreecycle.jar
```

### Option 2: aliasing

Bash:

```bash
echo "alias jfreecycle=\"java -jar /path/to/jar/jfreecycle.jar\"" >> ~/.bashrc
```

Fish:

```bash
echo "alias jfreecycle=\"java -jar /path/to/jar/jfreecycle.jar\"" >> ~/.config/fish/config.fish
```

## 🐾 Usage

```bash
# Display hundred most recent posts from GalwayIE group
jfreecycle GalwayIE

# Display hundred most recent offer posts from GalwayIE group
jfreecycle -t offer GalwayIE

# Display hundred most recent wanted posts from GalwayIE group
jfreecycle -t wanted GalwayIE
```
