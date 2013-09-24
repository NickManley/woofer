CREATE  TABLE `woofer`.`users` (
  `username` VARCHAR(20) NOT NULL ,
  `email` VARCHAR(128) NOT NULL ,
  `birthdate` DATE NOT NULL ,
  `last_login` DATETIME NOT NULL ,
  `account_created` DATETIME NOT NULL ,
  `salt` VARCHAR(16) NOT NULL ,
  `password` VARCHAR(4096) NOT NULL ,
  PRIMARY KEY (`username`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) );