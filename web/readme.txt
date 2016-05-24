todo
old-sthlm.se
oldsthlm.se
sthlm.com
old-sthlm.com
oldstockholm

old-stockholm.com
old-stockholm.se
oldstockholm.com
oldstockholm.se
old-sthml.se
old-sthml.com


https://sv.wikipedia.org/wiki/Stockholms_innerstad

*** Images
' Original with always= 2176px

https://developer.apple.com/library/prerelease/tvos/documentation/General/Conceptual/AppleTV_PG/CreatingParallaxArtwork.html

http://127.0.01:8080/FlowEngine_war_exploded/tv/postcard?templates=CatalogTemplate.xml.js
http://127.0.01:8080/FlowEngine_war_exploded/tv/postcard?template=CatalogTemplate.xml.js

1# Start main Alert with shelf (Södermalm
2# Catalog (Gator
3# oneupTemplate

1# move templates to new foldder
2# Crea Java classes for templates

support postCards
tvml
PostCardTVServlet (getTVML + TVJS js file...
PostCardCollector
/poll filesystem + create Item/ItemGroups with tag PostCard+Stockholm+Götgatan
bygger tvml vid varje request till PostCardTVServlet getItem from FlowEngine
getItems() with tag PostCard+

- Item remove type
- Item clean text åäö
- Item/ItemGrup sort on date
-

web gui 1
- itemGroups verkar fel 705st, max 5
- search itemComponents by tags
- create good rss items
- trigger

API UseCases:

*** #1 Onboarding first time ever *******
1-> getTagTree()
//Steg 1 Visa bubblor med topketegorier att välja bort
//Steg 2 Välj vilka underkategori taggr du är extra intreserad av och inte alls intreserad av (+,- bubblor?)
2-> createUser()  -> User(excludeTags,favoriteTags)
3-> updateUser() excludeTags,favoriteTags
go to start #2

*** #2 Launch/relunch application (polling) Main feed
1-> getItemsForUser(User) -> All ItemComponents for this user, as long as updateUser() has not been called
all items still exists for the user. after updateUser() with recivedItems + reindex then new ones will be aviable.
this method can return an empty list meaning no new items exists for the user, all items are already recived.
2-> updateUser() set all deliverd Item id's ??
//Så fort user items ändras så skapas nytt, getItemsForUser() ska alltid bara returnera nytt för usern
om returen är tom=inget nytt finns


*** App running in foreground
1-> poll request (30sec?) got to #2
getItemsForUser()
getTopNewsForLocation(Country...)

*** Todo...
Push updates





Open Shidt
0# Kopiera Bilderna
rm oldImages first !!!!!!!!!!!!!! på servern
scp  -r OldStockholmImages  561bdb2f0c1e66b8bd00008c@flowengine-miguelk.rhcloud.com://var/lib/openshift/561bdb2f0c1e66b8bd00008c/wildfly/standalone/tmp

//REMOVE scp  -r OldStockholmImages  561bdb2f0c1e66b8bd00008c@flowengine-miguelk.rhcloud.com://var/lib/openshift/561bdb2f0c1e66b8bd00008c/wildfly/standalone/tmp/OldStockholmImages

1# Kopiera war filen** Stå i Documents/Temp katalogen
scp FlowEngine_war.war 561bdb2f0c1e66b8bd00008c@flowengine-miguelk.rhcloud.com://var/lib/openshift/561bdb2f0c1e66b8bd00008c/wildfly/standalone/deployments/


Bara Logga in
ssh 561bdb2f0c1e66b8bd00008c@flowengine-miguelk.rhcloud.com



URL
http://www.oldstockhlm.com

Bara Logga in
ssh 561bdb2f0c1e66b8bd00008c@flowengine-miguelk.rhcloud.com

WildFly 8 administrator added.  Please make note of these credentials:

   Username: adminw3LEVHs
   Password: lDJ5Qz5CJPDr

   run 'rhc port-forward flowengine' to access the web admin area on port 9990.


Versions 0.11
- Ny kategori Flygfoton
- Bläddra 3:e stget bug
- Nya bilder

