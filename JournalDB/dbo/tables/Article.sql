CREATE TABLE [dbo].[Article]
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [articleTitle] NVARCHAR(50) NOT NULL, 
    [articleDescription] NVARCHAR(MAX) NULL, 
    [articleAuthorName] NVARCHAR(50) NOT NULL
)
