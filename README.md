# proiectPa

Nume proiect : MDVSP 
Numele studentilor : Spiridon Madalina, Ursu Ioana Bianca

Aplicatia  propune un algoritm pentru rezolvarea  problemei MDVSP intr-o maniera interactiva.

Pentru interfata grafica s-a folosit javafx. Prima scena reprezinta " intro-ul " de unde se pot selecta optiuni cum ar fi : sunetul respectiv rularea algoritmului.

A doua scena se remarca prin animatia in care un avion aduce harta in prim plan si pentru ca utilizatorul sa interactioneze cu ea, selectand noduri de start respectiv end a unor calatorii. Dupa selectarea acestor puncte este necesara alegerea unor depozite si a numarului de masini per depozit.
Trecerea in scene urmatoare se realizeaza prin apasarea unui buton sub forma de semafor . In ultima scena se deseneaza si se ofera solutii pentru problema... valorile de timp si costuri pentru calatorii  fiind generate random.

Algoritmul pentru MDVSP se foloseste de SDVSP si planifica calatoriile cu ajutorul  unei liste de masini disponibile. Daca o calatorie este deja onorata de un vehicul,  algoritmul prevede daca este mai optim ca aceeasi masina sa onoreze alta calatorie la care se poate ajunge in timp util sau  o masina diferita  sa o serveasca in caz ca mai sunt disponibile la depozit .
Nodurile ( calatoriile ) onorate de acelasi vehicul vor fi colorate cu aceasi culoare .




