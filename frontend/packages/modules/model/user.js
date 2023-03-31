import Project from './project.js';

class User {
    id;
    name;
    email;
    password;
    projects = []; // Array of Project objects
    
    constructor(id, name, email, password, projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.projects = projects;
    }

    addProject(projectName, projectCode, projectDeadline, projectDescription) {
        let project = new Project(projectName, projectCode, projectDeadline, projectDescription);
        this.projects.push(project);
    }

    removeProject(project) {
        this.projects.splice(this.projects.indexOf(project), 1);
    }

    getProjects() {
        return this.projects;
    }
    
    setProjects(projects) {
        this.projects = projects;
    }

    getId() {
        return this.id;
    }
    setID(id) {
        this.id = id;
    }

    getName() {
        return this.name;
    }

    setName(name) {
        this.name = name;
    }

    getEmail() {
        return this.email;
    }

    setEmail(email) {
        this.email = email;
    }

    getPassword() {
        return this.password;
    }
    setPassword(password) {
        this.password = password;
    }
}

export default User;