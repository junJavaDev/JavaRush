ALTER TABLE employee
    ADD COLUMN weight FLOAT;

SELECT *
    FROM employee;

INSERT INTO employee (name, weight)
    VALUES
        ('Vasya', 73.3),
        ('Pasha', 0.0),
        ('Sasha', 40.2),
        ('Dima', null),
        ('Sasha', null);

ALTER TABLE `test`.`employee`
    CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT ;