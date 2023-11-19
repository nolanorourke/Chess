all: Chess.class Execute

Chess.class: Chess.java
	javac Chess.java

Execute: *.java
	java Chess