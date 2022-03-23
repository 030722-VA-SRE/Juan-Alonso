drop table if exists items;

create table if not exists items(
id serial primary key,
item_name varchar(20) not null,
value integer not null,
description varchar(100) not null 
);


	--IF CHANGES ARE MADE, MAKE SURE CONSOLE REPO MATCHES FOR UNIT TESTING
insert into items(item_name, value, description) values 
	('Deku Stick', 10, 'A long branch gathered from the Great Deku Tree.'),
	('Deku Seeds', 30, 'Can be used as slingshot ammo. Set of 30.'),
	('Deku Shield', 40, 'Shield made from the Great Deku Tree material.'),
	('Bombs', 30, 'Explosives hatched from Goron Plants. Set of 10'),
	('Fairy', 50, 'Can bring back to life or cure illness.'),
	('Arrows', 30, 'You can shoot these if you have a bow. Set of 30'),
	('Magic Bean', 100, 'These beans are magical! Only for sale to bean experts.'),
	('Bombchu', 50, 'These are small bombs that run along the ground.'),
	('Milk', 20, 'Heals you for 8 hearts. Contains 2 servings. Need an empty bottle to carry.'),
	('Chateau Romani', 200, 'Vintage milk produced by specially-bred cows. Need an empty bottle.');


	('Red Potion', 25, 'Refills your health entirely. Need an empty bottle to carry.'),
	('Green Potion', 20, 'Refills your magic. Need an empty bottle to carry.'),
	('Blue Potion', 60, 'Refills both your health and magic. Need an empty bottle to carry.');
	
	


-----------------------------------------------------------------------
//used to test adding and restarting id serial sequence 

delete from items where id = 5;

//reset sequence to specified number
alter sequence items_id_seq restart with 5; 

update items set item_name = 'blank', value = 2, description = 'testing' where id = 5;

select * from items where item_name like '%Deku Stick%';