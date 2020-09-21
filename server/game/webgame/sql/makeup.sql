REPLACE INTO tb_constant(`key`,`value`) VALUES ('version','1.1380');
ALTER TABLE `tb_single_ins` ADD `actPlayTime` TEXT NOT NULL ;
UPDATE `tb_single_ins` SET `actPlayTime` = '{}';
ALTER TABLE `tb_task` ADD `findRewardMax` TEXT NOT NULL ;
UPDATE `tb_task` SET `findRewardMax` = '{}';
ALTER TABLE `tb_welfare` ADD `backGiftId` INT(11) NOT NULL DEFAULT '0';