# Sudoku


---

## ⚙️ Instructions de compilation et d'exécution
### Côté Client
```bash

cd client

javac ICallback.java

javac Callback.java

rmic Callback

```
### Côté Serveur
```bash
cd Serveur

cp ../Client/ICallback.class .

cp ../Client/Callback_Stub.class .

javac *.java

rmic SudokuImpl FabSudokuImpl

java -Djava.security.policy=java.policy SudokuServer
```

### Côté Client
```bash

cd Client

cp ../Serveur/SudokuImpl_Stub.class .

cp ../Serveur/SudokuInterface.class . 

cp ../Serveur/FabSudokuImpl_Stub.class .

cp ../Serveur/FabSudokuInterface.class .

javac *.java 

java -Djava.security.policy=java.policy SudokuClient