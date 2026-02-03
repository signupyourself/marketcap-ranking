CREATE TABLE Countries(
    Id serial PRIMARY KEY,
    Country varchar(255) NOT NULL,
    MarketCap real NOT NULL,
    Revenue real NOT NULL,
    Earning real NOT NULL,
    MarketCapRanking int NOT NULL
);