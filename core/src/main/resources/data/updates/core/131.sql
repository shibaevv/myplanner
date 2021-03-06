--drop table AssetAllocation;

CREATE TABLE AssetAllocation
(
	AssetAllocationID 	int
#ifdef MSSQL
    IDENTITY (1,1) NOT NULL,
#endif MSSQL
#ifdef HSQLDB
    GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
#endif HSQLDB
	Amount 		numeric(15,4),
	InCash 		numeric(15,4),
	InFixedInterest 	numeric(15,4),
	InAustShares 		numeric(15,4),
	InIntnlShares 	numeric(15,4),
	InProperty 		numeric(15,4),
	Include 		char
);
#ifdef MSSQL
	ALTER TABLE AssetAllocation ADD CONSTRAINT PK_AssetAllocationID PRIMARY KEY (AssetAllocationID)
#endif MSSQL
GO

ALTER TABLE Financial ADD AssetAllocationID int NULL;
ALTER TABLE Financial ADD
  CONSTRAINT FK_Financial_AssetAllocation FOREIGN KEY (AssetAllocationID) REFERENCES AssetAllocation (AssetAllocationID);

ALTER TABLE StrategyModel ADD AssetAllocationID int NULL;
ALTER TABLE StrategyModel ADD
  CONSTRAINT FK_StrategyModel_AssetAllocation FOREIGN KEY (AssetAllocationID) REFERENCES AssetAllocation (AssetAllocationID);

--
-- this should be the last statement in any update script
--
INSERT INTO DBVersion (CurrentVersion, PreviousVersion)
VALUES ('FPS.01.31', 'FPS.01.30');
