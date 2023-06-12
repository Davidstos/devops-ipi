CREATE DATABASE IF NOT EXISTS `socialnetwork`;

USE `socialnetwork`;

CREATE TABLE IF NOT EXISTS `Role`
(
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` NVARCHAR(120)
    );

CREATE TABLE IF NOT EXISTS `User`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` NVARCHAR(160) NOT NULL,
    `password` NVARCHAR(160) NOT NULL,
    `mail` NVARCHAR(160) NOT NULL,
    `firstName` NVARCHAR(160) NOT NULL,
    `lastName` NVARCHAR(160) NOT NULL,
    `birthDate` DATE NOT NULL,
    `zipCode` NVARCHAR(160) NOT NULL,
    `address` NVARCHAR(160) NOT NULL,
    `bio` NVARCHAR(160) NOT NULL,
    `relationship` NVARCHAR(160) NOT NULL,
    `loginDate` DATETIME,
    `status` NVARCHAR(160),
    `role_id` INT,
    CONSTRAINT `PK_User` PRIMARY KEY  (`id`)
);

CREATE TABLE IF NOT EXISTS `ChatGroup`
(
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` NVARCHAR(120) DEFAULT NULL
    );


CREATE TABLE IF NOT EXISTS `Message`
(
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `text` NVARCHAR(120),
    `messageDate` DATETIME,
    `ChatGroup_id` INT NOT NULL,
    `UserSender_id` INT NOT NULL
);




-- This is the junction table.
CREATE TABLE `ChatGroupUser` (
    `userId` INT REFERENCES `User` (`id`),
    `chatGroupId` INT REFERENCES `ChatGroup` (`id`),
    PRIMARY KEY (`UserId`, `ChatGroupId`)
);

CREATE TABLE `Friends` (
    `userMain` INT REFERENCES `User` (`id`),
    `userFriend` INT REFERENCES `User` (`id`),
    `name` NVARCHAR(120) DEFAULT NULL,
    PRIMARY KEY (`userMain`, `userFriend`)
);


ALTER TABLE `Message` ADD CONSTRAINT `FK_MessageChatGroupId`
    FOREIGN KEY (`ChatGroup_id`) REFERENCES `ChatGroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- CREATE INDEX `IFK_MessagesChatGroupId` ON `Messages` (`ChatGroup_id`);



ALTER TABLE `Message` ADD CONSTRAINT `FK_UserId`
    FOREIGN KEY (`UserSender_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- CREATE INDEX `IFK_MessagesUserId` ON `Messages` (`UserSender_id`);


ALTER TABLE `ChatGroupUser` ADD CONSTRAINT `FK_UserChatId`
    FOREIGN KEY (`userId`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- CREATE INDEX `IFK_UserId` ON `ChatGroupUser` (`UserId`);

ALTER TABLE `ChatGroupUser` ADD CONSTRAINT `FK_ChatGroupId`
    FOREIGN KEY (`chatGroupId`) REFERENCES `ChatGroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- CREATE INDEX `IFK_ChatGroupId` ON `ChatGroupUser` (`ChatGroupId`);


ALTER TABLE `Friends` ADD CONSTRAINT `FK_UserMain`
    FOREIGN KEY (`userMain`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `Friends` ADD CONSTRAINT `FK_UserFriend`
    FOREIGN KEY (`userFriend`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `User` ADD CONSTRAINT `FK_UserRoleId`
    FOREIGN KEY (`role_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


INSERT INTO Role (name) VALUES ('ROLE_ADMIN');
INSERT INTO Role (name) VALUES ('ROLE_USER');

INSERT INTO User(username, password, mail, firstName, lastName, birthDate, zipCode, address, bio, relationship,loginDate,status,role_id) VALUES ('admin','$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy','test@gmail.com','Tim','Smith','2000-07-17','11 random Street','69780','hello this is a bio','single','2022-02-13 13:00:00','online',1);
INSERT INTO User(username, password, mail, firstName, lastName, birthDate, zipCode, address, bio, relationship,loginDate,status,role_id) VALUES ('user','$2a$10$TA.UfUqLa8uDeGkt95FfLeq7T5Y5vpDpzAtvJrHSLzLliY/PARXUq','test2@gmail.com','Marc','Ray','2005-02-15','05 random Avenue','69800','hello this is a bio2','in a relationship','2022-02-13 14:00:00','do not disturb',2);

INSERT INTO ChatGroup(name) VALUES ('The Awesome Twins');

INSERT INTO Message(text, messageDate, ChatGroup_id, UserSender_id) VALUES ('Hey Salut Marc !','2022-02-13 13:00:00','1','1');
INSERT INTO Message(text, messageDate, ChatGroup_id, UserSender_id) VALUES ('Salut Tim!','2022-02-13 14:00:00','1','2');
INSERT INTO Message(text, messageDate, ChatGroup_id, UserSender_id) VALUES ('Ca va ?','2022-02-13 15:00:00','1','1');
INSERT INTO Message(text, messageDate, ChatGroup_id, UserSender_id) VALUES ('Ouais !','2022-02-13 16:00:00','1','2');


INSERT INTO ChatGroupUser(UserId,ChatGroupId) VALUES (1,1);
INSERT INTO ChatGroupUser(UserId,ChatGroupId) VALUES (2,1);

INSERT INTO Friends(UserMain,UserFriend) VALUES (1,2);



/*INSERT INTO public.user (id, first_name, last_name, email, password, username, role_id) VALUES (1, 'Admin', 'Admin','admin@gmail.com', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy', 'admin', 1);
INSERT INTO public.user (id, first_name, last_name, email, password, username, role_id) VALUES (2, 'User', 'User','user@gmail.com','$2a$10$TA.UfUqLa8uDeGkt95FfLeq7T5Y5vpDpzAtvJrHSLzLliY/PARXUq', 'user', 2)*/

-- INSERT INTO Friends(UserSenderId,UserReceiverId) VALUES (2,1); un user peut mettre en ami plusieurs fois le même, a corriger
