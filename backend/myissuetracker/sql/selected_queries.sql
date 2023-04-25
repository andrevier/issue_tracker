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

-- Get a list of all project information for the project's page. 
SELECT mp.user_id, mp.project_id, p.project_name, p.project_description
FROM manage_project mp 
JOIN project p
ON mp.project_id = p.project_id;

SELECT v.user_id, v.project_id, v.project_name, v.project_description, pt.starting_date, pt.deadline
FROM (
    SELECT mp.user_id, mp.project_id, p.project_name, p.project_description, mp.project_time_id
    FROM manage_project mp 
    JOIN project p
    ON mp.project_id = p.project_id
) v
JOIN project_time pt
ON pt.project_time_id = v.project_time_id;

-- Get projects of the user_id and returns a project by the project_id.
SELECT v.project_id, v.project_name, v.project_description, pt.starting_date, pt.deadline
FROM (
    SELECT mp.project_id, p.project_name, p.project_description, mp.project_time_id
    FROM manage_project mp 
    JOIN project p 
    ON mp.project_id = p.project_id 
    WHERE mp.user_id = 1
) v
JOIN project_time pt 
ON pt.project_time_id = v.project_time_id

-- Get all issues from all projects and all users.
SELECT mi.issue_id, i.issue_name, i.issue_description, i.priority_label_id, i.project_id
FROM manage_issue mi
JOIN issue_data i
ON mi.issue_id = i.issue_id


-- Get issues from all projects for a defined user_id.
SELECT mi.issue_id, i.issue_name, i.issue_description, i.priority_label_id, i.project_id
FROM manage_issue mi
JOIN issue_data i
ON mi.issue_id = i.issue_id
WHERE mi.user_id = :id

-- Get issues in a project, indexed by a project_id, and a user, indexed by a user_id.
SELECT v.issue_id, v.issue_name, v.issue_description, it.starting_date, it.deadline, v.priority_label_id
FROM (
    SELECT v.issue_id, v.issue_name, v.issue_description, it.starting_date, it.deadline, v.priority_label_id
    FROM (
        SELECT mi.issue_id, i.issue_name, i.issue_description, mi.issue_time_id, i.priority_label_id
        FROM manage_issue mi
        JOIN issue_data i
        ON mi.issue_id = i.issue_id
        WHERE mi.user_id = 1 AND i.project_id = 1
    ) v
    JOIN issue_time it
    ON v.issue_time_id = it.issue_time_id
) x
JOIN priority_label pl
ON x.priority_label_id = pl.priority_label_id;

-- Cascade join
SELECT i.issue_name, i.issue_description, it.starting_date, it.deadline, i.priority_label
FROM manage_issue mi 
JOIN issue_data i ON mi.issue_id = i.issue_id
WHERE mi.user_id = 1 AND i.project_id = 1
JOIN issue_time it ON mi.issue_time_id = it.issue_time_id

