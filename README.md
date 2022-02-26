# BEST Hackathon 2022
Rešitev na temo "Napovedovanje trendov porabe za namen dobave" z uporabo full-stack Docker web aplikacije. Za potrebe uporabe v realnem življenju se lahko prilagodi na uporabo z Access namesto SQL bazo.

## Uporaba
Klonirajte repozitorij s kodo.
```bash
git clone git@github.com:robiworks/best2022.git
```

Za uporabo aplikacije morate imeti nameščen Docker. Aplikacijo zaženete z ukazom:
```bash
sudo docker-compose up
```

Do aplikacije lahko dostopate na naslovu [http://localhost:80/](http://localhost:80/). Aplikacija prikazuje anomalije, popravke in opozorila glede trendov porabe.