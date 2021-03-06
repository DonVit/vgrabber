CREATE TABLE [dbo].[anunt] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[editie_id] [int] NOT NULL ,
	[categorie_id] [int] NOT NULL ,
	[regiune_id] [int] NULL ,
	[anunt] [nvarchar] (1500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[data] [datetime] NOT NULL ,
	[interested] [bit] NOT NULL 
) ON [PRIMARY]
 

CREATE TABLE [dbo].[categorie] (
	[id] [int] NOT NULL ,
	[parent_id] [int] NULL ,
	[nume] [nvarchar] (100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[toupdate] [bit] NOT NULL 
) ON [PRIMARY]
 

CREATE TABLE [dbo].[contact] (
	[id] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[phone] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[note] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
 

CREATE TABLE [dbo].[contact_anunt] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[contact_id] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[anunt_id] [int] NOT NULL 
) ON [PRIMARY]
 

CREATE TABLE [dbo].[editie] (
	[id] [int] NOT NULL ,
	[data] [datetime] NOT NULL ,
	[note] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
 

CREATE TABLE [dbo].[regiune] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[name] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
 

CREATE TABLE [dbo].[regiune_filtru] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[regiune_id] [int] NOT NULL ,
	[name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
 

ALTER TABLE [dbo].[anunt] WITH NOCHECK ADD 
	CONSTRAINT [PK_temp] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
 

ALTER TABLE [dbo].[categorie] WITH NOCHECK ADD 
	CONSTRAINT [PK_SubCategorie] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
 

ALTER TABLE [dbo].[contact] WITH NOCHECK ADD 
	CONSTRAINT [PK_Contact] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
 

ALTER TABLE [dbo].[contact_anunt] WITH NOCHECK ADD 
	CONSTRAINT [PK_contact_anunt] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
 

ALTER TABLE [dbo].[editie] WITH NOCHECK ADD 
	CONSTRAINT [PK_Editie] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
 

ALTER TABLE [dbo].[regiune] WITH NOCHECK ADD 
	CONSTRAINT [PK_regiune] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
 

ALTER TABLE [dbo].[regiune_filtru] WITH NOCHECK ADD 
	CONSTRAINT [PK_regiune_filtru] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
 

ALTER TABLE [dbo].[anunt] ADD 
	CONSTRAINT [DF_anunt_data] DEFAULT (getdate()) FOR [data],
	CONSTRAINT [DF_anunt_interested] DEFAULT (0) FOR [interested]
 

ALTER TABLE [dbo].[categorie] ADD 
	CONSTRAINT [DF_categorie_toupdate] DEFAULT (0) FOR [toupdate]
 

ALTER TABLE [dbo].[anunt] ADD 
	CONSTRAINT [FK_anunt_Categorie] FOREIGN KEY 
	(
		[categorie_id]
	) REFERENCES [dbo].[categorie] (
		[id]
	),
	CONSTRAINT [FK_anunt_editie] FOREIGN KEY 
	(
		[editie_id]
	) REFERENCES [dbo].[editie] (
		[id]
	)
 

ALTER TABLE [dbo].[categorie] ADD 
	CONSTRAINT [FK_Categorie_Categorie] FOREIGN KEY 
	(
		[parent_id]
	) REFERENCES [dbo].[categorie] (
		[id]
	)
 

ALTER TABLE [dbo].[contact_anunt] ADD 
	CONSTRAINT [FK_contact_anunt_anunt] FOREIGN KEY 
	(
		[anunt_id]
	) REFERENCES [dbo].[anunt] (
		[id]
	),
	CONSTRAINT [FK_contact_anunt_contact] FOREIGN KEY 
	(
		[contact_id]
	) REFERENCES [dbo].[contact] (
		[id]
	)
 
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

CREATE FUNCTION fGetPath (@id int)  
RETURNS nvarchar(1000) AS  
BEGIN 
declare @parent_id int
declare @name  nvarchar(1000)
set @parent_id=(select parent_id from categorie where id=@id)
set @name= (select [nume] from categorie where id=@id)
if (@parent_id=null)
set @name=@name
else
set @name=dbo.fGetPath(@parent_id)+' '+@name
return @name
END

GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

CREATE PROCEDURE spGetNews(@id int) AS
select
contact_anunt.anunt_id, 
dbo.fgetpath((select categorie_id from anunt where id=anunt_id)), 

(select anunt from anunt where id=anunt_id)

from (
SELECT     contact_anunt.contact_id
FROM         editie INNER JOIN
                      anunt ON editie.id = anunt.editie_id INNER JOIN
                      contact_anunt ON anunt.id = contact_anunt.anunt_id
WHERE     (editie.id = @id)
GROUP BY contact_anunt.contact_id ) as x inner join contact_anunt on x.contact_id=contact_anunt.contact_id


where x.contact_id not in (

SELECT     contact_anunt.contact_id
FROM         editie INNER JOIN
                      anunt ON editie.id = anunt.editie_id INNER JOIN
                      contact_anunt ON anunt.id = contact_anunt.anunt_id
WHERE     (editie.id != @id)
GROUP BY contact_anunt.contact_id)
group by contact_anunt.anunt_id
GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO



/*
Insert of dictionary data
*/

INSERT INTO categorie(id, parent_id, nume) values (91, null, N'МУЗЫКАЛЬНЫЕ ИНСТРУМЕНТЫ')
INSERT INTO categorie(id, parent_id, nume) values (5, null, N'АУДИОТЕХНИКА')
INSERT INTO categorie(id, parent_id, nume) values (35, null, N'АПТЕКА')
INSERT INTO categorie(id, parent_id, nume) values (36, null, N'БУКИНИСТ')
INSERT INTO categorie(id, parent_id, nume) values (37, null, N'БЮРО УСЛУГ')
INSERT INTO categorie(id, parent_id, nume) values (1, null, N'SECOND HAND')
INSERT INTO categorie(id, parent_id, nume) values (56, null, N'ВИДЕОТЕХНИКА')
INSERT INTO categorie(id, parent_id, nume) values (59, null, N'ВСЕ ДЛЯ ДОМА')
INSERT INTO categorie(id, parent_id, nume) values (61, null, N'ВСЕ ДЛЯ СВАДЬБЫ')
INSERT INTO categorie(id, parent_id, nume) values (70, null, N'ВСЯКАЯ ВСЯЧИНА')
INSERT INTO categorie(id, parent_id, nume) values (71, null, N'ЗООУГОЛОК')
INSERT INTO categorie(id, parent_id, nume) values (76, null, N'ИЩУ РАБОТУ')
INSERT INTO categorie(id, parent_id, nume) values (77, null, N'КАНЦТОВАРЫ')
INSERT INTO categorie(id, parent_id, nume) values (78, null, N'КУПЛЮ')
INSERT INTO categorie(id, parent_id, nume) values (86, null, N'МЕБЕЛЬ')
INSERT INTO categorie(id, parent_id, nume) values (168, null, N'ОБУВЬ')
INSERT INTO categorie(id, parent_id, nume) values (172, null, N'ОДЕЖДА')
INSERT INTO categorie(id, parent_id, nume) values (176, null, N'ОРГТЕХНИКА')
INSERT INTO categorie(id, parent_id, nume) values (180, null, N'ПАРФЮМЕРИЯ, КОСМЕТИКА')
INSERT INTO categorie(id, parent_id, nume) values (181, null, N'ПЕРЕВОЗКИ')
INSERT INTO categorie(id, parent_id, nume) values (73, null, N'ИНФОРМБЮРО')
INSERT INTO categorie(id, parent_id, nume) values (184, null, N'ПОДАРЮ')
INSERT INTO categorie(id, parent_id, nume) values (186, null, N'ПОЗДРАВЛЕНИЯ')
INSERT INTO categorie(id, parent_id, nume) values (187, null, N'ПОЧТОВЫЙ ЯЩИК МАКЛЕРА')
INSERT INTO categorie(id, parent_id, nume) values (189, null, N'ПРЕДЛАГАЮ РАБОТУ')
INSERT INTO categorie(id, parent_id, nume) values (190, null, N'ПРОД. ПИТАНИЯ И УПАКОВКА')
INSERT INTO categorie(id, parent_id, nume) values (191, null, N'НАПИТКИ И ТАБАЧНЫЕ ИЗДЕЛИЯ')
INSERT INTO categorie(id, parent_id, nume) values (193, null, N'ПРОФЕССИОНАЛЬНАЯ СВЕТО-,ЗВУКОТЕХНИКА')
INSERT INTO categorie(id, parent_id, nume) values (198, null, N'РЕМОНТ')
INSERT INTO categorie(id, parent_id, nume) values (195, null, N'РАСТЕНИЯ')
INSERT INTO categorie(id, parent_id, nume) values (204, null, N'РАЗНОЕ ДЛЯ ДЕТЕЙ')
INSERT INTO categorie(id, parent_id, nume) values (206, null, N'СЛУЖБА ЗНАКОМСТВ "МАКЛЕРА"')
INSERT INTO categorie(id, parent_id, nume) values (215, null, N'СТИРАЛЬНЫЕ МАШИНЫ')
INSERT INTO categorie(id, parent_id, nume) values (216, null, N'СТРОЙМАТЕРИАЛЫ И ХОЗТОВАРЫ')
INSERT INTO categorie(id, parent_id, nume) values (217, null, N'ТЕЛЕВИЗОРЫ')
INSERT INTO categorie(id, parent_id, nume) values (219, null, N'ФОТОТОВАРЫ')
INSERT INTO categorie(id, parent_id, nume) values (221, null, N'ШВЕЙНЫЕ, ВЯЗАЛЬНЫЕ МАШИНЫ')
INSERT INTO categorie(id, parent_id, nume) values (222, null, N'ШИЛО НА МЫЛО (МЕНЯЮ)')
INSERT INTO categorie(id, parent_id, nume) values (223, null, N'ЭКСПРЕСС-ЗНАКОМСТВА')
INSERT INTO categorie(id, parent_id, nume) values (230, null, N'АВТОРЫНОК')
INSERT INTO categorie(id, parent_id, nume) values (231, null, N'МЕТАЛЛЫ И ИЗДЕЛИЯ')
INSERT INTO categorie(id, parent_id, nume) values (232, null, N'ОБУЧЕНИЕ И РАБОТА')
INSERT INTO categorie(id, parent_id, nume) values (233, null, N'ПУТЕШЕСТВУЙ И ОТДЫХАЙ')
INSERT INTO categorie(id, parent_id, nume) values (234, null, N'РЕКЛАМА И ИНФОРМАЦИЯ')
INSERT INTO categorie(id, parent_id, nume) values (235, null, N'С ЛЕГКИМ ПАРОМ')
INSERT INTO categorie(id, parent_id, nume) values (236, null, N'НЕДВИЖИМОСТЬ')
INSERT INTO categorie(id, parent_id, nume) values (237, null, N'ПИЛОМАТЕРИАЛЫ И ИЗДЕЛИЯ')
INSERT INTO categorie(id, parent_id, nume) values (238, null, N'ЭЛЕКТРОНИКА')
INSERT INTO categorie(id, parent_id, nume) values (212, null, N'СПОРТТОВАРЫ И СПОРТКЛУБЫ')
INSERT INTO categorie(id, parent_id, nume) values (213, null, N'СТАНКИ И ОБОРУДОВАНИЕ')
INSERT INTO categorie(id, parent_id, nume) values (227, null, N'ЭЛЕКТРОМЕЛОЧИ')
INSERT INTO categorie(id, parent_id, nume) values (244, null, N'КИШИНЕВСКАЯ АФИША')
INSERT INTO categorie(id, parent_id, nume) values (253, null, N'АГРО-МАКЛЕР')
INSERT INTO categorie(id, parent_id, nume) values (258, null, N'ОКНА, ДВЕРИ, АКСЕССУАРЫ')
INSERT INTO categorie(id, parent_id, nume) values (259, null, N'БЫТОВАЯ ХИМИЯ')
INSERT INTO categorie(id, parent_id, nume) values (268, null, N'САНТЕХНИКА, ОТОПЛЕНИЕ, ВОДОСНАБЖЕНИЕ')
INSERT INTO categorie(id, parent_id, nume) values (269, null, N'КУДА ПОЙТИ УЧИТЬСЯ')
INSERT INTO categorie(id, parent_id, nume) values (270, null, N'КАРНАВАЛЬНЫЕ КОСТЮМЫ, НОВОГОДНИЕ УКРАШЕНИЯ')
INSERT INTO categorie(id, parent_id, nume) values (249, null, N'ИЩУ ФИЛЬМ')
INSERT INTO categorie(id, parent_id, nume) values (2, 1, N'ОДЕЖДА, ОБУВЬ')
INSERT INTO categorie(id, parent_id, nume) values (3, 1, N'БЫТОВАЯ ТЕХНИКА')
INSERT INTO categorie(id, parent_id, nume) values (4, 1, N'ЭЛЕКТРОНИКА')
INSERT INTO categorie(id, parent_id, nume) values (6, 5, N'МАГНИТОФОНЫ')
INSERT INTO categorie(id, parent_id, nume) values (228, 5, N'ПРОИГРЫВАТЕЛИ')
INSERT INTO categorie(id, parent_id, nume) values (58, 37, N'МЕДИЦИНСКИЕ ТОВАРЫ И УСЛУГИ')
INSERT INTO categorie(id, parent_id, nume) values (38, 37, N'АТЕЛЬЕ МОД')
INSERT INTO categorie(id, parent_id, nume) values (39, 37, N'ПОМОЩЬ ПО ДОМУ')
INSERT INTO categorie(id, parent_id, nume) values (40, 37, N'САНТЕХРАБОТЫ')
INSERT INTO categorie(id, parent_id, nume) values (41, 37, N'ЭЛЕКТРОРАБОТЫ')
INSERT INTO categorie(id, parent_id, nume) values (42, 37, N'ОБСЛУЖИВАНИЕ ТОРЖЕСТВ')
INSERT INTO categorie(id, parent_id, nume) values (43, 37, N'СДАЮ НАПРОКАТ')
INSERT INTO categorie(id, parent_id, nume) values (44, 37, N'САЛОН КРАСОТЫ')
INSERT INTO categorie(id, parent_id, nume) values (45, 37, N'ЛЕЧЕБНЫЙ МАССАЖ')
INSERT INTO categorie(id, parent_id, nume) values (46, 37, N'РИТУАЛЬНЫЕ УСЛУГИ')
INSERT INTO categorie(id, parent_id, nume) values (47, 37, N'НЕТРАДИЦИОННАЯ МЕДИЦИНА')
INSERT INTO categorie(id, parent_id, nume) values (48, 37, N'ПРЕДСКАЗАНИЯ')
INSERT INTO categorie(id, parent_id, nume) values (49, 37, N'РЕКЛАМА И ДИЗАЙН')
INSERT INTO categorie(id, parent_id, nume) values (50, 37, N'КОМПЬЮТЕРНЫЕ, ИНТЕРНЕТ-УСЛУГИ')
INSERT INTO categorie(id, parent_id, nume) values (51, 37, N'РЕПЕТИТОРСТВО')
INSERT INTO categorie(id, parent_id, nume) values (52, 37, N'ПЕРЕВОДЫ')
INSERT INTO categorie(id, parent_id, nume) values (53, 37, N'БУХГАЛТЕРСКИЕ УСЛУГИ')
INSERT INTO categorie(id, parent_id, nume) values (54, 37, N'ЮРИДИЧЕСКИЕ УСЛУГИ')
INSERT INTO categorie(id, parent_id, nume) values (55, 37, N'РИЭЛТОРСКИЕ УСЛУГИ')
INSERT INTO categorie(id, parent_id, nume) values (57, 56, N'МАГНИТОФОНЫ')
INSERT INTO categorie(id, parent_id, nume) values (60, 59, N'КОВРЫ И ПАЛАСЫ')
INSERT INTO categorie(id, parent_id, nume) values (65, 59, N'ЭЛЕКТРОМЕЛОЧИ')
INSERT INTO categorie(id, parent_id, nume) values (66, 59, N'ПЛИТЫ')
INSERT INTO categorie(id, parent_id, nume) values (67, 59, N'ПЕЧИ')
INSERT INTO categorie(id, parent_id, nume) values (68, 59, N'ХОЛОДИЛЬНИКИ')
INSERT INTO categorie(id, parent_id, nume) values (69, 59, N'МОРОЗИЛЬНИКИ')
INSERT INTO categorie(id, parent_id, nume) values (229, 59, N'ТКАНИ, ПОСТЕЛЬНОЕ БЕЛЬЕ')
INSERT INTO categorie(id, parent_id, nume) values (194, 59, N'ПЫЛЕСОСЫ')
INSERT INTO categorie(id, parent_id, nume) values (62, 61, N'СВАДЕБНЫЕ НАРЯДЫ')
INSERT INTO categorie(id, parent_id, nume) values (63, 61, N'СВАДЕБНЫЕ АКСЕССУАРЫ')
INSERT INTO categorie(id, parent_id, nume) values (72, 71, N'РАЗНОЕ')
INSERT INTO categorie(id, parent_id, nume) values (74, 73, N'СЧИТАТЬ НЕДЕЙСТВИТЕЛЬНЫМ')
INSERT INTO categorie(id, parent_id, nume) values (75, 73, N'БЮРО НАХОДОК')
INSERT INTO categorie(id, parent_id, nume) values (79, 78, N'МЕДИКАМЕНТЫ')
INSERT INTO categorie(id, parent_id, nume) values (80, 78, N'ОРГТЕХНИКУ')
INSERT INTO categorie(id, parent_id, nume) values (81, 78, N'TV, VIDEO, AUDIO')
INSERT INTO categorie(id, parent_id, nume) values (82, 78, N'МЕБЕЛЬ')
INSERT INTO categorie(id, parent_id, nume) values (83, 78, N'БЫТОВУЮ ТЕХНИКУ')
INSERT INTO categorie(id, parent_id, nume) values (84, 78, N'НЕДВИЖИМОСТЬ')
INSERT INTO categorie(id, parent_id, nume) values (85, 78, N'АВТО')
INSERT INTO categorie(id, parent_id, nume) values (87, 86, N'КУХНИ')
INSERT INTO categorie(id, parent_id, nume) values (88, 86, N'СТЕНКИ')
INSERT INTO categorie(id, parent_id, nume) values (89, 86, N'СПАЛЬНИ')
INSERT INTO categorie(id, parent_id, nume) values (90, 86, N'МЯГКАЯ МЕБЕЛЬ')
INSERT INTO categorie(id, parent_id, nume) values (169, 168, N'ДЛЯ ЖЕНЩИН')
INSERT INTO categorie(id, parent_id, nume) values (170, 168, N'ДЛЯ МУЖЧИН')
INSERT INTO categorie(id, parent_id, nume) values (171, 168, N'ДЛЯ ДЕТЕЙ')
INSERT INTO categorie(id, parent_id, nume) values (173, 172, N'ДЛЯ ЖЕНЩИН')
INSERT INTO categorie(id, parent_id, nume) values (174, 172, N'ДЛЯ МУЖЧИН')
INSERT INTO categorie(id, parent_id, nume) values (175, 172, N'ДЛЯ ДЕТЕЙ')
INSERT INTO categorie(id, parent_id, nume) values (177, 176, N'КОМПЬЮТЕРЫ И ПРОГРАММЫ')
INSERT INTO categorie(id, parent_id, nume) values (178, 176, N'ТЕЛЕФОННЫЕ АППАРАТЫ')
INSERT INTO categorie(id, parent_id, nume) values (179, 176, N'ПИШУЩИЕ МАШИНКИ')
INSERT INTO categorie(id, parent_id, nume) values (182, 181, N'ГРУЗОВЫЕ')
INSERT INTO categorie(id, parent_id, nume) values (183, 181, N'ПАССАЖИРСКИЕ')
INSERT INTO categorie(id, parent_id, nume) values (185, 184, N'ПРИМУ В ДАР')
INSERT INTO categorie(id, parent_id, nume) values (196, 195, N'СЕМЕНА')
INSERT INTO categorie(id, parent_id, nume) values (197, 195, N'УДОБРЕНИЯ')
INSERT INTO categorie(id, parent_id, nume) values (199, 198, N'АВТО')
INSERT INTO categorie(id, parent_id, nume) values (200, 198, N'ЭЛЕКТРОНИКИ')
INSERT INTO categorie(id, parent_id, nume) values (201, 198, N'БЫТОВОЙ ТЕХНИКИ')
INSERT INTO categorie(id, parent_id, nume) values (202, 198, N'ЖИЛЬЯ')
INSERT INTO categorie(id, parent_id, nume) values (203, 198, N'МЕБЕЛИ')
INSERT INTO categorie(id, parent_id, nume) values (205, 204, N'КОЛЯСКИ')
INSERT INTO categorie(id, parent_id, nume) values (207, 206, N'ПОИСК СЕРЬЕЗНОГО ПАРТНЕРА')
INSERT INTO categorie(id, parent_id, nume) values (208, 206, N'ПОИСК ИНТЕРЕСНОГО ПАРТНЕРА')
INSERT INTO categorie(id, parent_id, nume) values (209, 206, N'ПОИСК СЕКСУАЛЬНОГОГО ПАРТНЕРА')
INSERT INTO categorie(id, parent_id, nume) values (210, 206, N'ПОИСК СОСТОЯТЕЛЬНОГО ПАРТНЕРА')
INSERT INTO categorie(id, parent_id, nume) values (211, 206, N'ПОИСК ИДЕАЛЬНОГО ПАРТНЕРА')
INSERT INTO categorie(id, parent_id, nume) values (214, 213, N'ТОРГОВОЕ ОБОРУДОВАНИЕ')
INSERT INTO categorie(id, parent_id, nume) values (218, 217, N'КОМПЛЕКТУЮЩИЕ')
INSERT INTO categorie(id, parent_id, nume) values (224, 223, N'ИЩУ ДРУЗЕЙ')
INSERT INTO categorie(id, parent_id, nume) values (225, 223, N'БРАЧНЫЕ СООБЩЕНИЯ')
INSERT INTO categorie(id, parent_id, nume) values (226, 223, N'ДЕЛОВОЕ ПАРТНЕРСТВО')
INSERT INTO categorie(id, parent_id, nume) values (239, 230, N'  ДРУГИЕ')
INSERT INTO categorie(id, parent_id, nume) values (240, 230, N'АВТОБУСЫ')
INSERT INTO categorie(id, parent_id, nume) values (241, 230, N'ВНЕДОРОЖНИКИ')
INSERT INTO categorie(id, parent_id, nume) values (242, 230, N'МОТОТРАНСПОРТ')
INSERT INTO categorie(id, parent_id, nume) values (243, 230, N'ПРИЦЕПЫ')
INSERT INTO categorie(id, parent_id, nume) values (8, 230, N'СЕЛЬХОЗМАШИНЫ')
INSERT INTO categorie(id, parent_id, nume) values (9, 230, N'  ВАЗ')
INSERT INTO categorie(id, parent_id, nume) values (10, 230, N'  SKODA')
INSERT INTO categorie(id, parent_id, nume) values (11, 230, N'  АЗЛК, ИЖ')
INSERT INTO categorie(id, parent_id, nume) values (12, 230, N'  ГАЗ')
INSERT INTO categorie(id, parent_id, nume) values (13, 230, N'  ЗАЗ')
INSERT INTO categorie(id, parent_id, nume) values (14, 230, N'ГРУЗОВИКИ')
INSERT INTO categorie(id, parent_id, nume) values (15, 230, N'ЛЕГКОВЫЕ АВТОМОБИЛИ')
INSERT INTO categorie(id, parent_id, nume) values (16, 230, N'  AUDI')
INSERT INTO categorie(id, parent_id, nume) values (17, 230, N'  BMW')
INSERT INTO categorie(id, parent_id, nume) values (18, 230, N'  FIAT')
INSERT INTO categorie(id, parent_id, nume) values (19, 230, N'  FORD')
INSERT INTO categorie(id, parent_id, nume) values (20, 230, N'  HONDA')
INSERT INTO categorie(id, parent_id, nume) values (21, 230, N'  MAZDA')
INSERT INTO categorie(id, parent_id, nume) values (22, 230, N'  MERCEDES')
INSERT INTO categorie(id, parent_id, nume) values (23, 230, N'  MITSUBISHI')
INSERT INTO categorie(id, parent_id, nume) values (24, 230, N'  NISSAN')
INSERT INTO categorie(id, parent_id, nume) values (25, 230, N'  OPEL')
INSERT INTO categorie(id, parent_id, nume) values (26, 230, N'  PEUGEOT')
INSERT INTO categorie(id, parent_id, nume) values (27, 230, N'  RENAULT')
INSERT INTO categorie(id, parent_id, nume) values (28, 230, N'  TOYOTA')
INSERT INTO categorie(id, parent_id, nume) values (29, 230, N'  VOLKSWAGEN')
INSERT INTO categorie(id, parent_id, nume) values (30, 230, N'  VOLVO')
INSERT INTO categorie(id, parent_id, nume) values (31, 230, N'АВТОКОМПЛЕКТУЮЩИЕ ПРОИЗВОДСТВА СТРАН СНГ')
INSERT INTO categorie(id, parent_id, nume) values (32, 230, N'АВТОКОМПЛЕКТУЮЩИЕ ЗАРУБЕЖНОГО ПРОИЗВОДСТВА')
INSERT INTO categorie(id, parent_id, nume) values (33, 230, N'АВТО (ВОЗЬМУ В АРЕНДУ)')
INSERT INTO categorie(id, parent_id, nume) values (34, 230, N'АВТО (СДАЮ В АРЕНДУ)')
INSERT INTO categorie(id, parent_id, nume) values (92, 236, N'НЕДВИЖИМОСТЬ (МЕНЯЮ)')
INSERT INTO categorie(id, parent_id, nume) values (93, 236, N'  ПО КИШИНЕВУ')
INSERT INTO categorie(id, parent_id, nume) values (94, 236, N'  ПО МОЛДОВЕ')
INSERT INTO categorie(id, parent_id, nume) values (95, 236, N'  ЗА РУБЕЖОМ')
INSERT INTO categorie(id, parent_id, nume) values (96, 236, N'НЕДВИЖИМОСТЬ (СНИМУ)')
INSERT INTO categorie(id, parent_id, nume) values (97, 236, N'НЕДВИЖИМОСТЬ (ПРОДАЮ)')
INSERT INTO categorie(id, parent_id, nume) values (98, 236, N'  КВАРТИРЫ')
INSERT INTO categorie(id, parent_id, nume) values (99, 236, N'    1-КОМНАТНЫЕ')
INSERT INTO categorie(id, parent_id, nume) values (100, 236, N'      ЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (101, 236, N'      БОТАНИКА')
INSERT INTO categorie(id, parent_id, nume) values (102, 236, N'      БУЮКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (103, 236, N'      РЫШКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (104, 236, N'      ПОШТА ВЕКЕ')
INSERT INTO categorie(id, parent_id, nume) values (105, 236, N'      СКУЛЕНЬ')
INSERT INTO categorie(id, parent_id, nume) values (106, 236, N'      ЧОКАНА')
INSERT INTO categorie(id, parent_id, nume) values (107, 236, N'      ТЕЛЕЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (108, 236, N'      ДРУГИЕ РАЙОНЫ КИШИНЕВА')
INSERT INTO categorie(id, parent_id, nume) values (109, 236, N'      ДРУГИЕ НАСЕЛЕННЫЕ ПУНКТЫ')
INSERT INTO categorie(id, parent_id, nume) values (110, 236, N'    2-КОМНАТНЫЕ')
INSERT INTO categorie(id, parent_id, nume) values (111, 236, N'      ЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (112, 236, N'      БОТАНИКА')
INSERT INTO categorie(id, parent_id, nume) values (113, 236, N'      БУЮКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (114, 236, N'      РЫШКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (115, 236, N'      ПОШТА ВЕКЕ')
INSERT INTO categorie(id, parent_id, nume) values (116, 236, N'      СКУЛЕНЬ')
INSERT INTO categorie(id, parent_id, nume) values (117, 236, N'      ЧОКАНА')
INSERT INTO categorie(id, parent_id, nume) values (118, 236, N'      ТЕЛЕЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (119, 236, N'      ДРУГИЕ РАЙОНЫ КИШИНЕВА')
INSERT INTO categorie(id, parent_id, nume) values (120, 236, N'      ДРУГИЕ НАСЕЛЕННЫЕ ПУНКТЫ')
INSERT INTO categorie(id, parent_id, nume) values (121, 236, N'    3-КОМНАТНЫЕ')
INSERT INTO categorie(id, parent_id, nume) values (122, 236, N'      ЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (123, 236, N'      БОТАНИКА')
INSERT INTO categorie(id, parent_id, nume) values (124, 236, N'      БУЮКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (125, 236, N'      РЫШКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (126, 236, N'      ПОШТА ВЕКЕ')
INSERT INTO categorie(id, parent_id, nume) values (127, 236, N'      СКУЛЕНЬ')
INSERT INTO categorie(id, parent_id, nume) values (128, 236, N'      ЧОКАНА')
INSERT INTO categorie(id, parent_id, nume) values (129, 236, N'      ТЕЛЕЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (130, 236, N'      ДРУГИЕ РАЙОНЫ КИШИНЕВА')
INSERT INTO categorie(id, parent_id, nume) values (131, 236, N'      ДРУГИЕ НАСЕЛЕННЫЕ ПУНКТЫ')
INSERT INTO categorie(id, parent_id, nume) values (132, 236, N'    4-КОМНАТНЫЕ И БОЛЕЕ')
INSERT INTO categorie(id, parent_id, nume) values (133, 236, N'      ЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (134, 236, N'      БОТАНИКА')
INSERT INTO categorie(id, parent_id, nume) values (135, 236, N'      БУЮКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (136, 236, N'      РЫШКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (137, 236, N'      ПОШТА ВЕКЕ')
INSERT INTO categorie(id, parent_id, nume) values (138, 236, N'      СКУЛЕНЬ')
INSERT INTO categorie(id, parent_id, nume) values (139, 236, N'      ЧОКАНА')
INSERT INTO categorie(id, parent_id, nume) values (140, 236, N'      ТЕЛЕЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (141, 236, N'      ДРУГИЕ РАЙОНЫ КИШИНЕВА')
INSERT INTO categorie(id, parent_id, nume) values (142, 236, N'      ДРУГИЕ НАСЕЛЕННЫЕ ПУНКТЫ')
INSERT INTO categorie(id, parent_id, nume) values (143, 236, N'  ДОМА')
INSERT INTO categorie(id, parent_id, nume) values (144, 236, N'    ЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (145, 236, N'    БОТАНИКА')
INSERT INTO categorie(id, parent_id, nume) values (146, 236, N'    БУЮКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (147, 236, N'    РЫШКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (148, 236, N'    ПОШТА ВЕКЕ')
INSERT INTO categorie(id, parent_id, nume) values (149, 236, N'    СКУЛЕНЬ')
INSERT INTO categorie(id, parent_id, nume) values (150, 236, N'    ЧОКАНА')
INSERT INTO categorie(id, parent_id, nume) values (151, 236, N'    ТЕЛЕЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (152, 236, N'    ДРУГИЕ РАЙОНЫ КИШИНЕВА')
INSERT INTO categorie(id, parent_id, nume) values (153, 236, N'    ДРУГИЕ НАСЕЛЕННЫЕ ПУНКТЫ')
INSERT INTO categorie(id, parent_id, nume) values (154, 236, N'  ДАЧИ')
INSERT INTO categorie(id, parent_id, nume) values (155, 236, N'  УЧАСТКИ')
INSERT INTO categorie(id, parent_id, nume) values (156, 236, N'  ГАРАЖИ')
INSERT INTO categorie(id, parent_id, nume) values (157, 236, N'НЕДВИЖИМОСТЬ (СДАЮ)')
INSERT INTO categorie(id, parent_id, nume) values (158, 236, N'  ЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (159, 236, N'  БОТАНИКА')
INSERT INTO categorie(id, parent_id, nume) values (160, 236, N'  БУЮКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (161, 236, N'  РЫШКАНЬ')
INSERT INTO categorie(id, parent_id, nume) values (162, 236, N'  ПОШТА ВЕКЕ')
INSERT INTO categorie(id, parent_id, nume) values (163, 236, N'  СКУЛЕНЬ')
INSERT INTO categorie(id, parent_id, nume) values (164, 236, N'  ЧОКАНА')
INSERT INTO categorie(id, parent_id, nume) values (165, 236, N'  ТЕЛЕЧЕНТРУ')
INSERT INTO categorie(id, parent_id, nume) values (166, 236, N'  ДРУГИЕ РАЙОНЫ КИШИНЕВА')
INSERT INTO categorie(id, parent_id, nume) values (167, 236, N'  ДРУГИЕ НАСЕЛЕННЫЕ ПУНКТЫ')
INSERT INTO categorie(id, parent_id, nume) values (264, 236, N'  НЕЖИЛОЙ ФОНД')
INSERT INTO categorie(id, parent_id, nume) values (265, 244, N'ЭКСКУРСИИ')
INSERT INTO categorie(id, parent_id, nume) values (266, 244, N'  УСЛУГИ')
INSERT INTO categorie(id, parent_id, nume) values (267, 244, N'КОНЦЕРТЫ, ГАСТРОЛИ')
INSERT INTO categorie(id, parent_id, nume) values (250, 244, N'  АВАРИЙНЫЕ СЛУЖБЫ')
INSERT INTO categorie(id, parent_id, nume) values (251, 244, N'  ДРУГИЕ ИНФОРМАЦИОННЫЕ СЛУЖБЫ')
INSERT INTO categorie(id, parent_id, nume) values (245, 244, N'СПОРТКЛУБЫ')
INSERT INTO categorie(id, parent_id, nume) values (246, 244, N'ЭКСТРЕННЫЕ ТЕЛЕФОНЫ')
INSERT INTO categorie(id, parent_id, nume) values (247, 244, N'АФИША КЛУБОВ')
INSERT INTO categorie(id, parent_id, nume) values (248, 244, N'РЕСТОРАНЫ,БАРЫ, и ПРОЧЕЕ')
INSERT INTO categorie(id, parent_id, nume) values (260, 244, N'КИНОАФИША')
INSERT INTO categorie(id, parent_id, nume) values (261, 244, N'ВЫСТАВКИ')
INSERT INTO categorie(id, parent_id, nume) values (262, 244, N'ТЕАТРЫ')
INSERT INTO categorie(id, parent_id, nume) values (263, 244, N'МУЗЕИ')
INSERT INTO categorie(id, parent_id, nume) values (254, 244, N'  ТЕЛЕФОННЫЕ СПРАВОЧНЫЕ СЛУЖБЫ')
INSERT INTO categorie(id, parent_id, nume) values (255, 244, N'  ИНФОРМАЦИОННЫЕ СЛУЖБЫ')
INSERT INTO categorie(id, parent_id, nume) values (256, 244, N'  ТАКСИ')
INSERT INTO categorie(id, parent_id, nume) values (257, 244, N'  ДОСТАВКА')
INSERT INTO categorie(id, parent_id, nume) values (271, 270, N'ПРОДАЮ')
INSERT INTO categorie(id, parent_id, nume) values (272, 270, N'СДАЮ НАПРОКАТ')
INSERT INTO categorie(id, parent_id, nume) values (273, 270, N'ВОЗЬМУ НАПРОКАТ')
INSERT INTO categorie(id, parent_id, nume) values (274, 270, N'КУПЛЮ')

