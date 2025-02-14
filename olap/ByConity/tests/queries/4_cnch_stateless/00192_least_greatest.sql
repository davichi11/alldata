SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT 1 AS x, 2 AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT 1.1 AS x, 2 AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT -1 AS x, 2 AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT 1.0 AS x, 2.0 AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT 1 AS x, 2000 AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT 1 AS x, 200000 AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT 1 AS x, 20000000000 AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT 123 AS x, 123 AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT toDate('2010-01-02') AS x, toDate('2011-02-03') AS y) ;
SELECT x, y, least(x, y), greatest(x, y), least(x, materialize(y)), greatest(materialize(x), y), greatest(materialize(x), materialize(y)), toTypeName(least(x, y)) FROM (SELECT toDateTime('2010-01-02 03:04:05') AS x, toDateTime('2011-02-03 04:05:06') AS y) ;
SELECT greatest(now(), now() + 10) - now();
SELECT greatest(today(), yesterday() + 10) - today();
