CREATE TABLE `Counters` (
    `id` int(11) NOT NULL AUTO_INCREMENT, `count` int(11) NOT NULL DEFAULT '1', `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `Classes` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `class_id` int(11) NOT NULL,
    `class_name` varchar(255) NOT NULL,
    `teacher_id` int(11) NOT NULL,
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Students` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `student_id` int(11) NOT NULL,
    `student_name` varchar(255) NOT NULL,
    `open_id`  varchar(255)  NOT NULL,
    `student_tel` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Teachers` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `teacher_id` int(11) NOT NULL,
    `teacher_name` varchar(255) NOT NULL,
    `open_id`  varchar(255)  NOT NULL,
    `teacher_tel` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `StudentClass` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `student_id` int(11) NOT NULL ,
    `class_id` int(11) NOT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*分割*/
CREATE TABLE `Papers` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `test_id` int(11) NOT NULL,
     `test_name` varchar(255) NOT NULL ,
     `question_id` int(11) NOT NULL,
     `score` int(11) NOT NULL,
     `teacher_id` int(11)  NOT NULL,
     `class_id` int(11)    NOT NULL,
     `status_id` SMALLINT(3) NOT NULL,
     `time` int(11) NOT NULL
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Answers` (
    `id` int(11) NOT NULL AUTO_INCREMENT ,
    `question_id` int(11) NOT NULL ,
    `test_id` int(11) NOT NULL,
    `student_id` int(11) NOT NULL,
    `start_time` time NOT NULL,
    `end_time` time NOT NULL,
    `stu_answer_content` varchar(255) NOT NULL ,
    `answer_content` varchar(255) NOT NULL ,
    `score` int(11) NOT NULL,
    `status` smallint(3) NOT NULL,
    `get_score` int(11) default 0 NOT NULL,
    `time` int(11) NOT NULL,
    PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Questions` (
     `id` int(11) NOT NULL AUTO_INCREMENT ,
     `question_describe` text NOT NULL ,
     `type` varchar(255) NOT NULL ,
     `answer_content` varchar(255) NOT NULL ,
     `other_answer` varchar(255) NOT NULL ,
     `disorder` tinyint(1) NOT NULL,
     `key` text not null ,
     `source` tinyint(1) not null ,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Keywords` (
    `id` int(11) NOT NULL AUTO_INCREMENT ,
    `key` varchar(233) not null ,
    PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
