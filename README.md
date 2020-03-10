## Specifikáció

### Bevezetés

#### Ötlet

Egy egyszerű Android alkalmazás, amely kriptovaluták árfolyamairól tud információkat adni.

#### Cél

- Legalább 2 képernyő (listázó + részletek)
- API hívás
- Helyi DB
- ORM
    
#### Platform
    
- Natív Android
- Min API 21
    
#### Design

Justinmind wireframe ábrák (content + lo-fi).
Nincs előre megadott design
    
#### Határidő

2020.05.11
    
### Általános követelmények

#### Képernyőtervek

**Content wireframe**

![Drawer](/docs/images/content/Drawer.png)
![List](/docs/images/content/List.png)
![Details](/docs/images/content/Details.png)
![About](/docs/images/content/About.png)

**Lo-Fi wireframe**

![Drawer](/docs/images/lo-fi/Drawer.png)
![List](/docs/images/lo-fi/List.png)
![Details](/docs/images/lo-fi/Detail.png)
![About](/docs/images/lo-fi/About.png)
    
#### Eszköz típus

Telefon, álló
    
#### Online/Offline

Támogasson offline működést, amennyiben nincs hálózat és már volt korábban betöltött adat.
    
#### Backend

Kommunikáció, akár több crypto API-val
- [CoinLib](https://coinlib.io/)
- [CoinLayer](http://coinlayer.com/)
- Ingyenes API kulcsokkal, nincs https
- UI tesztek futtatásához mock webserver

#### Nyelv

Az alkalmazás angol nyelven készül.

### Specifikus követelmények

#### Képernyők

3 darab képernyőből áll az alkalmazás:
- Lista
- Részletek
- Rólunk

#### Navigáció

Lista és a Rólunk oldal között lehet navigálni oldalsó menüvel (navigation drawer). Lista oldalon egy elemre kattintva átnavigál a Részletekre, ahonnan csak vissza navigáció lehetséges a Lista oldalra.

#### Lista

Kilistázza a kriptovalutákat.

Minimálisan a következőket kell tartalmaznia:
- Pull to refresh
- Keresés a listaelemek között, rövidítés alapján.

Listaelemnek tartalmaznia kell:
- Név
- Ikon
- Aktuális átlag ár USD-ban
- Rank

#### Részletek

Egy adott kriptovalutának az adatait jeleníti meg.

Következő adatokat kell megjeleníteni:
- Rövidítés
- Név
- Ikon
- Átlag ár USD-ban
- 24 órás legalacsonyabb ár USD-ban
- 24 órás legmagasabb ár USD-ban
- Legutóbbi 1 órás különbség százalékban
- Legutóbbi 24 órás különbség százalékban
- Legutóbbi 7 napos különbség százalékban

#### Rólunk

- Leírás az alkalmazásról
- Szoftver licensze
- Fejlesző

## Use Case

![UseCase](/docs/images/diagrams/use-case.png)

## User Story-k

- A felhasználó az oldalsó menüből a lista opciót választva, megjeleníti a lista nézetet
- A felhasználó az oldalsó menüből a rólunk opciót választva, megjeleníti a rólunk nézetet
- A felhasználó a lista oldalon, a keresés ikonra kattintva, szűkíti a találatokat
- A felhasználó a lista oldalon, pull-to-refresh mozdulattal, frissíti a listát
- A felhasználó a részletek oldalon, pull-to-refresh mozdulattal, frissíti a részletek oldalt
- A felhasználó a lista oldalon, lista elemre kattintva, átnavigál a részletek oldalra
