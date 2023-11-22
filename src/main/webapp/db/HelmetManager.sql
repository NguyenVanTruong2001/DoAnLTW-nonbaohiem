create database HelmetManager;
use HelmetManager;

create table Users(
	`UserID` int auto_increment not null primary key,
    `Username` varchar(25) character set utf8mb4 not null,
    `Email` varchar(100) character set utf8mb4 not null,
    `Password` varchar(25) character set utf8mb4 not null,
    `Role` enum('User', 'Admin') default 'User'
);

insert into Users(`Username`, `Email`, `Password`, `Role`) values
('Admin', 'admin@gmail.com', 'admin', 'Admin');
insert into Users(`Username`, `Email`, `Password`) values
('TigerNixon', 'tigernixon@gmail.com', 'nixon'),
('GarrettWinters', 'garrettwinters@gmail.com', 'winters');

select * from Users;
drop table Users;