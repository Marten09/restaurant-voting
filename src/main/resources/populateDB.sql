DELETE FROM user_role;
DELETE FROM dish;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurants(name)
VALUES ('Лисичка'),
       ('Тетерев'),
       ('Онегин Дача');

INSERT INTO dish (description, price, registered, restaurant_id)
VALUES ('пюре', 500, '2020-01-23', 100003),
       ('Шашлык', 1000, '2020-01-24', 100003),
       ('Блины', 400, '2020-01-25',100003),
       ('Пельмени', 250, '2020-01-26', 100003),
       ('Лосось в кляре', 900, '2020-01-20',100004),
       ('Запеканка', 150, '2020-01-27',100004),
       ('Форель', 810, '2020-01-28',100004),
       ('Овощи на гриле', 410, '2020-01-29',100005),
       ('Шаурма', 280, '2020-01-30',100005);