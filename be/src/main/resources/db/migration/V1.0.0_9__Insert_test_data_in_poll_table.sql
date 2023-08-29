INSERT INTO public."poll"(
	id, survey_id, question_text, poll_type)
	VALUES (1, 1, 'Are you satisfied with the quality of our products?','SINGLE'),
	       (2, 1, 'Are you satisfied with the price of our products?','SINGLE'),
	       (3, 1, 'Does the price of our products match its quality?','SINGLE'),

	       (4, 2, 'Are you satisfied with your work schedule?','SINGLE'),
	       (5, 2, 'Where would you like to see additional recreation areas','VARIABLE'),
	       (6, 2, 'What period of time should be allocated for a lunch break?','SINGLE'),

	       (7, 3, 'Have you tried out the new features of our app?','SINGLE'),
	       (8, 3, 'Were the innovations useful?','SINGLE'),
	       (9, 3, 'Choose innovations whose work satisfies you','VARIABLE'),
	       (10, 3, 'Choose innovations whose work does not satisfy you','VARIABLE'),

	       (11, 5, 'How often do you meet our brand in everyday life?','SINGLE'),
	       (12, 5, 'How often do you see ads for our brand','SINGLE'),
	       (13, 5, 'How did you find out about our brand?','SINGLE'),

	       (14, 4, 'Rate our event','SINGLE'),
	       (15, 4, 'Has the organization met your expectations?','SINGLE'),
	       (16, 4, 'What age category of people do you think this event is designed for?','SINGLE'),
	       (17, 4, 'Would you like us to hold such events more often?','SINGLE');