CREATE TABLE Companies(
    Id serial PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Country varchar(255) NOT NULL,
    MarketCap real NOT NULL,
    ImageUrl varchar(255) NOT NULL,
    Description varchar(3000) NOT NULL,
    Revenue real NOT NULL,
    Earning real NOT NULL,
    MarketCapRanking int NOT NULL
);