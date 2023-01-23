DROP DATABASE IF EXISTS liga;
CREATE DATABASE liga CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE liga;

CREATE TABLE equipo ( 
CodEquipo VARCHAR(4) NOT NULL PRIMARY KEY,
Nombre    VARCHAR(30) NOT NULL,
Localidad VARCHAR(15) NULL
);

CREATE TABLE jugador (
CodJugador VARCHAR(4) NOT NULL PRIMARY KEY,
Nombre     VARCHAR(30) NOT NULL,
FechaNacimiento DATE NULL,
Demarcacion VARCHAR(10) NULL,
CodEquipo VARCHAR(4) NOT NULL,
FOREIGN KEY (CodEquipo) REFERENCES equipo (CodEquipo) ON UPDATE CASCADE
);

CREATE TABLE partido (
CodPartido VARCHAR(4) NOT NULL PRIMARY KEY,
CodEquipoLocal VARCHAR(4) NOT NULL,
CodEquipoVisitante VARCHAR(4) NOT NULL,
Fecha DATE NULL,
Jornada VARCHAR(20) NULL,
FOREIGN KEY (CodEquipoLocal) REFERENCES equipo (CodEquipo) ON UPDATE CASCADE,
FOREIGN KEY (CodEquipoVisitante) REFERENCES equipo (CodEquipo) ON UPDATE CASCADE
);
CREATE TABLE incidencias (
NumIncidencia VARCHAR(6) PRIMARY KEY,
CodPartido VARCHAR(4) NOT NULL,
CodJugador VARCHAR(4) NOT NULL,
Minuto INTEGER(2) NULL,
Tipo VARCHAR(20) NULL,
FOREIGN KEY (CodPartido) REFERENCES partido (CodPartido) ON UPDATE CASCADE,
FOREIGN KEY (CodJugador) REFERENCES jugador (CodJugador) ON UPDATE CASCADE
);

INSERT INTO equipo (CodEquipo,Nombre,Localidad) 
VALUES ('RMCF','Real Madrid','Madrid'),
 ('FCB','Barcelona','Barcelona'),
 ('VCF','Villarreal','Villarreal'),
 ('SFC','Sevilla','Sevilla');

INSERT INTO jugador(CodJugador,Nombre,FechaNacimiento,Demarcacion,CodEquipo)
VALUES ('KB','Karim Benzema','1987-12-19','Delantero','RMCF'),
 ('DCF','Daniel Ceballos Fernández','1996-08-07','Medio','RMCF'),
 ('FT','Ferran Torres','2000-02-29','Delantero','FCB'),
 ('IP','Iñaki Peña','1999-03-02','Portero','FCB'),
 ('CR','Carlos Romero','2001-10-28','Defensa','VCF'),
 ('AP','Alfonso Pedraza','1996-04-08','Medio','VCF'),
 ('JFST','Jesús Joaquín Fernández Sáenz','1993-11-19','Delantero','SFC'),
 ('YB','Yassine Bounou','1991-04-05','Portero','SFC');

INSERT INTO partido(CodPartido,CodEquipoLocal,CodEquipoVisitante,Fecha,Jornada)
VALUES ('PRB1','RMCF','FCB','2022-10-25','10'),
 ('PSR1','SFC','RMCF','2023-01-03','19'),
 ('PSB1','SFC','FCB','2023-01-23','22'),
 ('PRS1','RMCF','SFC','2022-12-20','23'),
 ('PBV1','FCB','VCF','2023-02-14','24'),
 ('PVS1','VCF','SFC','2023-02-28','26'),
 ('PBS1','FCB','SFC','2023-04-04','30'),
 ('PVB1','VCF','FCB','2023-04-18','32');

INSERT INTO incidencias(NumIncidencia,CodPartido,CodJugador,Minuto,Tipo)
VALUES ('IRB1','PRB1','KB',30,'Gol'),
 ('IRB2','PRB1','DCF',78,'Gol'),
 ('IRB3','PRB1','FT',54,'Gol'),
 ('ISR1','PSR1','KB',15,'Gol'),
 ('ISR2','PSR1','KB',43,'Tarjeta Amarilla'),
 ('ISR3','PSR1','JFST',65,'Tarjeta Amarilla'),
 ('ISB1','PSB1','JFST',10,'Gol'),
 ('ISB2','PSB1','JFST',56,'Tarjeta Amarilla'),
 ('ISB3','PSB1','FT',80,'Tarjeta Amarilla'),
 ('IRS1','PRS1','DCF',37,'Tarjeta Roja'),
 ('IRS2','PRS1','JFST',48,'Gol'),
 ('IRS3','PRS1','KB',90,'Tarjeta Amarilla'),
 ('IBV1','PBV1','CR',24,'Gol'),
 ('IBV2','PBV1','FT',70,'Tarjeta Roja'),
 ('IBV3','PBV1','AP',87,'Tarjeta Amarilla'),
 ('IVS1','PVS1','AP',4,'Tarjeta Amarilla'),
 ('IVS2','PVS1','JFST',35,'Gol'),
 ('IVS3','PVS1','CR',62,'Tarjeta Amarilla'),
 ('IVB1','PVB1','CR',16,'Tarjeta Amarilla'),
 ('IVB2','PVB1','FT',47,'Tarjeta Amarilla'),
 ('IVB3','PVB1','AP',72,'Tarjeta Amarilla');

	
COMMIT;
