create table if not exists memo_information(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    memo_name TEXT NOT NULL UNIQUE,
    memo TEXT,
    registered_datetime TEXT NOT NULL,
    updated_datetime TEXT NOT NULL
)
;