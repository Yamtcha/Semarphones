# Makefile for Data Structures Hash Tables Assignments
SRCDIR = src
BINDIR = bin
DOCDIR = doc
JAVAC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR)

vpath %.java $(SRCDIR)/
vpath %.class $(BINDIR)/

# define general build rule for java sources
.SUFFIXES:  .java  .class

.java.class:
	$(JAVAC)  $(JFLAGS)  $<

#default rule - will be invoked by make

all: Simulator.class Taxi.class Person.class  Trips.class  Clock.class Timer.class 

# Rules for generating documentation
doc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java

clean:
	@rm -f  $(BINDIR)/*.class
	@rm -f $(BINDIR)/*/*.class
	@rm -Rf doc