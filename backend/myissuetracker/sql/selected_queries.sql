-- Selected queries for the application.
-- SQL cheat-sheet to guide the implementation of queries for all requests 
-- of the app in JPA format.

-- Projects' page function (projects.html): 
-- How to get all the projects created by a user and shows the project's
-- name, starting date, and deadline to fill the projects table.
SELECT u.user_id, p.project_name, pt.starting_date, pt.deadline
FROM user_data as u
JOIN manage_project as mp
ON u.user_id = mp.user_id
JOIN project p
ON mp.project_id = p.project_id
JOIN project_time as pt
ON pt.project_time_id = mp.project_time_id;

-- Edit a project: Query from the tables.
SELECT
u.user_id, p.project_id, pt.project_time_id, p.project_name,
pt.starting_date, pt.deadline, p.project_description
FROM user_data as u
JOIN manage_project as mp 
ON u.user_id = mp.user_id
JOIN project as p 
ON mp.project_id = p.project_id 
JOIN project_time as pt
ON pt.project_time_id = mp.project_time_id;

-- Update a project.
UPDATE project as p
SET 
p.project_name = 'Buy a horse', 
p.project_description = 'Save money to buy a horse.',
where p.project_id = 1

UPDATE project_time as pt
SET
pt.starting_date = new_date, 
pt.deadline_date = new_date
WHERE pt.project_time_id = id

-- Delete a project with an id.
DELETE FROM project WHERE project_id = 1;