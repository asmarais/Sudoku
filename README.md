# Sudoku


---

## ⚙️ Instructions de compilation et d'exécution

### Côté Serveur

```bash
cd Serveur

javac *.java

# Générer les stubs RMI
rmic SudokuImpl FabSudokuImpl

java -Djava.security.policy=../java.policy SudokuServer
```

### Côté Client
```bash

cd Client

cp ../Serveur/SudokuImpl_Stub.class .

cp ../Serveur/SudokuInterface.class . 

cp ../Serveur/FabSudokuImpl_Stub.class .

cp ../Serveur/FabSudokuInterface.class .

javac *.java 
java -Djava.security.policy=../java.policy SudokuClient