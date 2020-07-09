(require '[next.jdbc :as jdbc])

(def ds
  (jdbc/get-datasource {:dbtype "h2" :dbname "people"}))

(jdbc/execute! ds ["
create table people (
  id int auto_increment primary key,
  fname varchar(32),
  lname varchar (32),
  email varchar(255)
)"])

  (jdbc/execute! ds ["
insert into people(fname,lname,email) values
  ('Rich', 'Hickey','rich@example.com'),
  ('Carin', 'Meier','gigasquid@example.com'),
  ('Stu', 'Halloway','stu@example.com'),
  ('Zach', 'Tellman','zach@example.com')"])
