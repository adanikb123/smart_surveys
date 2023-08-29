INSERT INTO public."survey"(
    id, survey_title, survey_description, anonymity, author_id, repeat_survey_interval,
     open_survey_date, close_survey_date, close_survey_iterable_date)
    VALUES (1, 'Customer Satisfaction Snapshot',
            'This survey aims to gather feedback from our valued customers to understand their level of satisfaction with our products and services, and identify areas for improvement.',
            TRUE, 3, '0 days', '2023-06-01 00:00:00', '2023-09-01 00:00:00', '2023-09-01 00:00:00'),
           (2, 'Employee Engagement Pulse',
           'A quick check-in with our team members to gauge their engagement, job satisfaction, and gather insights on how we can create a better work environment.',
           FALSE, 3, '0 days', '2023-01-02 14:08:03', '2023-09-20 14:17:39', '2023-09-20 14:17:39'),
           (3, 'Product Development Insights',
           'A survey designed to collect user opinions and suggestions on our latest product features, helping us prioritize improvements and tailor our offerings to better meet customer needs.',
           FALSE, 1, '0 days', '2023-01-30 00:53:58', '2023-10-22 16:04:58', '2023-10-22 16:04:58'),
           (4, 'Event Experience Feedback',
           'A post-event survey to gather attendees thoughts on the overall experience, including the quality of speakers, content, and organization, to help us plan and improve future events.',
           FALSE, 1, '1 month', '2023-01-13 22:10:11', '2023-12-01 00:00:00', '2023-07-01 00:00:00'),
           (5, 'Brand Perception Survey',
           'This survey seeks to understand how our target audience perceives our brand, including brand awareness, associations, and overall sentiment, to inform our marketing and communication strategies.',
           TRUE, 3, '0 days', '2023-05-30 05:48:52', '2023-09-07 16:48:27', '2023-09-07 16:48:27');