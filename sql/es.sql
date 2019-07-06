DROP IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `doctor_id` varchar(10) NOT NULL,
  `doctor_name` varchar(200) NOT NULL DEFAULT '',
  `doctor_title` varchar(50) DEFAULT '',
  `specialty` varchar(4000) DEFAULT '',
  `label` varchar(2000) DEFAULT '',
  `doctor_des` varchar(4000) DEFAULT '',
  `hospital_id` varchar(8) NOT NULL DEFAULT '',
  `hospital_name` varchar(200) NOT NULL,
  `bussiness_type` int(2) NOT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `hospital_index` (`hospital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
