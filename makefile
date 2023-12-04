JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class: $(JC) $(JFLAGS) $.java
CLASSES = Chess.java ChessJPanel.java 

default: classes
classes: $(CLASSES:.java=.class)
clean: rm -rf *.class