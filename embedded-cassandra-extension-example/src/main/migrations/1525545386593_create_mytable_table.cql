-- description: creates events table
-- authoredAt: 1525545386593

-- up:
create table mytable (id text primary key, value timestamp)

-- down:
drop table mytable