find . -name "*.java" > sources.txt;
javac -sourcepath src @sources.txt -d ./
java simulator.Simulator scenario.txt;
