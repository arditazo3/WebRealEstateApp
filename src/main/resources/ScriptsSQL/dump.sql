CREATE DATABASE IF NOT EXISTS web_real_estate_app;
USE web_real_estate_app;

--
-- Definition of table nation
--

DROP TABLE IF EXISTS nation;
CREATE TABLE nation (
  id_nation int(11) NOT NULL AUTO_INCREMENT,
  name_nation varchar(40) NOT NULL,
  PRIMARY KEY (id_nation)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table nation
--

/*!40000 ALTER TABLE nation DISABLE KEYS */;
INSERT INTO nation (id_nation,name_nation) VALUES
  (1,'Albania'),
  (2,'Turkey'),
  (3,'USA'),
  (4,'Brasil'),
  (5,'Italy'),
  (6,'Germany'),
  (7,'Russia'),
  (8,'Spain'),
  (9,'Austria'),
  (10,'Greece'),
  (11,'Macedonia'),
  (12,'Tunisia'),
  (13,'Marocoo'),
  (14,'Peru'),
  (15,'Argentina');
/*!40000 ALTER TABLE nation ENABLE KEYS */;


--
-- Definition of table city
--

DROP TABLE IF EXISTS city;
CREATE TABLE city (
  id_city int(11) NOT NULL AUTO_INCREMENT,
  name varchar(80) NOT NULL,
  PRIMARY KEY (id_city)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table id_city
--

/*!40000 ALTER TABLE city DISABLE KEYS */;
INSERT INTO city (id_city,name) VALUES
 (1,'Tirana'),
 (3,'Elbasan'),
 (5,'Durres'),
 (6,'Peshkopi'),
 (13,'Korce'),
 (20,'Vlore'),
 (21,'Shkoder'),
 (22,'Saranda'),
 (24,'Kukes'),
 (25,'Puke'),
 (26,'Fier');
/*!40000 ALTER TABLE city ENABLE KEYS */;

--
-- Definition of table gender
--

DROP TABLE IF EXISTS gender;
CREATE TABLE gender (
  id_gender int(11) NOT NULL AUTO_INCREMENT,
  description varchar(9) NOT NULL,
  PRIMARY KEY (id_gender),
  UNIQUE KEY description (description)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table gender
--

/*!40000 ALTER TABLE gender DISABLE KEYS */;
INSERT INTO gender (id_gender,description) VALUES
  (2,'Female'),
  (1,'Male');
/*!40000 ALTER TABLE gender ENABLE KEYS */;

--
-- Definition of table type_user
--

DROP TABLE IF EXISTS type_user;
CREATE TABLE type_user (
  id_type_user int(11) NOT NULL AUTO_INCREMENT,
  description varchar(150) NOT NULL,
  PRIMARY KEY (id_type_user),
  UNIQUE KEY description (description)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE type_user DISABLE KEYS */;
INSERT INTO type_user (id_type_user,description) VALUES
  (3,'Agency Staff'),
  (2,'Buyer'),
  (1,'Seller');
/*!40000 ALTER TABLE type_user ENABLE KEYS */;


--
-- Definition of table type_address
--

DROP TABLE IF EXISTS type_address;
CREATE TABLE type_address (
  id_type_address int(11) NOT NULL AUTO_INCREMENT,
  descr_type_address varchar(35) NOT NULL,
  PRIMARY KEY (id_type_address)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table type_address
--

/*!40000 ALTER TABLE type_address DISABLE KEYS */;
INSERT INTO type_address (id_type_address,descr_type_address) VALUES
  (1,'Residential'),
  (2,'Commercial'),
  (3,'RODOVIA'),
  (4,'FAZENDA'),
  (5,'Industry'),
  (6,'CHÁCARA'),
  (7,'Vila');
/*!40000 ALTER TABLE type_address ENABLE KEYS */;


--
-- Definition of table type_region
--

DROP TABLE IF EXISTS type_region;
CREATE TABLE type_region (
  id_type_region int(11) NOT NULL AUTO_INCREMENT,
  descr_type_region varchar(40) NOT NULL,
  PRIMARY KEY (id_type_region)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table type_region
--

/*!40000 ALTER TABLE type_region DISABLE KEYS */;
INSERT INTO type_region (id_type_region,descr_type_region) VALUES
  (1,'RUA'),
  (2,'ALAMEDA'),
  (3,'AVENIDA'),
  (4,'VIELA'),
  (5,'BECO'),
  (6,'TRAVESSA'),
  (7,'PRAÇA');
/*!40000 ALTER TABLE type_region ENABLE KEYS */;

--
-- Definition of table property
--

DROP TABLE IF EXISTS property;
CREATE TABLE property (
  id_property int(11) NOT NULL AUTO_INCREMENT,
  descr_property varchar(255) NOT NULL,
  type_property varchar(255) NOT NULL,
  date_register date NOT NULL,
  address varchar(80) NOT NULL,
  id_city int(11) NOT NULL,
  id_type_region int(11) NOT NULL,
  PRIMARY KEY (id_property),
  KEY property_city_key (id_city),
  KEY property_region_key (id_type_region),
  CONSTRAINT property_region_key FOREIGN KEY (id_type_region) REFERENCES type_region (id_type_region),
  CONSTRAINT property_city_key FOREIGN KEY (id_city) REFERENCES city (id_city)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

INSERT INTO property(descr_property, type_property, date_register, address, id_city, id_type_region) VALUES
('Apartment', 'Apartment', now(), 'Groove Str', 1, 1);

--
-- Definition of table user
--

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id_user int(11) NOT NULL AUTO_INCREMENT,
  CPF varchar(14) NOT NULL,
  date_register date NOT NULL,
  date_birth date NOT NULL,
  email varchar(80) NOT NULL,
  name varchar(80) NOT NULL,
  phone varchar(15) NOT NULL,
  id_gender int(11) NOT NULL,
  username varchar(25) DEFAULT NULL,
  permission varchar(36) DEFAULT NULL,
  password varchar(40) DEFAULT NULL,
  id_type_user int(11) NOT NULL,
  PRIMARY KEY (id_user),
  UNIQUE KEY username (username),
  KEY user_gender_key (id_gender),
  KEY user_type_user_key (id_type_user),
  CONSTRAINT user_gender_key FOREIGN KEY (id_gender) REFERENCES gender (id_gender),
  CONSTRAINT user_type_user_key FOREIGN KEY (id_type_user) REFERENCES type_user (id_type_user)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table user
--

/*!40000 ALTER TABLE user DISABLE KEYS */;
INSERT INTO user (id_user,CPF,date_register,date_birth,email,name,phone,id_gender,username,permission,password, id_type_user) VALUES
 (1,'12345667644','2013-01-12','2013-01-01','teste@live.com','ADMIN','(11) 1111-1111',1,'admin','ROLE_ADMIN','d033e22ae348aeb5660fc2140aec35850c4da997', 1);
/*!40000 ALTER TABLE user ENABLE KEYS */;

--
-- Definition of table address
--

DROP TABLE IF EXISTS address;
CREATE TABLE address (
  id_address int(11) NOT NULL AUTO_INCREMENT,
  street varchar(80) DEFAULT NULL,
  code_street varchar(9) DEFAULT NULL,
  zip int(11) DEFAULT NULL,
  region varchar(80) DEFAULT NULL,
  region_number int(11) DEFAULT NULL,
  id_city int(11) NOT NULL,
  id_nation int(11) NOT NULL,
  id_user int(11) DEFAULT NULL,
  id_type_address int(11) NOT NULL,
  id_type_region int(11) NOT NULL,
  PRIMARY KEY (id_address),
  KEY address_region_key (id_type_region),
  KEY address_city_key (id_city),
  KEY address_type_address_key (id_type_address),
  KEY address_nation_key (id_nation),
  KEY address_user_key (id_user),
  CONSTRAINT address_city_key FOREIGN KEY (id_city) REFERENCES city (id_city),
  CONSTRAINT address_nation_key FOREIGN KEY (id_nation) REFERENCES nation (id_nation),
  CONSTRAINT address_user_key FOREIGN KEY (id_user) REFERENCES user (id_user),
  CONSTRAINT address_type_address_key FOREIGN KEY (id_type_address) REFERENCES type_address (id_type_address),
  CONSTRAINT address_region_key FOREIGN KEY (id_type_region) REFERENCES type_region (id_type_region)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table address
--

/*!40000 ALTER TABLE address DISABLE KEYS */;
INSERT INTO address (id_address,street,code_street,zip,region,region_number,id_city,id_nation,id_user,id_type_address,id_type_region) VALUES
  (1,'AHFGSDGFS','11111-111',0,'WERTYUI',21,1,11,1,1,1);
/*!40000 ALTER TABLE address ENABLE KEYS */;
