use CupcakeShop;

insert into `CupcakeShop`.`bottom`(bottomName, price)
values ('Chocolate', 5),
('Vanilla', 5),
('Nutmeg', 5),
('Pistacio', 6),
('Almond', 7);

insert into `CupcakeShop`.`topping`(toppingName, price)
values ('Chocolate', 5),
('Blueberry', 5),
('Raspberry', 5),
('Crispy', 6),
('Strawberry', 6),
('Rum/Raisin', 7),
('Orange', 8),
('Lemon', 8),
('Blue Cheese', 9);

insert into CupcakeShop.`user`
values('hans123', 'hotdog45', 50), ('davsdu', 'nejtakdu', 100);

insert into `order`(invoice, price, `status`, `user`)
values ('Blbalbalblabla', 10, 'unfinished', 'hans123'), ('Hutlihu', 12, 'finished', 'davsdu'); 


insert into cupcakeDetails
values (1, 1, 'Chocolate', 'Chocolate'), (2, 1,'Blueberry', 'Almond');
