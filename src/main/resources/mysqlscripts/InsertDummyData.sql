INSERT INTO Task (task_Id,task_Name,estimated_Hours,years_To_Expiry_Date) VALUES (1,'Voorzitter Examencommissie',32,3);
INSERT INTO Task (task_Id,task_Name,estimated_Hours,years_To_Expiry_Date) VALUES (2,'Lid BibliotheekCommissie',16,3);
INSERT INTO Task (task_Id,task_Name,estimated_Hours,years_To_Expiry_Date) VALUES (3,'Begeleiding',8,3);

INSERT INTO `mijn_inzet`.`subject` (`subject_id`, `estimated_hours`, `subject_name`, `years_to_expiry_date`) VALUES ('1', '20', 'Programming', '2');
INSERT INTO `mijn_inzet`.`subject` (`subject_id`, `estimated_hours`, `subject_name`, `years_to_expiry_date`) VALUES ('2', '30', 'OOP', '2');
INSERT INTO `mijn_inzet`.`subject` (`subject_id`, `estimated_hours`, `subject_name`, `years_to_expiry_date`) VALUES ('3', '20', 'Databases', '2');

INSERT INTO role VALUES (1,'ADMIN_USER','This user has admin rights for administrative work');
INSERT INTO role VALUES (2,'TEACHER','teacher');
INSERT INTO role VALUES (3,'COORDINATOR','coordinator');
INSERT INTO role VALUES (4,'SCHEDULER','This user has admin rights for administrative work');
INSERT INTO role VALUES (5,'MANAGER','This user has admin rights for administrative work');

insert into user (user_id,first_name,last_name,email,password,status) values (1,'hans','zuidervaart','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED');
insert into user_role (user_id, role_id) values ('1','1');
