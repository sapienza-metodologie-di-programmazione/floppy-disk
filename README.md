# Floppy Disk

Implementare un floppy disk da `3.5 pollici`. Il floppy disk è un supporto magnetico che contiene dati e può essere acceduto in **lettura e scrittura.

Esso ha una capacità pari a `1.474.560 Bytes` (ovvero `1.44 MB`)

Il floppy disk possiede anche un **blocco scrittura** che è possibile *attivare* o *disattivare*; questo meccanismo, se attivato, **impedisce la scrittura di dati**;

## Test

> NOTA: è stato lasciato appositamente un errore di compilazione in `AppTest.java`. Capire cosa è necessario modificare nell'interfaccia Floppy per risolvere l'errore!

- `public static Floppy floppyDisk()`
    - ritorna un'istanza dell'interfaccia `Floppy` che occupa i normali `1.4 MB`

### Test difficile

- `public static Floppy bigFloppyDisk()`
    - ritorna un'istanza dell'interfaccia `Floppy` che occupa `20 GB` (`21474836480 Byte`)
    - questa funzione va implementata **dopo aver superato i test** per il floppy disk da `1.4 MB` 

### interfaccia Floppy

L'interfaccia `Floppy` richiede di implementare 

- `void placeHead(int position) throws OutOfMemory`
    - posiziona la testina al `byte` in posizione `position`
    - se il `byte` è al di **fuori della memoria** del floppy disk, lancia l'eccezione `OutOfMemory`
    - il byte con indice `1474560` è considerato `OutOfMemory` (i byte sono indicizzati partendo da `0`)

```java

```

- `List<Byte> read(int size) throws OutOfMemory, UndefinedMemory`
    - legge un numero di `byte` pari a `size` dalla memoria, partendo dalla `position` impostata
    - se leggendo **esce dalla memoria massima possibile nel floppy disk**, lancia l'eccezione `OutOfMemory` e resetta la memoria al valore precedente
    - se si prova a leggere memoria mai scritta fino a quel momento, lancia l'eccezione `UndefinedMemory` 

- `void write(List<Byte> bytes) throws OutOfMemory, WriteBlockActive`
    - scrive i `bytes` in memoria partendo dalla `position` impostata
    - se scrivendo **esce dalla memoria massima possibile nel floppy disk**, lancia l'eccezione `OutOfMemory`

- `void format() throws WriteBlockActive;`
    - formatta il disco (resetta tutti i `byte` a 0)
    - se il **blocco scrittura** è attivo, lancia l'eccezione `WriteBlockActive`
    - tutta la memoria del floppy disk è impostata a `Undefined`, quindi le letture subito dopo un `format()` lanciano una `UndefinedMemory` exception

- `void activateWriteBlock()`
    - attiva il blocco scrittura

- `void deactivateWriteBlock()`
    - disattiva il blocco scrittura



