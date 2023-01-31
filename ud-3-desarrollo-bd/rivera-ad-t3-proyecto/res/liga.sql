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
  ('PRB2','RMCF','FCB','2023-04-24','33'),
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
 ('IVB3','PVB1','AP',72,'Tarjeta Amarilla'),
 ('IRBB1','PRB2','FT',57,'Gol'),
 ('IRBB2','PRB2','FT',67,'Tarjeta Amarilla'),
 ('IRBB3','PRB2','KB',13,'Tarjeta Amarilla');




/*TRIGGER*/

DELIMITER $$

create trigger no_gol_porteros_insert
BEFORE INSERT ON incidencias
for each row
BEGIN
  DECLARE tipo VARCHAR(20);
  DECLARE Cod_jugador VARCHAR(4);
  DECLARE demar VARCHAR(10);
 
       SET tipo=NEW.Tipo;
       SET Cod_jugador=NEW.CodJugador;
       SET demar =(select  Demarcacion from jugador where CodJugador=Cod_jugador);

   if tipo='Gol' AND  demar = 'Portero' then
         signal  sqlstate '45000'
         set MESSAGE_TEXT='No se permite insertar incidencias de tipo Gol para jugadores cuya demarcacion es Portero';
   end if;
END$$
DELIMITER ;

DELIMITER $$

create trigger no_gol_porteros_update
BEFORE UPDATE ON incidencias
for each row
BEGIN
  DECLARE tipo VARCHAR(20);
  DECLARE Cod_jugador VARCHAR(4);
  DECLARE demar VARCHAR(10);
 
       SET tipo=NEW.Tipo;
       SET Cod_jugador=NEW.CodJugador;
       SET demar =(select  Demarcacion from jugador where CodJugador=Cod_jugador);

   if tipo='Gol' AND  demar = 'Portero' then
         signal  sqlstate '45000'
         set MESSAGE_TEXT='No se permite insertar incidencias de tipo Gol para jugadores cuya demarcacion es Portero';
   end if;
END$$
DELIMITER 

/*PROCEDIMIENTOS ALMACENADOS*/

DELIMITER $$
 CREATE PROCEDURE resultado_partido(IN CodEquipo_local VARCHAR(4),IN CodEquipo_visitante VARCHAR(4))
 BEGIN
	 DECLARE cod VARCHAR(4);
	 DECLARE goles_local INTEGER;
	 DECLARE goles_visitante INTEGER;
	 DECLARE var_final INTEGER DEFAULT 0;

DECLARE partidos CURSOR FOR SELECT CodPartido FROM partido p WHERE p.CodEquipoLocal = CodEquipo_local AND p.CodEquipoVisitante=CodEquipo_visitante;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET var_final=1;	
	/* SET cod=(SELECT p.CodPartido FROM partido p 
	 WHERE p.CodEquipoLocal = CodEquipo_local AND p.CodEquipoVisitante=CodEquipo_visitante);*/
DROP TEMPORARY TABLE IF EXISTS partidoClone;
create  TEMPORARY TABLE partidoClone(
CodPartido VARCHAR(4) PRIMARY KEY,
Fecha DATE ,
golesL INTEGER,
golesV INTEGER
);

OPEN partidos;
bucle:LOOP
		
	FETCH partidos INTO cod;
	IF var_final =1 THEN
	LEAVE bucle;
END IF;


    SET goles_local=(select COUNT(*) FROM incidencias i 
	INNER JOIN jugador j ON j.CodJugador =i.CodJugador  
	INNER JOIN partido p ON p.CodPartido =i.CodPartido
	INNER JOIN equipo e ON e.CodEquipo = j.CodEquipo 
    where e.CodEquipo =CodEquipo_local AND p.CodPartido =cod AND i.Tipo like 'Gol');
   
    SET goles_visitante=(select COUNT(*) FROM incidencias i 
	INNER JOIN jugador j ON j.CodJugador =i.CodJugador  
	INNER JOIN partido p ON p.CodPartido =i.CodPartido
	INNER JOIN equipo e ON e.CodEquipo = j.CodEquipo 
    where e.CodEquipo =CodEquipo_visitante AND p.CodPartido =cod AND i.Tipo like 'Gol');
   

   insert into partidoClone VALUES (cod,(SELECT fecha FROM partido WHERE CodPartido=cod),goles_local,goles_visitante);
   
	

END LOOP bucle;

CLOSE partidos;

 SELECT c.CodPartido,c.Fecha, e.Nombre AS 'Equipo Local',
	 e2.Nombre AS 'Equipo Visitante',c.golesL AS 'Goles Equipo Local' ,c.golesV AS 'Goles Equipo Visitante' 
	 FROM partido p 
	 INNER JOIN partidoClone c ON p.CodPartido =c.CodPartido
	 INNER JOIN equipo e ON p.CodEquipoLocal=e.CodEquipo 
	 INNER JOIN equipo e2 ON e2.CodEquipo =p.CodEquipoVisitante;
	
 END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE informe()
 BEGIN
 	
DECLARE v_cod_equipo VARCHAR(4);
DECLARE v_cod_partido VARCHAR(4);
DECLARE v_nombre VARCHAR(30) DEFAULT '';
DECLARE v_partidos_jugados INTEGER DEFAULT 0;
DECLARE v_partidos_ganados INTEGER DEFAULT 0;
DECLARE v_partidos_empatados INTEGER DEFAULT 0;
DECLARE v_partidos_perdidos INTEGER DEFAULT 0;
DECLARE v_goles_favor INTEGER DEFAULT 0;
DECLARE v_goles_encontra INTEGER DEFAULT 0;
DECLARE v_puntos INTEGER DEFAULT 0;
DECLARE var_final INTEGER DEFAULT 0;
DECLARE v_count INTEGER;
DECLARE goles_equipo INTEGER;
DECLARE goles_equipo2 INTEGER;

DECLARE equipos CURSOR FOR SELECT CodEquipo FROM equipo;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET var_final=1;
DROP TEMPORARY TABLE IF EXISTS equip;
DROP TEMPORARY TABLE IF EXISTS partidoClone;
CREATE TEMPORARY TABLE equip(
     cod_equipo VARCHAR(4),
     nombre VARCHAR(30),
 partidos_jugados INTEGER,
 partidos_ganados INTEGER ,
 partidos_empatados INTEGER,
 partidos_perdidos INTEGER,
 goles_favor INTEGER ,
 goles_encontra INTEGER ,
 puntos INTEGER ,
primary key(cod_equipo)
  );

create  TEMPORARY TABLE partidoClone(
CodPartido VARCHAR(4) PRIMARY KEY,
CodEquipoLocal VARCHAR(4),
CodEquipoVisitante VARCHAR(4),
Fecha DATE ,
Jornada VARCHAR(20)
);

OPEN equipos;
bucle:LOOP
	
	FETCH equipos INTO v_cod_equipo;
	IF var_final =1 THEN
	LEAVE bucle;
END IF;

/*Partidos jugados*/
SET v_nombre= (SELECT Nombre FROM equipo WHERE CodEquipo=v_cod_equipo);
SET v_partidos_jugados=(SELECT COUNT(CodPartido)  FROM partido 
WHERE CodEquipoLocal=v_cod_equipo  OR CodEquipoVisitante =v_cod_equipo);

INSERT INTO partidoClone SELECT * from partido WHERE CodEquipoLocal =v_cod_equipo OR CodEquipoVisitante=v_cod_equipo;
set v_count=(select COUNT(*) from partido WHERE CodEquipoLocal =v_cod_equipo OR CodEquipoVisitante=v_cod_equipo);

WHILE v_count>0 DO

set v_cod_partido =(SELECT CodPartido FROM partidoClone LIMIT 1 );


SET goles_equipo =(select COUNT(*) FROM incidencias i 
	INNER JOIN jugador j ON j.CodJugador =i.CodJugador  
	INNER JOIN partido p ON p.CodPartido =i.CodPartido
	INNER JOIN equipo e ON e.CodEquipo = j.CodEquipo 
    where e.CodEquipo =v_cod_equipo AND p.CodPartido =v_cod_partido AND i.Tipo like 'Gol');
   
   SET goles_equipo2 =(select COUNT(*) FROM incidencias i 
	INNER JOIN jugador j ON j.CodJugador =i.CodJugador  
	INNER JOIN partido p ON p.CodPartido =i.CodPartido
	INNER JOIN equipo e ON e.CodEquipo = j.CodEquipo 
    where e.CodEquipo !=v_cod_equipo AND p.CodPartido =v_cod_partido AND i.Tipo like 'Gol');
/*Goles encontra*/
   SET v_goles_encontra=v_goles_encontra+goles_equipo2;
   
   IF goles_equipo > goles_equipo2 THEN
   SET v_partidos_ganados=v_partidos_ganados+1;
   SET v_puntos=v_puntos+3;
  ELSEIF goles_equipo2 > goles_equipo THEN
   SET v_partidos_perdidos=v_partidos_perdidos+1;
   ELSE 
   SET v_partidos_empatados=v_partidos_empatados+1;
   SET v_puntos=v_puntos+1;
   END IF;
 
delete  from partidoClone limit 1;
SET v_count=(SELECT count(*) FROM partidoClone); 

END WHILE;



/*goles a favor*/
SET v_goles_favor=(select count(*) FROM incidencias i
	WHERE CodJugador IN (SELECT CodJugador FROM jugador 
WHERE CodEquipo=v_cod_equipo AND i.Tipo LIKE 'Gol'));

insert into equip(cod_equipo ,nombre ,partidos_jugados ,partidos_ganados ,partidos_empatados,
 partidos_perdidos  ,
 goles_favor  ,
 goles_encontra ,
 puntos ) VALUES ( v_cod_equipo,v_nombre,v_partidos_jugados, v_partidos_ganados, v_partidos_empatados,v_partidos_perdidos, 
 v_goles_favor,
 v_goles_encontra,
 v_puntos );

set v_partidos_ganados=0 ;
set v_partidos_empatados=0 ;
set v_partidos_perdidos=0;
set v_puntos=0;
set v_goles_encontra=0;
/*SELECT e.Nombre,partidos_jugados AS 'Partidos jugados',goles_favor,goles_encontra 
 FROM equipo e WHERE CodEquipo =cod_equipo;*/
	
END LOOP bucle;

CLOSE equipos;

	SELECT *FROM equip order by puntos DESC;
	 
 END$$
 
DELIMITER ;
 		
	
COMMIT;
