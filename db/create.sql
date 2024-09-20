 SELECT 'CREATE DATABASE compose'
 WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'compose')\gexec