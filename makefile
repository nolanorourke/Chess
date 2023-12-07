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
	rm -rf *.class && $(JC) $(JFLAGS) Chess.java && $(J) Chess
jar:
	$(JR) ChessProject.jar *
clean:
	rm -rf *.class