# Question_Game_Java_SQL_File
This is a simple Question game which asks questions from SQL database or a text file.

There is a algorithm to shuffle the answers, and to score for each true answer by user.

=========================
The questions could be from a text file like this:
Question text
True answer
False answer1
False answer2
False answer3
--> Next questions like this should be written like the last five lines(8-12).

=========================
The questions could be from SQL database, namely questiongame.
The table is Qestion with six columns, such as Question_ID which is primary key
                                               Question_text
                                               True_Answer
                                               FalseAnswer1
                                               FalseAnswer2
                                               FalseAnswer3
