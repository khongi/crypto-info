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

### Specifikus követelmények

#### Képernyők

3 darab képernyőből áll az alkalmazás:
- Lista
- Részletek
- Rólunk

#### Navigáció

Lista és a Rólunk oldal között lehet navigálni oldalsó menüvel (navigation drawer). Lista oldalon egy elemre kattintva átnavigál a Részletekre, ahonnan csak vissza navigáció lehetséges a Lista oldalra.
