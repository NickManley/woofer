CREATE  TABLE `woofer`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NOT NULL ,
  `text` VARCHAR(1024) NOT NULL ,
  `posted` DATETIME NOT NULL ,
  `deleted` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) );
