JFLAGS = -g
JC = javac
JR = jar -cvfm
J = java
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
	$(JR) ChessProject.jar *
clean:
	find . -type f -name "*.class" -exec rm {} \;
