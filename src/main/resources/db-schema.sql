set mode MySQL;

CREATE TABLE IF NOT EXISTS user_preferences(
    id VARCHAR(25) PRIMARY KEY,
    value VARCHAR(25)
);