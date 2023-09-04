INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest'),
       ('alex', 'alex@yandex.ru', '{noop}password');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 2),
       ('USER', 4);

INSERT INTO RESTAURANT(name)
VALUES ('Лисичка'),
       ('Нияма'),
       ('Онегин Дача');

INSERT INTO DISH (description, price, registered, restaurant_id)
VALUES ('Пюре', 500, now() + 1, 1),
       ('Шашлык', 1000, now() + 1, 1),
       ('Блины', 400, now() + 1, 1),
       ('Пельмени', 250, now() + 1, 1),
       ('Лосось в кляре', 900, now() + 1, 1),
       ('Запеканка', 150, now(), 1),
       ('Форель', 810, now(), 1),
       ('Овощи на гриле', 410, now(), 1),
       ('Шаурма', 280, now(), 1),
       ('Пюре', 150, now(), 2),
       ('Шашлык', 1000, now() + 1, 2),
       ('Блины', 230, now() + 1, 2),
       ('Пельмени', 250, now() + 1, 2),
       ('Лосось в кляре', 900, now(), 2),
       ('Запеканка', 150, now(), 2),
       ('Форель', 810, now(), 3),
       ('Овощи на гриле', 410, now(), 3),
       ('Шаурма с овощами', 220, now(), 3),
       ('Шашлык с овощами', 1000, now(), 3),
       ('Блины с мясом', 400, now(), 3),
       ('Хинкали', 250, now(), 3);

INSERT INTO VOTE(vote_date, vote_time, user_id, restaurant_id)
VALUES (now(), '11:00', 1, 1),
       (now(), '13:00', 2, 3);