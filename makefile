JFLAGS = -g
JC = javac
JR = jar -cvfm
J = java
RJ = java -jar
.SUFFIXES:
	.java .class
CLASSES = Chess.java

# default:
# 	classes
default:
	$(JC) $(JFLAGS) Chess.java

run:
	find . -type f -name "*.class" -exec rm {} \; && $(JC) $(JFLAGS) Chess.java && $(J) Chess
jar:
	$(JR) hwx.jar Manifest.txt *

jarrun:
	$(RJ) hwx.jar

clean:
	find . -type f -name "*.class" -exec rm {} \;
