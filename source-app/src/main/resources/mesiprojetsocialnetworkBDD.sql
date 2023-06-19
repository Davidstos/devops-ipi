CREATE DATABASE IF NOT EXISTS `socialnetwork`;

USE `socialnetwork`;

CREATE TABLE IF NOT EXISTS `role`
(
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` NVARCHAR(120)
    );

CREATE TABLE IF NOT EXISTS `user`
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
    CONSTRAINT `PK_user` PRIMARY KEY  (`id`)
);

CREATE TABLE IF NOT EXISTS `ChatGroup`
(
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` NVARCHAR(120) DEFAULT NULL
    );


CREATE TABLE IF NOT EXISTS `message`
(
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `text` NVARCHAR(120),
    `messageDate` DATETIME,
    `ChatGroup_id` INT NOT NULL,
    `userSender_id` INT NOT NULL
);




-- This is the junction table.
CREATE TABLE `ChatGroupuser` (
    `userId` INT REFERENCES `user` (`id`),
    `chatGroupId` INT REFERENCES `ChatGroup` (`id`),
    PRIMARY KEY (`userId`, `ChatGroupId`)
);

CREATE TABLE `friends` (
    `userMain` INT REFERENCES `user` (`id`),
    `userFriend` INT REFERENCES `user` (`id`),
    `name` NVARCHAR(120) DEFAULT NULL,
    PRIMARY KEY (`userMain`, `userFriend`)
);


ALTER TABLE `message` ADD CONSTRAINT `FK_messageChatGroupId`
    FOREIGN KEY (`ChatGroup_id`) REFERENCES `ChatGroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- CREATE INDEX `IFK_messagesChatGroupId` ON `messages` (`ChatGroup_id`);



ALTER TABLE `message` ADD CONSTRAINT `FK_userId`
    FOREIGN KEY (`userSender_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- CREATE INDEX `IFK_messagesuserId` ON `messages` (`userSender_id`);


ALTER TABLE `ChatGroupuser` ADD CONSTRAINT `FK_userChatId`
    FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- CREATE INDEX `IFK_userId` ON `ChatGroupuser` (`userId`);

ALTER TABLE `ChatGroupuser` ADD CONSTRAINT `FK_ChatGroupId`
    FOREIGN KEY (`chatGroupId`) REFERENCES `ChatGroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- CREATE INDEX `IFK_ChatGroupId` ON `ChatGroupuser` (`ChatGroupId`);


ALTER TABLE `friends` ADD CONSTRAINT `FK_userMain`
    FOREIGN KEY (`userMain`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `friends` ADD CONSTRAINT `FK_userFriend`
    FOREIGN KEY (`userFriend`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user` ADD CONSTRAINT `FK_userroleId`
    FOREIGN KEY (`role_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


INSERT INTO role (name) VALUES ('role_ADMIN');
INSERT INTO role (name) VALUES ('role_user');

INSERT INTO user(username, password, mail, firstName, lastName, birthDate, zipCode, address, bio, relationship,loginDate,status,role_id) VALUES ('admin','$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy','test@gmail.com','Tim','Smith','2000-07-17','11 random Street','69780','hello this is a bio','single','2022-02-13 13:00:00','online',1);
INSERT INTO user(username, password, mail, firstName, lastName, birthDate, zipCode, address, bio, relationship,loginDate,status,role_id) VALUES ('user','$2a$10$TA.UfUqLa8uDeGkt95FfLeq7T5Y5vpDpzAtvJrHSLzLliY/PARXUq','test2@gmail.com','Marc','Ray','2005-02-15','05 random Avenue','69800','hello this is a bio2','in a relationship','2022-02-13 14:00:00','do not disturb',2);

INSERT INTO ChatGroup(name) VALUES ('The Awesome Twins');

INSERT INTO message(text, messageDate, ChatGroup_id, userSender_id) VALUES ('Hey Salut Marc !','2022-02-13 13:00:00','1','1');
INSERT INTO message(text, messageDate, ChatGroup_id, userSender_id) VALUES ('Salut Tim!','2022-02-13 14:00:00','1','2');
INSERT INTO message(text, messageDate, ChatGroup_id, userSender_id) VALUES ('Ca va ?','2022-02-13 15:00:00','1','1');
INSERT INTO message(text, messageDate, ChatGroup_id, userSender_id) VALUES ('Ouais !','2022-02-13 16:00:00','1','2');


INSERT INTO ChatGroupuser(userId,ChatGroupId) VALUES (1,1);
INSERT INTO ChatGroupuser(userId,ChatGroupId) VALUES (2,1);

INSERT INTO friends(userMain,userFriend) VALUES (1,2);



/*INSERT INTO public.user (id, first_name, last_name, email, password, username, role_id) VALUES (1, 'Admin', 'Admin','admin@gmail.com', '$2a$10$bpNMKeaQXKpJ4JVxOHWvu.tZdmCLT9nKcZreJ/ELfCgmTCyhC7GPy', 'admin', 1);
INSERT INTO public.user (id, first_name, last_name, email, password, username, role_id) VALUES (2, 'user', 'user','user@gmail.com','$2a$10$TA.UfUqLa8uDeGkt95FfLeq7T5Y5vpDpzAtvJrHSLzLliY/PARXUq', 'user', 2)*/

-- INSERT INTO friends(userSenderId,userReceiverId) VALUES (2,1); un user peut mettre en ami plusieurs fois le même, a corriger
