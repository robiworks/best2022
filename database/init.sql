GRANT ALL PRIVILEGES ON DATABASE aquasystems TO aquasystems;

CREATE TABLE IF NOT EXISTS logs (
    log_date    DATE,
    log_time    TIME,
    log_value   DOUBLE PRECISION,
    log_flag    BOOLEAN,
    PRIMARY KEY (log_date, log_time)
);

COPY logs(log_date, log_time, log_value) FROM '/usr/src/app/database.txt' (FORMAT CSV, DELIMITER('|'));