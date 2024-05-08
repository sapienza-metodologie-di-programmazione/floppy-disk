# Floppy Disk

Implementare un floppy disk da `3.5 pollici`. Il floppy disk è un supporto magnetico che contiene dati e può essere acceduto in **lettura e scrittura.

Esso ha una capacità pari a `1.474.560 Bytes` (ovvero `1.44 MB`)

Il floppy disk possiede anche un **blocco scrittura** che è possibile *attivare* o *disattivare*; questo meccanismo, se attivato, **impedisce la scrittura di dati**;

## Test

- `public static Floppy floppyDisk()`
    - ritorna un'istanza dell'interfaccia `Floppy` 

L'interfaccia `Floppy` richiede di implementare 

- `void placeHead(int position) throws OutOfMemory`
    - posiziona la testina al `byte` in posizione `position`
    - se il `byte` è al di **fuori della memoria** del floppy disk, lancia l'eccezione `OutOfMemory`

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

- `void activateWriteBlock()`
    - attiva il blocco scrittura

- `void deactivateWriteBlock()`
    - disattiva il blocco scrittura
