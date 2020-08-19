# NOTE: In alpha stage

# jfreecycle

Command-line utility for [Freecycle.org](https://www.freecycle.org/).

### Prerequisites

- [Java](https://openjdk.java.net/install/)
- [Maven](https://maven.apache.org/download.cgi)

## Building

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

Note: jar is built in `target/` folder. 

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

Fish:

```bash
> echo "alias jfreecycle=\"java -jar /path/to/jar/jfreecycle.jar\"" >> ~/.config/fish/fish.config
```

## Usage

```bash
# Display hundred most recent posts from GalwayIE group
jfreecycle GalwayIE

# Display hundred most recent offer posts from GalwayIE group
jfreecycle -t offer GalwayIE

# Display hundred most recent wanted posts from GalwayIE group
jfreecycle -t wanted GalwayIE
```
