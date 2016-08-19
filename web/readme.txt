Open Shift
1# Kopiera war filen till servern:
scp scp /Users/miguelkrantz/Documents/temp/ItemStore.war 5751eef32d5271c77f0001b3@engine-itemstore.rhcloud.com://var/lib/openshift/5751eef32d5271c77f0001b3/wildfly/standalone/deployments/


Bara Logga in
ssh 5751eef32d5271c77f0001b3@engine-itemstore.rhcloud.com

URL API
http://engine-itemstore.rhcloud.com/ItemStore/api/itemGroup/search?favoriteTagFilter=swe_news

http://engine-itemstore.rhcloud.com/ItemStore/jsp/index.jsp

-------------------
Heruku / Openshift
miguelworker1 /Swift%123


-----ide
Search 1 (hit)
*Sport* = A,C,D

ItemTagTree A
swe_sport_fotboll
swe_sport_zlatan
swe_sport_os+fotboll

ItemTagTree B
swe_blog_fotboll
swe_blog_os+fotboll

ItemTagTree C
eng_sport_fotboll

ItemTagTree D
swe_nyheter_fotboll
swe_nyheter_stockholm
swe_nyheter_sport

----------------- Composite?
<TagStrore>
Map<String,Tag>

Tag //synonyms
registerTag(Tag tag) //UI store in DB,file hardcoded now

Tag
isComposite() //Om tag friends bara om composit del av tagTreePath

TagTree      //1 eller flera TagTreePath(s) från till String
TagTreePath //fullstäning path root till child e.g: swe_sport_zlatan
Tag	     //Enskild tag "Sport" "Zlatan"
TagGroup     //Ett tagname + 1 flera taggar i lsta
Tag groupNameTag
List<Tag> friends

Article 1 Södermalm,Hornstull,Skanstull
Article 2 Hornstull //scan
<TagStore>
//Hardcoded + dynamic tags from file/DB

tag ->*context_tag
    ->synonymer
context_words
Södermalm,tanto, tanto badet,Hornstull;

--Luc Bessons
tag: Luc Besson,Luc Bessons
context: film

ItemX
  TagTree tr [swe_blog]


tag_friends
[Södermalm] .. synonymer hornstull,[skanstull]
[Hornstull] .. synonymer

Ostermlam
 ...tags...


