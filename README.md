# Escape Campus

Escape Campus ist ein textbasiertes Java-Konsolenspiel. Du bewegst dich durch Raeume, sammelst Items, kaempfst gegen Gegner und suchst den Ausgang hinter dem Bossraum.

Die Spielwelt wird aus `campus.csv` geladen. Das Projekt hat aktuell kein Maven oder Gradle, sondern wird direkt ueber `Game.java` gestartet.

## Starten

Am einfachsten in IntelliJ:

1. Projektordner `Escape-Room` oeffnen.
2. Ein JDK als Project SDK auswaehlen.
3. `Game.java` oeffnen.
4. `public static void main(String[] args)` starten.

Alternativ ueber die Konsole, wenn `javac` im PATH ist:

```powershell
javac -encoding UTF-8 Game.java
java Game
```

Wichtig: Das Spiel muss aus dem Projektordner gestartet werden, weil `campus.csv`, `highscore.csv` und `saveData.csv` ueber relative Pfade verwendet werden.

## Befehle

| Befehl | Beschreibung |
| --- | --- |
| `hilfe` | Zeigt die Befehle im Spiel. |
| `schau` | Zeigt die Beschreibung des aktuellen Raums. |
| `gehe n` | Geht nach Norden. |
| `gehe s` | Geht nach Sueden. |
| `gehe o` | Geht nach Osten. |
| `gehe w` | Geht nach Westen. |
| `inventar` | Zeigt dein Inventar. |
| `inspect <Item>` | Zeigt Informationen zu einem Item. |
| `benutze <Item>` | Benutzt ein Item. |
| `status` | Zeigt Leben, Schaden und Ruestung. |
| `fullmap` | Zeigt die komplette Karte. |
| `ende` | Beendet das Spiel. |

## Spielsystem

Der Spieler startet im `Foyer`. Raeume koennen Items, Punkte, Events oder verschlossene Tueren enthalten. Verschlossene Raeume brauchen einen `Schlussel`. Beim Betreten eines Raums werden dessen Punkte zum Highscore addiert.

Das Inventar kann maximal 5 Items enthalten. Wenn es voll ist, kann ein vorhandenes Item ersetzt werden.

Wichtige Items:

- `Schlussel`: oeffnet verschlossene Tueren
- `Baseballschlaeger` und `Schwert`: Waffen
- `Lederruestung` und `Eisenruestung`: Ruestungen
- `Heilungstrank`: heilt Leben
- `Staerketrank`: erhoeht Angriffsschaden
- `Upgradestein`: verzaubert Waffen oder Ruestungen

Events werden beim Betreten eines Raums gestartet. Es gibt normale Gegner, den Bossgegner `Eisenwaechter` und eine verletzte Person als optionales Event.

## Projektstruktur

```text
Game.java                 Hauptspiel und Einstiegspunkt
campus.csv                Raumdaten, Items, Events und Ausgaenge
highscore.csv             gespeicherte Highscores
saveData.csv              aktueller Raum oder Inventar-Daten

Items/                    Item-, Waffen- und Ruestungsklassen
Karte/                    Kartenanzeige
RaumSystem/               Raeume und Events
RaumSystem/Event/         Gegner, Boss, NPC und Kampf
Spieler/                  Spieler, Inventar und Werte
HighScore/                Highscore-Logik
helper/                   Farben, Wartezeiten, Boss-Check
```

## campus.csv bearbeiten

Raeume haben dieses Format:

```text
ROOM;Name;Beschreibung;verschlossen;Item;Event;X;Y;Punkte;
```

Ausgaenge haben dieses Format:

```text
EXIT;Raum;Richtung;Zielraum
```

Item-Codes:

```text
N = kein Item
S = Schlussel
B = Baseballschlaeger
H = Heilungstrank
W = Schwert
A = Staerketrank
L = Lederruestung
```

Event-Codes:

```text
N = kein Event
Z = Zombie/Gegner
V = verletzte Person
B = Bossgegner
```

Hinweis: Im Spiel benutzt du `gehe o` fuer Osten. In `campus.csv` wird fuer Osten aktuell `e` verwendet.

## Hinweise

- `saveData.csv` wird beschrieben, aber aktuell nicht automatisch geladen.
- `RoomExceptions.main(Player, Room)` ist keine echte Startmethode, sondern Speziallogik fuer bestimmte Raeume.
- Es gibt noch keine automatisierten Tests.
