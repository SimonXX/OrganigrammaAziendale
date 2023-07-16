
# Organigramma Aziendale

[Relazione Progetto con schemi UML](RelazioneProgetto.pdf)

L'applicazione in esame consente la gestione di un organigramma aziendale.


Nota: si allega un file *organigrammaExample.json* che, tramite l'opzione *Carica Organigramma* 

successivamente illustrata, consente di caricare una versione d'esempio di un organigramma.

## Visualizzazione

L'interfaccia grafica mette a disposizione due tipi di visualizzazione per quanto riguarda

la rappresentazione dell'organigramma.

### Con Rettangoli

Nel pannello centrale è possibile visualizzare le unità rappresentate

come rettangoli e collegate alle relative sotto unità mediante dei segmenti.

In questa visualizzazione vengono visualizzate solo le unità, non gli impiegati.

*la struttura grafica dell'organigramma si adatta dinamicamente all'inserimento*

*ed all'eliminazione delle varie unità*.

### ad Albero 

Sul pannello a sinistra è possibile visualizzare la struttura dell'organigramma mediante una

visualizzazione ad albero.


Cliccando su una determinata unità sara possibile espanderne il contenuto, se presente,

e visualizzarne eventuali sotto unità ed impiegati.

*Un impiegato all'interno di un'unità sarà visualizzato come figlio di essa nella gerarchia,*

*inoltre, nella sua etichetta, saranno indicati i ruoli che ricopre nell'unità stessa.*


## Barra Strumenti

Di seguito una lista dei comandi offerti dalla barra degli strumenti.

*al fine di visualizzare i comandi, è necessario fare click sul pannello apposito*


| Comando | 
| :-------- | 
| Modifica Nome Unità |
|Aggiungi Sotto Unità | 
|Rimuovi Unità |
|Aggiungi Impiegato Azienda |
|Rimuovi Impiegato Azienda | 
|Assegna Impiegato ad Unità| 
|Rimuovi Impiegato da Unità|
|Crea Ruolo| 
|Rimuovi Ruolo| 
|Assegna Ruolo ad Unità| 
|Rimuovi Ruolo da Unità| 

### Modifica Nome Unità

Consente di modificare il nome di un'unità esistente all'interno dell'azienda.

### Aggiungi Sotto Unità

Consente di definire una nuova sotto unità per un'unità esistente.

### Rimuovi Unità

Consente di rimuovere un'unità esistente.

*Non è possibile rimuovere un'unità che presenta delle sotto unità*

(è necessario partire dai nodi figli per l'eliminazione)

### Aggiungi Impiegato Azienda

Consente di definire un nuovo impiegato nell'azienda.

*dopo averlo creato, esso potrà essere assegnato alle unità ricoprendo uno o più ruoli in esse*

### Rimuovi Impiegato Azienda

Consente di rimuovere un impiegato dall'azienda.

*una volta eliminato, l'impiegato non risulterà più assegnato alle unità per cui ricopriva un determinato ruolo*

### Assegna Impiegato ad Unità

Consente di assegnare un impiegato, con un determinato ruolo, ad una determinata unità.

*è necessario che per l'unità sia stato assegnato almeno un ruolo consentito, affinché vi si possa*

*essere assegnato un impiegato*

### Rimuovi Impiegato da Unità

Consente di eliminare l'assegnamento di un impiegato da un'unità.

*l'impiegato continuerà a far parte dell'azienda e potrà essere*

*riassegnato ad una determinata unità*

### Crea Ruolo

Consente di definire un ruolo all'interno dell'azienda

*dopo averlo creato, esso potrà essere assegnato alle unità come ruolo consentito per l'unità*

### Rimuovi Ruolo

Consente di eliminare un ruolo definito all'interno dell'azienda

*eliminando un ruolo, tutti gli impiegati assegnati con quel determinato ruolo, a delle unità,*

*non risulteranno più assegnati con quel ruolo (nel caso in cui ricoprano solo quel ruolo nell'unità,*

*essi non risulteranno più assegnati all'unità)*

### Assegna Ruolo ad Unità

Consente di assegnare un ruolo ad una determinata unità

*se un ruolo è assegnato ad un'unità, l'impiegato potrà ricoprire quel determinato ruolo*

*all'interno dell'unità*

### Rimuovi Ruolo da Unità

Consente di eliminare l'assegnamento di ruolo da un'unità.

*il ruolo non sarà più consentito in quell' unità, e gli impiegati*

*non potranno più essere assegnati ad essa ricoprendo quel determinato ruolo*

*(il ruolo continuerà a far parte dell'azienda e potrà continuare ad essere assegnato alle varie unità)*

## Pannello laterale 

### Visualizza Ruoli Ammessi per Unità

Indirizza ad una tabella che associa ad ogni unità i ruoli consentiti in essa.

### Visualizza Impiegati nell'Azienda

Indirizza ad una tabella che associa ad ogni impiegato, tutte le unità a cui è assegnato

e tutti i ruoli che ricopre in esse.

### Visualizza Ruoli Creati nell'Azienda

Indirizza ad una tabella che consente di visualizzare tutti i ruoli creati all'interno dell'azienda.

### Visualizza Statistiche

Indirizza ad una finestra che mostra le statistiche che caratterizzano l'organigramma:
- totale unità
- totale impiegati
- totale ruoli

### Stampa Info Azienda

Indirizza ad una finestra che fornisce una rappresentazione testuale dello stato corrente

dell'organigramma.

### Salva Organigramma

Attraverso un menù di navigazione, consente di salvare lo stato corrente dell'organigramma

in un file in formato .json, che successivamente potrà essere ricaricato nell'applicazione

con l'opzione *Carica Organigramma*.

### Carica Organigramma

Attraverso un menù di navigazione, consente di caricare un organigramma salvato 

in formato .json attraverso l'opzione *Salva Organigramma*

### Azzera Organigramma

Consente di ripristinare l'organigramma allo stato iniziale.

### Guida all'utilizzo

Indirizza ad una finestra con le istruzioni relative all'applicazione in esame.
