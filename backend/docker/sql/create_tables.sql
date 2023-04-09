-- Create all the tables designed according to ./images/schema.drawio
-- User entity.
CREATE TABLE user_data (
    user_id SERIAL,
    user_name VARCHAR(128) NOT NULL,
    user_email VARCHAR(128) UNIQUE NOT NULL,
    user_password VARCHAR(32) NOT NULL,
    PRIMARY KEY (user_id)
);

-- Project entity.
CREATE TABLE project (
    project_id SERIAL,
    project_name VARCHAR(128) NOT NULL,
    project_description TEXT,
    code VARCHAR(100) NOT NULL,
    PRIMARY KEY (project_id)
);

-- Entity to hold the project's time.
CREATE TABLE project_time (
    project_time_id SERIAL,
    starting_date TIMESTAMP WITH TIME ZONE NOT NULL,
    deadline TIMESTAMP WITH TIME ZONE,
    closing_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (project_time_id)
);

-- Entity to manage the project's time.
CREATE TABLE manage_project (
    project_id INTEGER REFERENCES project (project_id) ON DELETE CASCADE,
    project_time_id INTEGER REFERENCES project_time (project_time_id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES user_data (user_id) ON DELETE CASCADE,
    PRIMARY KEY (project_id, project_time_id)
);

-- Priority entity
CREATE TABLE priority_label (
    priority_id SERIAL,
    priority_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (priority_id)
);

-- Issue entity.
CREATE TABLE issue_data (
    issue_id SERIAL,
    issue_name VARCHAR(128) NOT NULL,
    issue_description TEXT,
    priority_id INTEGER REFERENCES priority_label (priority_id) ON DELETE CASCADE,
    issue_group VARCHAR(128),
    PRIMARY KEY (issue_id)
);

-- Time for an issue to be solved.
CREATE TABLE issue_time (
    issue_time_id SERIAL,
    starting_date TIMESTAMP WITH TIME ZONE NOT NULL,
    deadline TIMESTAMP WITH TIME ZONE,
    closing_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (issue_time_id)
);

-- Entity to manage the issue's time.
CREATE TABLE manage_issue (
    issue_id INTEGER REFERENCES issue_data (issue_id) ON DELETE CASCADE,
    issue_time_id INTEGER REFERENCES issue_time (issue_time_id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES user_data (user_id) ON DELETE CASCADE,
    PRIMARY KEY (issue_id, issue_time_id)
);