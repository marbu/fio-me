# Fio API client for Java ME

This is simple
[Java ME](https://en.wikipedia.org/wiki/Java_Platform,_Micro_Edition) client
application for
[Fio Bank API](http://www.fio.cz/bank-services/internetbanking-api).
The purpose of this client is to provide quick status of given account,
including total balance and details of recent transactions.

The project is in development phase, nothing actually useful has been
implemented so far.

## Development

Build process is managed via [maven](http://maven.apache.org/). To build
the project, run:

	$ mvn clean package

This default build uses
[Microemulator](https://sourceforge.net/projects/microemulator/)
API implementation to simplify the build process, but it's only
[MIDP-2.0](http://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/)
implementation.

### How to run it in the Microemulator

Just use `exec:java` goal after you've successfully built the project:

	$ mvn exec:java

## Acknowledgement

Project structure, maven build configuration and other Java plumbing related
details (including build instructions in this README file) are based on code of
[topt-me](https://github.com/kwart/totp-me) project.

## License

Distributed under the terms of the
[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
