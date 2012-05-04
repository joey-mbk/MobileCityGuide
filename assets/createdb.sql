PRAGMA foreign_keys=OFF;
CREATE TABLE "Location" ("locationID" INTEGER PRIMARY KEY  NOT NULL ,"latitude" DOUBLE NOT NULL ,"longitude" DOUBLE NOT NULL );
INSERT INTO "Location" VALUES(1,50.672383,4.612269);
INSERT INTO "Location" VALUES(2,50.670558,4.616135);
INSERT INTO "Location" VALUES(3,50.667969,4.591487);
INSERT INTO "Location" VALUES(4,50.670132,4.609913);
INSERT INTO "Location" VALUES(5,50.668573,4.61613);
INSERT INTO "Location" VALUES(6,50.668392,4.621994);
INSERT INTO "Location" VALUES(7,50.668194,4.619471);
INSERT INTO "Location" VALUES(8,50.669453,4.611582);
INSERT INTO "Location" VALUES(9,50.66994,4.615287);
INSERT INTO "Location" VALUES(10,50.668482,4.617016);
INSERT INTO "Location" VALUES(11,50.666867,4.605836);
CREATE TABLE "CategoryTitles" ("categoryID" INTEGER NOT NULL , "language" TEXT NOT NULL, "title" TEXT NOT NULL, FOREIGN KEY ( "language") REFERENCES Language( "language") ON DELETE CASCADE );
INSERT INTO "CategoryTitles" VALUES(1,'francais','Musée');
INSERT INTO "CategoryTitles" VALUES(1,'english','Museum');
INSERT INTO "CategoryTitles" VALUES(1,'nederlands','Museum');
INSERT INTO "CategoryTitles" VALUES(2,'francais','Shopping');
INSERT INTO "CategoryTitles" VALUES(2,'english','Shopping');
INSERT INTO "CategoryTitles" VALUES(2,'nederlands','Winkelen');
INSERT INTO "CategoryTitles" VALUES(3,'francais','Cinema');
INSERT INTO "CategoryTitles" VALUES(3,'english','Cinema');
INSERT INTO "CategoryTitles" VALUES(3,'nederlands','Bioscoop');
INSERT INTO "CategoryTitles" VALUES(4,'francais','Restaurant');
INSERT INTO "CategoryTitles" VALUES(4,'english','Restaurant');
INSERT INTO "CategoryTitles" VALUES(4,'nederlands','Restaurant');
INSERT INTO "CategoryTitles" VALUES(5,'francais','Nature');
INSERT INTO "CategoryTitles" VALUES(5,'english','Nature');
INSERT INTO "CategoryTitles" VALUES(5,'nederlands','Natuur');
INSERT INTO "CategoryTitles" VALUES(6,'francais','Eau');
INSERT INTO "CategoryTitles" VALUES(6,'english','Water');
INSERT INTO "CategoryTitles" VALUES(6,'nederlands','Water');
CREATE TABLE "ItineraryTitles" ("itineraryID" INTEGER NOT NULL , "language" TEXT NOT NULL, "title" TEXT NOT NULL, FOREIGN KEY (language) REFERENCES Language(language) ON DELETE CASCADE );
INSERT INTO "ItineraryTitles" VALUES(1,'francais','Places de Louvain-La-Neuve');
INSERT INTO "ItineraryTitles" VALUES(1,'english','Places of Louvain-La-Neuve');
INSERT INTO "ItineraryTitles" VALUES(1,'nederlands','Plaatsen van Louvain-La-Neuve');
CREATE TABLE "Language" ("userName" INTEGER NOT NULL, "priority" INTEGER NOT NULL , "language" TEXT NOT NULL, FOREIGN KEY (userName) REFERENCES User(userName) ON DELETE CASCADE );
INSERT INTO "Language" VALUES('Maxime',1,'francais');
INSERT INTO "Language" VALUES('Maxime',2,'english');
INSERT INTO "Language" VALUES('Maxime',3,'nederlands');
INSERT INTO "Language" VALUES('Pierre',1,'francais');
INSERT INTO "Language" VALUES('Hervé',1,'nederlands');
INSERT INTO "Language" VALUES('Hervé',2,'english');
INSERT INTO "Language" VALUES('Hervé',3,'francais');
CREATE TABLE "POI" ("poiID" INTEGER PRIMARY KEY  NOT NULL  UNIQUE , "city" VARCHAR(50) NOT NULL , "locationID" INTEGER NOT NULL, "address" VARCHAR(250) NOT NULL, FOREIGN KEY (locationID) REFERENCES Location(locationID) ON DELETE CASCADE);
INSERT INTO "POI" VALUES(1,'Louvain-La-Neuve',1,'Rue du Labrador 26, 1348 Louvain-La-Neuve');
INSERT INTO "POI" VALUES(2,'Louvain-La-Neuve',2,'Place de l''Accueil, 1348 Louvain-la-Neuve');
INSERT INTO "POI" VALUES(3,'Louvain-La-Neuve',3,'Grand-Place 55, 1348 Louvain-la-Neuve');
INSERT INTO "POI" VALUES(4,'Louvain-La-Neuve',4,'Place Blaise Pascal, 1 bte  L3.03.01, 1348 Louvain-la-Neuve');
INSERT INTO "POI" VALUES(5,'Louvain-La-Neuve',5,'Place des Brabançons 1A,1348 Louvain-La-Neuve');
INSERT INTO "POI" VALUES(6,'Louvain-La-Neuve',6,'Place Sainte-Barbe,1348 Louvain-La-Neuve');
INSERT INTO "POI" VALUES(7,'Louvain-La-Neuve',7,'Place des Sciences, 1348 Louvain-la-Neuve');
INSERT INTO "POI" VALUES(8,'Louvain-La-Neuve',8,'Grand-Place, 1348 Louvain-la-Neuve');
INSERT INTO "POI" VALUES(9,'Louvain-La-Neuve',9,'Place de l''Université, 1348 Louvain-La-Neuve');
INSERT INTO "POI" VALUES(10,'Louvain-La-Neuve',10,'Place des Wallons, 1348 Louvain-La-Neuve');
INSERT INTO "POI" VALUES(11,'Louvain-La-Neuve',11,'Rêverie du Promeneur Solitaire, 1348 Louvain-La-Neuve');
CREATE TABLE "User" ("userName" VARCHAR(100) PRIMARY KEY  NOT NULL ,"age" varchar(5) NOT NULL check("age" in('adult','kid')));
INSERT INTO "User" VALUES('Maxime','adult');
INSERT INTO "User" VALUES('Pierre','kid');
INSERT INTO "User" VALUES('Hervé','adult');
CREATE TABLE "POITitles" ("poiID" INTEGER NOT NULL ,"language" VARCHAR(20) NOT NULL ,"title" VARCHAR(20) NOT NULL, FOREIGN KEY (poiID) REFERENCES POI(poiID) ON DELETE CASCADE, FOREIGN KEY (language) REFERENCES Language(language) ON DELETE CASCADE);
INSERT INTO "POITitles" VALUES(1,'francais','Musée Hergé');
INSERT INTO "POITitles" VALUES(1,'english','Hergé Museum');
INSERT INTO "POITitles" VALUES(1,'nederlands','Hergé Museum');
INSERT INTO "POITitles" VALUES(2,'francais','Esplanade');
INSERT INTO "POITitles" VALUES(3,'francais','Cinéscope');
INSERT INTO "POITitles" VALUES(4,'francais','Musée de Louvain-La-Neuve');
INSERT INTO "POITitles" VALUES(5,'francais','La creperie Bretonne');
INSERT INTO "POITitles" VALUES(6,'francais','Place Sainte Barbe');
INSERT INTO "POITitles" VALUES(7,'francais','Place des Sciences');
INSERT INTO "POITitles" VALUES(8,'francais','Grand Place');
INSERT INTO "POITitles" VALUES(9,'francais','Place de l''Université');
INSERT INTO "POITitles" VALUES(10,'francais','Place des Wallons');
INSERT INTO "POITitles" VALUES(11,'francais','Lac');
CREATE TABLE "Description" ("poiID" INTEGER NOT NULL, "language" VARCHAR(20) NOT NULL, "age" VARCHAR(5) NOT NULL , "description" TEXT NOT NULL, FOREIGN KEY (poiID) REFERENCES POI(poiID) ON DELETE CASCADE, FOREIGN KEY ("language") REFERENCES Language("language") ON DELETE CASCADE );
INSERT INTO "Description" VALUES(1,'francais','adult','De grandes baies vitrées laissent présager un monde intérieur coloré, dédié au créateur de Tintin, à sa vie, son oeuvre, ses passions.');
INSERT INTO "Description" VALUES(1,'nederlands','adult','In het Hergé Museum is er voor elk een manier om te delen in het geluck. Kom een van de grote artiesten van de twintigste eeuw ontdekken, in zijn werk, zijn geluk en zijn tegenslag, in zijn intimiteit.');
INSERT INTO "Description" VALUES(1,'english','adult','Come and find out about one of the most important artists of the last century. Learn about Hergé life and work thourgh an intimate portrait of the man behind the myth. All visitors agree : the Hergé museum is full of surprises and there is plenty to discover.');
INSERT INTO "Description" VALUES(1,'francais','kid','Dans ce musée tu vas apprendre tout sur la vie de Tintin de son chien Milou, et de son ami le Capitaine Haddock.');
INSERT INTO "Description" VALUES(2,'francais','adult','L’esplanade, au cœur de Louvain-la-Neuve est un concept urbain dans lequel la ville accueille un centre commercial avec 95 commerces, une nouvelle rue commerçante piétonne, la Rue Charlemagne, prolongation extérieure du centre commercial jalonnée de plus de 30 boutiques. De quoi transformer le shopping en escapade dans une ambiance agréable et sympa.');
INSERT INTO "Description" VALUES(2,'english','adult','L’esplanade, in the centre of Louvain-la-Neuve, is an urban concept where the town is now home to a shopping centre containing 95 shops. This is joined by a new pedestrian shopping street to extend the shopping centre outside, Rue Charlemagne, lined with more than 30 more shops. Turning shopping into an adventure in an enjoyable, pleasant atmosphere.');
INSERT INTO "Description" VALUES(2,'nederlands','adult','L’esplanade bevindt zich in het hartje van Louvain-la-Neuve en maakt deel uit van eens stedelijk concept waarbij in de stad een winkelcentrum met 95 handelszaken is opgenomen en een nieuwe winkelwandelstraat met meer dan 30 winkels, de Rue Charlemagne, het winkelcentrum in de buitenwereld laat overvloeien. Shoppen wordt een avontuur in een aangename en hartelijke sfeer..');
INSERT INTO "Description" VALUES(3,'francais','adult','Le cinéma de Louvain-la-Neuve,réouvert en 2010, offre un large panel de films récents.');
INSERT INTO "Description" VALUES(4,'francais','adult','Le musée s''est ouvert en 1979, soit 8 ans après la fondation de la ville. Le patrimoine de ce musée est centré sur les grands courants de l''art européen et belge. Que peut-on voir au Musée de Louvain-la-Neuve ? Un espace des civilisations, un espace d''art du 20ème siècle, un espace de dialogue ainsi que des expositions temporaires.');
INSERT INTO "Description" VALUES(4,'english','adult','The museum opened in 1979, within the framework of the University of Louvain (UCL : Université catholique de Louvain), eight years after Louvain-la-Neuve was founded. Over the years, many donations have enriched the original collections which have constituted a remarkable heritage of Arts and Civilisations and are exhibited by rotation. This heritage is centred on the most important tendencies in both European and Belgian Art : from engravings by Albrecht Dürer or Rembrandt to Alechinsky’s or Delvaux’s paintings, and including Gothic or Baroque sculptures. And yet these collections also open vistas to other continents as well with a rich collection of « primal art » (from Africa and Oceania) or an opening on « popular » or « naïve » arts. Through these varied exhibits the hall-mark of which is historical, cultural and social diversity, whole segments of humanity are made present in the very heart of this new city, in the young province of Walloon Brabant. From one exhibition to another and with the changing presentation of the museum’s exhibits, visitors discover a renewed museum with each season. At any time visitors can learn more about the history of civilizations and the diversity of art in the 20th century. Visitors will also come across university students learning their future professions and school children from the area or from further afield, learning and creating art. This is what a contemporary university museum is : a place of life where exhibits and visitors from all walks of life meet together for mutual enrichment.');
INSERT INTO "Description" VALUES(4,'nederlands','adult','Acht jaar na de stichting van Louvain-la-Neuve opende het museum in 1979, in de gebouwen van de universiteit, haar deuren. Doorheen de jaren slaagt het museum er in de oorspronkelijke universitaire verzameling aanzienlijk uit te breiden dankzij verschillende schenkingen. Zo wordt een rijk patrimonium opgebouwd, dat nu per wisseling wordt tentoongesteld. Het erfgoed is vooral gericht op de grote Europese en Belgische stromingen : prenten van Albrecht Dürer of Rembrandt, schilderijen van Alechinsky of Delvaux, gotische of barokke beelden. Het museum bezit een aanzienlijke collectie « primitieve kunst » (uit Afrika en Oceanië) en andere collecties waarin volks- en naïeve kunst hun plaatsje hebben weten te veroveren. Merkwaardig is de historische, culturele of sociale verscheidenheid van de kunstwerken : een stukje geschiedenis van de mensheid biedt zich aan temidden in de nieuwe stad van de jonge provincie Waals Brabant. Bij ieder seizoen, naargelang de tentoonstellingen en presentatie van de kunstwerken, ontdekt de bezoeker telkens een nieuw museum. Hier maakt hij zich vertrouwdmet de geschiedenis van de beschavingen en de vele aspecten van de kunst van de 20ste eeuw. De bezoeker treft er studenten die hier hun opleiding volgen, ziet er kinderen leren of creatief zijn. Dit is het universiteitsmuseum van vandaag : een dialoog tussen mensen van verschillende horizonten en kunstwerken van uiteenlopende herkomst voor een betere wederzijdse ontplooiing.');
INSERT INTO "Description" VALUES(5,'francais','adult','Dans notre salle au décor rustique ou en terrasse aux beaux jours, dégustez l''une de nos 350 crêpes salées d''inspiration française ou exotique, ou appréciez l''une de nos salades variées. Pour accompagner votre plat, nous avons à votre disposition une carte de plus de 200 bières artisanales belges dont toutes les trappistes et 5 bières au fût. Nous pouvons aussi vous servir un cidre bien frais ou vous laisser choisir un vin de pays. En dessert vous choisirez une crêpe sucrée ou flambée, une coupe de glace maison ou encore un milkshake que vous accompagnerez d'' un café lointain, un thé vert parfumé ou une infusion. Nous vous accueillons et vous servons tout au long du jour de 9h du matin jusqu''à 1h de la nuit, la cuisine restant ouverte non-stop.');
INSERT INTO "Description" VALUES(5,'nederlands','adult','Keuken: Brasserie , Crêperie , Franse Accommodaties: Terras , RestoBookings , Faciliteiten voor mindervaliden , Honden toegelaten , Bar - Lounge Kader: Authentiek kader Groepscapaciteit: 20 Budget : - 25 EUR');
INSERT INTO "Description" VALUES(6,'francais','adult','La Place Sainte-Barbe est située au coeur du quartier scientifique de Louvain-la-Neuve. Elle est jallonée par des batiments tels que le Réaumur, qui abrite des sales didactiques ainsi que des laboratoires, mais aussi par des auditoires de cours, dans le bâtiment "Sainte Barbe".');
INSERT INTO "Description" VALUES(7,'francais','adult','La place des sciences est située au coeur du quartier scientifique de Louvain-la-Neuve. Elle est bordée par un restaurant universitaire (Le Gallilé), ainsi que par la bibliothèque des sciences exactes.');
INSERT INTO "Description" VALUES(11,'francais','adult','Prévu pour servir d''abord de bassin d''orage et ainsi éviter les inondations en aval de la ville de Louvain-la-Neuve, le lac de Louvain-la-Neuve a été conçu pour offrir un plan d''eau permanent. Il s''étend sur quelque 5 ha avec une profondeur moyenne de 1,20 m. Ces 60.000 m2 sont alimentés par les eaux de pluie de son bassin hydrographique qui couvre 350 ha. Un mm de pluie, soit 1 litre par m2, sur l''ensemble du bassin provoque une montée d''eau de quelque 30 mm. Une pluie centennale fera varier le niveau d''eau de 2,5 m. Le tour du lac, la Rêverie du promeneur solitaire, est long de 1.570 m.');
CREATE TABLE "Image" ("path" TEXT PRIMARY KEY  NOT NULL , "poiID" INTEGER NOT NULL, "timeOfDay" VARCHAR(5) NOT NULL check("timeOfDay" in('day','night')), FOREIGN KEY (poiID) REFERENCES POI(poiID) ON DELETE CASCADE);
INSERT INTO "Image" VALUES('musee-herge5.jpg',1,'night');
INSERT INTO "Image" VALUES('musee-herge31.jpg',1,'day');
INSERT INTO "Image" VALUES('esplanade.jpg',2,'day');
INSERT INTO "Image" VALUES('esplanade2.jpg',2,'day');
INSERT INTO "Image" VALUES('cinescope1.jpg',3,'day');
INSERT INTO "Image" VALUES('cinescope2.jpg',3,'day');
INSERT INTO "Image" VALUES('cinescope3.jpg',3,'day');
INSERT INTO "Image" VALUES('museelln1.jpg',4,'day');
INSERT INTO "Image" VALUES('museelln2.jpg',4,'day');
INSERT INTO "Image" VALUES('creperie_bretone.jpg',5,'day');
INSERT INTO "Image" VALUES('creperie_bretone2.jpg',5,'day');
INSERT INTO "Image" VALUES('barbe3.jpg',6,'day');
INSERT INTO "Image" VALUES('sciences1.jpg',7,'day');
INSERT INTO "Image" VALUES('sciences2.jpg',7,'day');
INSERT INTO "Image" VALUES('sciences3.jpg',7,'day');
INSERT INTO "Image" VALUES('gp1.jpg',8,'day');
INSERT INTO "Image" VALUES('gp2.jpg',8,'day');
INSERT INTO "Image" VALUES('gp3.jpg',8,'day');
INSERT INTO "Image" VALUES('univ1.jpg',9,'day');
INSERT INTO "Image" VALUES('univ2.jpg',9,'day');
INSERT INTO "Image" VALUES('univ3.jpg',9,'day');
INSERT INTO "Image" VALUES('wallons1.jpg',10,'day');
INSERT INTO "Image" VALUES('lac1.jpg',11,'day');
INSERT INTO "Image" VALUES('lac2.jpg',11,'day');
INSERT INTO "Image" VALUES('lac3.jpg',11,'day');
CREATE TABLE "LocationGuideLines" ("poiID" INTEGER NOT NULL ,"language" VARCHAR(20) NOT NULL ,"locationGuideLines" TEXT NOT NULL, FOREIGN KEY (poiID) REFERENCES POI(poiID) ON DELETE CASCADE, FOREIGN KEY (language) REFERENCES Language(language) ON DELETE CASCADE );
INSERT INTO "LocationGuideLines" VALUES(6,'francais','Situé derrière les auditoires St-Barbe');
INSERT INTO "LocationGuideLines" VALUES(3,'francais','Situé sur la Grand-Place');
INSERT INTO "LocationGuideLines" VALUES(2,'francais','Situé derrière la Place de L''université');
INSERT INTO "LocationGuideLines" VALUES(1,'francais','Situé à coté de l''école maternelle de Martin V');
INSERT INTO "LocationGuideLines" VALUES(1,'english','Located next to the kindergarten by Martin V');
INSERT INTO "LocationGuideLines" VALUES(1,'nederlands','Naast de kleuterschool ligt door Martin V');
INSERT INTO "LocationGuideLines" VALUES(11,'francais','Situé derrière l''Aula Magna');
INSERT INTO "LocationGuideLines" VALUES(4,'francais','Situé à proximité de la Place du Cardinal Mercier');
INSERT INTO "LocationGuideLines" VALUES(5,'francais','Situé à proximité de la brasserie "La cuvée des Trolls"');
INSERT INTO "LocationGuideLines" VALUES(7,'francais','Situé en haut de la rue des Wallons');
INSERT INTO "LocationGuideLines" VALUES(8,'francais','Situé en bas de la rue Charlemagne');
INSERT INTO "LocationGuideLines" VALUES(9,'francais','Situé entre la rue des Wallons et la rue Charlemagne');
INSERT INTO "LocationGuideLines" VALUES(10,'francais','Situé sur la rue des Wallons');
CREATE TABLE "POICategory" ("categoryID" INTEGER NOT NULL ,"poiID" INTEGER NOT NULL, FOREIGN KEY (poiID) REFERENCES POI(poiID) ON DELETE CASCADE, FOREIGN KEY (categoryID) REFERENCES CategoryTitles(categoryID) ON DELETE CASCADE);
INSERT INTO "POICategory" VALUES(1,1);
INSERT INTO "POICategory" VALUES(2,2);
INSERT INTO "POICategory" VALUES(3,3);
INSERT INTO "POICategory" VALUES(1,4);
INSERT INTO "POICategory" VALUES(4,5);
INSERT INTO "POICategory" VALUES(5,11);
INSERT INTO "POICategory" VALUES(6,11);
CREATE TABLE "POIItinerary" ("itineraryID" INTEGER NOT NULL, "poiID" INTEGER NOT NULL, "step" INTEGER NOT NULL, FOREIGN KEY (itineraryID) REFERENCES ItineraryTitles(itineraryID) ON DELETE CASCADE, FOREIGN KEY (poiID) REFERENCES POI(poiID) ON DELETE CASCADE );
INSERT INTO "POIItinerary" VALUES(1,6,1);
INSERT INTO "POIItinerary" VALUES(1,7,2);
INSERT INTO "POIItinerary" VALUES(1,10,3);
INSERT INTO "POIItinerary" VALUES(1,9,4);
INSERT INTO "POIItinerary" VALUES(1,8,5);
CREATE TABLE "UserItinerary" ("itineraryID" INTEGER NOT NULL,"userName" VARCHAR(100) NOT NULL,  FOREIGN KEY (itineraryID) REFERENCES ItineraryTitles(itineraryID) ON DELETE CASCADE, FOREIGN KEY (userName) REFERENCES User(userName) ON DELETE CASCADE,  FOREIGN KEY (itineraryID) REFERENCES ItineraryTitles(itineraryID) ON DELETE CASCADE);
INSERT INTO "UserItinerary" VALUES(1,'Maxime');
CREATE TABLE "UserCategory" ("categoryID" INTEGER NOT NULL, "userName" VARCHAR(100) NOT NULL, FOREIGN KEY (categoryID) REFERENCES CategoryTitles(categoryID) ON DELETE CASCADE, FOREIGN KEY (userName) REFERENCES User(userName) ON DELETE CASCADE, FOREIGN KEY (categoryID) REFERENCES CategoryTitles(categoryID) ON DELETE CASCADE);
INSERT INTO "UserCategory" VALUES(2,'Maxime');
INSERT INTO "UserCategory" VALUES(3,'Maxime');
INSERT INTO "UserCategory" VALUES(4,'Maxime');
INSERT INTO "UserCategory" VALUES(6,'Pierre');
INSERT INTO "UserCategory" VALUES(1,'Hervé');
INSERT INTO "UserCategory" VALUES(4,'Hervé');
CREATE TABLE "ItineraryCategory" ("categoryID" INTEGER NOT NULL, "itineraryID" INTEGER NOT NULL, FOREIGN KEY (categoryID) REFERENCES CategoryTitles(categoryID) ON DELETE CASCADE, FOREIGN KEY (itineraryID) REFERENCES POIItinerary(itineraryID) ON DELETE CASCADE, FOREIGN KEY (categoryID) REFERENCES CategoryTitles(categoryID) ON DELETE CASCADE);
INSERT INTO "ItineraryCategory" VALUES(1,1);

CREATE TRIGGER on_delete AFTER DELETE ON POI BEGIN DELETE FROM POICategory WHERE poiID = old.poiID; DELETE FROM POITitles WHERE poiID = old.poiID; DELETE FROM POIItinerary WHERE poiID = old.poiID; DELETE FROM LocationGuideLines WHERE poiID = old.poiID; DELETE FROM Image WHERE poiID = old.poiID; DELETE FROM Description WHERE poiID = old.poiID; END;
CREATE TRIGGER on_deleteLanguage AFTER DELETE ON Language BEGIN DELETE FROM CategoryTitles WHERE language = old.language; DELETE FROM Description WHERE language = old.language; DELETE FROM ItineraryTitles WHERE language = old.language; DELETE FROM CategoryTitles WHERE language = old.language; DELETE FROM LocationGuideLines WHERE language = old.language; DELETE FROM POITitles WHERE language = old.language; END;
CREATE TRIGGER on_deleteUserName AFTER DELETE ON User BEGIN DELETE FROM UserCategory WHERE userName = old.userName; DELETE FROM UserItinerary WHERE userName = old.userName; DELETE FROM Langage WHERE userName = old.userName; END;
CREATE TRIGGER on_deleteItineraryID AFTER DELETE ON ItineraryTitles BEGIN DELETE FROM UserItinerary WHERE itineraryID = old.itineraryID; DELETE FROM POIItinerary WHERE itineraryID = old.itineraryID; DELETE FROM ItineraryCategory WHERE itineraryID = old.itineraryID; END;
CREATE TRIGGER on_deleteCategoryID AFTER DELETE ON CategoryTitles BEGIN DELETE FROM UserCategory WHERE categoryID = old.categoryID; DELETE FROM POICategory WHERE categoryID = old.categoryID; DELETE FROM ItineraryCategory WHERE categoryID = old.categoryID; END;
CREATE TRIGGER on_deleteLocationID AFTER DELETE ON Location BEGIN DELETE FROM POI WHERE locationID = old.locationID; END;
CREATE TRIGGER update_user AFTER UPDATE ON User BEGIN UPDATE UserCategory SET userName = new.userName WHERE userName = old.userName; UPDATE Language SET userName = new.userName WHERE userName = old.userName; END;