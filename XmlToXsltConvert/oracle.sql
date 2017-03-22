CREATE TABLE developers (
     dev_id              NUMBER(10)       not null,
     dev_name            VARCHAR(32)      not null,
     CONSTRAINT pk_developers PRIMARY KEY (dev_id)
);
CREATE TABLE projects (
     pri_id               NUMBER(10)       not null,
     prj_name             VARCHAR(100)     not null,
     CONSTRAINT pk_project PRIMARY KEY (pri_id)
);
CREATE TABLE dev_prj (
  id                       NUMBER(10),
  dp_dev_id                NUMBER(10),
  dp_prj_id                NUMBER(10),
  FOREIGN KEY (dp_dev_id) REFERENCES developers (dev_id),
  FOREIGN KEY (dp_prj_id) REFERENCES projects (pri_id)
);

Задача:
1.	Вывести список проектов, в которых нет разработчиков

select p.prj_name
from projects p
full join dev_prj dp on dp.dp_prj_id=p.prj_id
where dp.dp_dev_id is null

2.	Вывести список проектов, в которых участвуют все разработчики

select p.prj_name
from projects p
full join dev_prj dp on dp.dp_prj_id=p.prj_id
group by p.prj_name
having count(dp.dp_dev_id) = (SELECT COUNT(dev_name) FROM developers)

3.	Вывести список проектов (с указанием количества разработчиков),
в которых принимает участие четное количество разработчиков и этих разработчиков больше, чем двое.

select p.prj_name, count(dp.dp_dev_id)
from projects p
  full join dev_prj dp on dp.dp_prj_id=p.prj_id
group by p.prj_name
having count(dp.dp_dev_id) > 2 and count(dp.dp_dev_id)%2 = 0


