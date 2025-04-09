# Sudoku

cd Serveur
javac *.java 
rmic SudokuImpl
java -Djava.security.policy=../java.policy SudokuServer

cd Client
cp ../serveur/SudokuImpl_Stub.class .
cp ../serveur/SudokuInterface.class .
javac *.java 
java -Djava.security.policy=../java.policy SudokuClient