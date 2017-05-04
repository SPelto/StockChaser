**Lyhesti ohjelmasta**:
StockChaser on ohjelma joka sallii markkinadatan lataamisen netistä, sen sailömisen paikallisesti sekä sen visualisoinnin.


**Sovelluslogiikan luokat**
- DataHandler
- DataFetcher
- FileMaker
- FileReader
- Parser
- Mapper


**Luokkien toiminta**
- DataHandler on vastuussa datan hakemisesta, muovaamisesta ja tallentamisesta. Se käyttää apunaan luokkia: "DataFetcher", "FileMaker" ja "FileReader"

- DataFetcher hakee netistä dataa ja Parser-luokan avulla muovaa siitä luettavaa.

- FileMaker on yksinkertainen luokka tiedostojen kirjoittamiseen.

- FileReader lukee .csv tiedostolta dataa ja esittää sen halutulla tavalla.

- Mapper sovittaa sille annetut datapisteet määritetyn kokoisen alueen graafin pisteiksi.

**Käyttöliittymä**
- GUI
- Grapher

**Toiminta**
- GUI on generoitu käyttäen netbeansin swingBuilderia.
- Grapher on itse kirjoitettu luokka joka toimii graafin piirtäjänä.
