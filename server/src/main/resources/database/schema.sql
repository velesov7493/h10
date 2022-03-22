CREATE TABLE tz_journals (
    id INTEGER PRIMARY KEY IDENTITY,
    name VARCHAR(250) NOT NULL UNIQUE
);

CREATE TABLE tz_packets (
    id BIGINT PRIMARY KEY,
    is_sent BOOLEAN DEFAULT false,
    imei CHAR(15) NOT NULL,
    command CHAR(4) NOT NULL,
    id_journal INTEGER REFERENCES tz_journals (id) ON DELETE CASCADE,
    seq_number INTEGER,
    seq_total INTEGER,
    data TEXT NOT NULL,
    voice BLOB
);

CREATE TABLE tz_voices (
    id INTEGER PRIMARY KEY IDENTITY,
    is_sent BOOLEAN DEFAULT false,
    imei CHAR(15) NOT NULL,
    duration INTEGER NOT NULL,
    created TIMESTAMP DEFAULT current_timestamp(),
    data BLOB
);