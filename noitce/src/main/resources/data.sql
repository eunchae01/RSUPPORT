-- Notice
INSERT INTO NOTICE (title, writer, text, write_date, start_date, end_date, hit) VALUES ('제목1', '작성자1', '내용1', '2018-01-01', '2018-01-01', '2018-01-03', 0);
INSERT INTO NOTICE (title, writer, text, write_date, start_date, end_date, hit) VALUES ('제목2', '작성자2', '내용2', '2019-01-01', '2019-01-05', '2019-01-07', 0);
INSERT INTO NOTICE (title, writer, text, write_date, start_date, end_date, hit) VALUES ('제목3', '작성자3', '내용3', '2020-01-01', '2020-01-01', '2020-01-03', 0);

-- Attach
INSERT INTO ATTACH(notice_no, ori_name, save_name, save_path, file_ext) VALUES (2, 'FILE1', 'FILE123', 'C:/', 'JPG');
INSERT INTO ATTACH(notice_no, ori_name, save_name, save_path, file_ext) VALUES (2, 'FILE2', 'FILE123222', 'C:/', 'JPG');